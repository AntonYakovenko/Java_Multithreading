package thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Demo06_Executors {
    public static void main(String[] args) {
//        Executor executor = Executors.newSingleThreadExecutor();
//        Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Executor executor = Executors.newCachedThreadPool();

        executor.execute(getTask());
        executor.execute(getTask());
        executor.execute(getTask());
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
