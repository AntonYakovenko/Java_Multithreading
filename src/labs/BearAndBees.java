package labs;

public class BearAndBees {
    private static int level = 0;

    public static void main(String[] args) {
        final Object mutex = new Object();
        final int CAPACITY = 10;

        new Thread(() -> {
            while (true) {
                synchronized (mutex) {
                    System.out.println("Bee is trying to put honey");
                    while (level++ != CAPACITY) {
                        System.out.println("Bee put honey(" + level + ")");
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException ignore) {/*NOP*/}
                    }
                    System.out.println("Pot is full!");
                    mutex.notify();
                    try {
                        mutex.wait();
                    } catch (InterruptedException ignore) {/*NOP*/}
                }
            }
        }, "Bee").start();

        new Thread(() -> {
            while (true) {
                synchronized (mutex) {
                    System.out.println("Bear is trying to take honey");
                    if (level == CAPACITY) {
                        System.out.println("Bear takes honey");
                        level = 0;
                        mutex.notify();
                    } else {
                        System.out.println("Pot is empty!");
                        try {
                            Thread.sleep(1000);
                            mutex.wait();
                        } catch (InterruptedException ignore) {/*NOP*/}
                    }
                }
            }
        }, "Bear").start();
    }
}
