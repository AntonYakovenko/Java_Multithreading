package java8_streams_lambdas.AppsNewLection;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class App24 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = newCachedThreadPool();

        byte[] bytes = pool.invokeAny(asList(
                () -> readAllBytes(get("d:/tmp0.txt")),
                () -> readAllBytes(get("d:/tmp1.txt")),
                () -> readAllBytes(get("d:/tmp2.txt"))));

        System.out.println(new String(bytes));

        pool.shutdown();
    }
}
