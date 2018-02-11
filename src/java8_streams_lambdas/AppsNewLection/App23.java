package java8_streams_lambdas.AppsNewLection;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.concurrent.Executors.newCachedThreadPool;

public class App23 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = newCachedThreadPool();

        Future<byte[]> futBytes0 = pool.submit(() -> readAllBytes(get("d:/tmp0.txt")));
        Future<byte[]> futBytes1 = pool.submit(() -> readAllBytes(get("d:/tmp1.txt")));
        Future<byte[]> futBytes2 = pool.submit(() -> readAllBytes(get("d:/tmp2.txt")));

        System.out.println(futBytes0.isDone());
        System.out.println(futBytes1.isDone());
        System.out.println(futBytes2.isDone());

        byte[] bytes0 = futBytes0.get();

        System.out.println(futBytes0.isDone());
        System.out.println(futBytes1.isDone());
        System.out.println(futBytes2.isDone());

        System.out.println(Arrays.toString(bytes0));

//        byte[] bytes1 = futBytes1.get();
//        System.out.println(Arrays.toString(bytes1));
//
//        byte[] bytes2 = futBytes2.get();
//        System.out.println(Arrays.toString(bytes2));

        pool.shutdown();
    }
}
