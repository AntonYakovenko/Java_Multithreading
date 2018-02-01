package thread_pool;

import java.util.concurrent.Executor;

public class Demo04_MyThreadPool {
    public static void main(String[] args) {
        Executor executor = new MyThreadPool(2);
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
        System.out.println("Bye!");
    }

    private static Runnable getTask() {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from " + Thread.currentThread());
            }
        };
    }
}
