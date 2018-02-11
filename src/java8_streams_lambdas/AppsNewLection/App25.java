package java8_streams_lambdas.AppsNewLection;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class App25 {
    public static void main(String[] args) {
        CompletableFuture<String> f0
                = CompletableFuture.supplyAsync(() -> {for (int k = 0; k < Long.MAX_VALUE; k++); return "42";});
        CompletableFuture<Integer> f1 = f0.thenApply(Integer::valueOf);
        CompletableFuture<Double> f2 = f1.thenApply(x -> Math.PI * x * x);
        f2.thenAccept(System.out::println);

        supplyAsync(() -> 42)
                .thenApply(Integer::valueOf)
                .thenApply(x -> Math.PI * x * x)
                .thenAccept(System.out::println);

        System.out.println("end");
    }
}
