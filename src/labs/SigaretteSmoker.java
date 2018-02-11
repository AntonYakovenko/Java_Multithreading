package labs;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SigaretteSmoker {
    private static boolean matches;
    private static boolean tobacco;
    private static boolean paper;

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Condition smokers = lock.newCondition();
        final Condition mediator = lock.newCondition();

        new Thread(() -> {
            while (true) {
                lock.lock();
                Random random = new Random();
                int probability = Math.abs(random.nextInt() % 3);
                String components = "";
                if (probability == 0) { matches = false;tobacco = true;paper = true;
                components = "tobacco and paper"; }
                if (probability == 1) { matches = true;tobacco = false;paper = true;
                components = "matches and paper"; }
                if (probability == 2) { matches = true;tobacco = true;paper = false;
                components = "matches and tobacco"; }
                try {
                    Thread.sleep(1000);
                    System.out.println("Mediator put " + components + " on the table");
                    smokers.signalAll();
                    mediator.await();
                } catch (InterruptedException ignore) {/*NOP*/} finally {
                    lock.unlock();
                }
            }
        }, "Mediator").start();

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (matches == false) {
                        Thread.sleep(1000);
                        System.out.println("Smoker1 make and smoke sigarette");
                        mediator.signal();
                        smokers.await();
                    } else {
                        Thread.sleep(1000);
                        System.out.println("Smoker1 can not make sigarette");
                        smokers.await();
                    }
                } catch (InterruptedException ignore) {/*NOP*/} finally {
                    lock.unlock();
                }
            }
        }, "SigaretteSmoker1").start(); // Smoker with matches

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (tobacco == false) {
                        Thread.sleep(1000);
                        System.out.println("Smoker2 make and smoke sigarette");
                        mediator.signal();
                        smokers.await();
                    } else {
                        Thread.sleep(1000);
                        System.out.println("Smoker2 can not make sigarette");
                        smokers.await();
                    }
                } catch (InterruptedException ignore) {/*NOP*/} finally {
                    lock.unlock();
                }
            }
        }, "SigaretteSmoker2").start(); // Smoker with tobacco

        new Thread(() -> {
            while (true) {
                lock.lock();
                try {
                    if (paper == false) {
                        Thread.sleep(1000);
                        System.out.println("Smoker3 make and smoke sigarette");
                        mediator.signal();
                        smokers.await();
                    } else {
                        Thread.sleep(1000);
                        System.out.println("Smoker3 can not make sigarette");
                        smokers.await();
                    }
                } catch (InterruptedException ignore) {/*NOP*/} finally {
                    lock.unlock();
                }
            }
        }, "SigaretteSmoker3").start(); // Smoker with paper
    }
}
