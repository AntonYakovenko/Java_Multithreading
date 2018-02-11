package java8_streams_lambdas;

import java.util.Optional;
import java.util.stream.Stream;

public class App11 {
    public static void main(String[] args) {

//        Stream<Double> stream = Stream.generate(Math::random);
//        int sum = Stream.iterate(1, k -> k + 1)
//                .limit(10)
//                .parallel()
//                .reduce(0, (x, y) -> x + y);

        Optional<Integer> sum = Stream.iterate(1, k -> k + 1)
                .limit(10)
                .parallel()
                .reduce((x, y) -> x + y);

        System.out.println(sum);

    }
}
