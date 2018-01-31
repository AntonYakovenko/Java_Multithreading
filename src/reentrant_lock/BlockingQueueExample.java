import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

//  Producers / Consumers
public class App00 {
    public static void main(String[] args) {
//        BlockingQueue<String> queue = new ArrayBlockingQueue<>(16);
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();


        // Producer-s
        for (int k = 0; k < 3; k++) {
            int finalK = k;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int counter = 0;
                    while (true) {
                        try {
                            Thread.sleep(300 + 1000 * finalK);
                            String data = "elem-" + finalK + "/" + ++counter;
                            queue.put(data);
    //                        if (!queue.offer(++counter, 1, TimeUnit.SECONDS)) {
    //                            System.out.println("Bad ...");
    //                        } else {
    //                            System.out.println("put: " + counter);
    //                        }
                            System.out.println("put: " + data);
                        } catch (InterruptedException ignore) {/*NOP*/}
                    }
                }
            }).start();
        }

        // Consumer
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
//                for (int k = 0; k < 3; k++) {
                    try {
//                        System.out.println("... waiting for take");
                        String data = queue.take(); // block thread
//                        Integer data = queue.poll(1, TimeUnit.SECONDS);
//                        if (data == null) {
//                            System.out.println("No data");
//                        }
//                        Integer data = queue.poll(); // null
//                        Integer data = queue.remove(); // NoSuchElementException
//                        if (data != null) {
                            System.out.println("take: " + data);
//                        } else {
//                            Thread.sleep(10);
//                        }
                    } catch (InterruptedException ignore) {/*NOP*/}
                    //process(data);
                }
            }

            private void process(int data) {
                System.out.println("process: " + data);
            }
        }).start();
    }
}
