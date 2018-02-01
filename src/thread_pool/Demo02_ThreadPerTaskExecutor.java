package thread_pool;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

public class Demo02_ThreadPerTaskExecutor {
    public static void main(String[] args) {
        Executor executor = getExecutor();
        executor.execute(getTask());
        executor.execute(getTask());

        System.out.println("Hello from " + Thread.currentThread());
    }

    // ThreadPerTaskExecutor
    private static Executor getExecutor() {
        return new Executor() {
            private final AtomicLong index = new AtomicLong(0);
            private final ThreadGroup group = new ThreadGroup("MyGroup");
            @Override
            public void execute(Runnable command) {
                Thread thread = new Thread(group, command);
                thread.setPriority(Thread.NORM_PRIORITY + 1);
//                thread.setDaemon(true);
                thread.setName("Thread#" + index.getAndIncrement());
                thread.start();
//                new Thread(command).start();
            }
        };
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
