package thread_pool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Demo07_Submit {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService execService = Executors.newCachedThreadPool();

        Callable<Integer> task0 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 42;
            }
        };

        Future<Integer> future0 = execService.submit(task0);

        Callable<Integer> task1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                while (true) {
                    /*Infinity loop*/
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                }
            }
        };

        Future<Integer> future1 = execService.submit(task1);

        Thread.sleep(1000);
        System.out.println("future0.isDone(): " + future0.isDone());
        System.out.println("future1.isDone(): " + future1.isDone());

        System.out.print("future0.get(): ");
        System.out.println(future0.get());
        System.out.print("future1.get(): ");
        System.out.println(future1.get());  // block here!
        System.out.println("finish");

        System.out.println();

        Integer result = execService.invokeAny(Arrays.asList(task0, task1));
        System.out.println(result);

        execService.shutdownNow();

    }
}
