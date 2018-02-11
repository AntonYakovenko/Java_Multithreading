package java8_streams_lambdas;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class App10 {
    public static void main(String[] args) {

        Supplier<Double> supplier = Math::random;
        Function<String, Integer> function = Integer::valueOf;
        Predicate<Double> predicate = arg -> arg > 1;

        // "1" -> "1"
        // "2 33" -> "2", "33"
        // "4, 55, 666" -> "4", "55", "666"
        // Output: "1", "2", "33", "4", "55", "666"
        Function<String, Stream<String>> f = s -> Stream.of(s.split(" "));
        Arrays.asList("", "1", "2 33", "4 55 666")
                .stream()
                .flatMap(f)
                .forEach(System.out::println);

    }
}
