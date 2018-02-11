package java8_streams_lambdas.AppsNewLection;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class App26 {
    public static void main(String[] args) throws IOException {
        CompletableFuture<String> f0 = supplyAsync(() -> {sleep(3000); return "A";});
        CompletableFuture<String> f1 = supplyAsync(() -> {sleep(1000); return "B";});

//        f0.acceptEitherAsync(f1, System.out::println);
        f0.thenAcceptBothAsync(f1, (a, b) -> System.out.println(a + "#" + b));

        System.in.read();
    }

    private static void sleep(long dt) {
        try {
            Thread.sleep(dt);
        } catch(InterruptedException ignore) {/*NOP*/}
    }
}
