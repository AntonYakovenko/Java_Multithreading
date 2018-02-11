package labs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Hairdresser {
//    private final Lock lock = new ReentrantLock();
//    private final Condition clients = lock.newCondition();
//    private final Condition hairdresser = lock.newCondition();

    public static void main(String[] args) {
//        final Object mutex = new Object();
        final int CAPACITY = 5;
        final int CLIENTS = 10;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);

        new Thread(() -> {
            for (int i = 1; i < CLIENTS; i++) {
                try {
                    queue.put(i);
                    System.out.println("Client#" + i + " takes place in queue");
                    Thread.sleep(1000);
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }, "Client").start();

        new Thread(() -> {
            while (true) {
                try {
                    Integer data = queue.poll(1000, TimeUnit.MILLISECONDS);
                    if (data == null) {
                        System.out.println("Hairdresser has no job");
                    } else {
                        System.out.println("Hairdresser cut hair from " + data + " client");
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }, "Hairdresser").start();
    }
}
