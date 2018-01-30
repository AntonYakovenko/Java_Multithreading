package monitor_synchronized;

import java.util.concurrent.locks.Lock;

public class App05 {
    static volatile boolean in = false;

    public static void main(String[] args) throws InterruptedException {
        final Object monitor = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                    in = true;
//                    while (true) {
//                        Thread.yield();
//                    }
                    try {
                        System.out.println("X");
                        monitor.wait();
                        System.out.println("Y");
                    } catch (InterruptedException ignore) {/*NOP*/}
                }
            }
        }).start();

//        Thread.sleep(1_000);

        System.out.println("A");
        while(!in); //spin lock / busy waiting
        System.out.println("B");
        synchronized (monitor) {
            System.out.println("C");
            monitor.notify();
            System.out.println("D");
        }
        System.out.println("E");
    }
}
