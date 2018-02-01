package thread_pool;

import java.util.concurrent.Executor;

public class Demo01 {
    public static void main(String[] args) {
        // two tasks for Executor 1
        Executor executor0 = getExecutor();
        executor0.execute(getTask());
        executor0.execute(getTask());

        // three tasks for Executor 2
        Executor executor1 = getExecutor();
        executor1.execute(getTask());
        executor1.execute(getTask());
        executor1.execute(getTask());
    }

    private static Executor getExecutor() {
        throw new UnsupportedOperationException();
    }

    private static Runnable getTask() {
        throw new UnsupportedOperationException();
    }
}
