package java8_streams_lambdas.AppsNewLection;

import java.util.Optional;

public class App22 {
    public static void main(String[] args) {
        Integer k = 42;
        Optional<Integer> optK = Optional.of(42);
        Optional<Integer> optNull = Optional.empty();

        Integer p = k + 1;
        Optional<Integer> optP = optK.map(x -> x + 1);
        System.out.println(optP);
    }
}
