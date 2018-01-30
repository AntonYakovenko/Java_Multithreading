package monitor_synchronized;

public class App09 {
    static /*volatile*/ int counter = 0;
    static volatile boolean finish0 = false;
    static volatile boolean finish1 = false;

    static synchronized int getCounter() {
        return counter;
    }

    //lost update
    private synchronized static void inc() {
        int tmp = counter;
        tmp = tmp +1;
        counter = tmp;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 10_000_000; k++) {
                    inc();
                }
                finish0 = true;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int k = 0; k < 10_000_000; k++) {
                    inc();
                }
                finish1 = true;
            }
        }).start();

//        while (!finish0 || !finish1);
//        System.out.println(counter);
        while (true) {
            System.out.println(getCounter());
        }
    }
}
