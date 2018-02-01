package thread_pool;

public class Demo00 {
    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello!");
            }
        };
        new Thread(task).start();
    }
}
