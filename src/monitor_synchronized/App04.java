package monitor_synchronized;

public class App04 {
    public static void main(String[] args) {
        final Object monitor0 = new Object();
        final Object monitor1 = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("0");
                while (true) {
                    synchronized (monitor0) {
                        System.out.println("+A");
                        System.out.println("-A");
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
                while (true) {
                    synchronized (monitor0) {
                        System.out.println("+B");
                        System.out.println("-B");
                    }
                }
            }
        }).start();
    }
}
