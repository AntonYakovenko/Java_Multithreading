package java8_streams_lambdas.AppsNewLection;

import java.util.Optional;

import static java.util.stream.Stream.of;

public class App21 {
    public static void main(String[] args) {
        Optional<Integer> sum = of(1, 2, 3).reduce((x, y) -> x + y);
        Optional<Integer> sqr = sum.map(x -> x * x);
        Optional<String> str = sqr.map(x -> "#" + x);
        System.out.println(str);

        Optional<Integer> sum1 = of(1, 2, 3).filter(x -> x > 10).reduce((x, y) -> x + y);
        Optional<Integer> sqr1 = sum1.map(x -> x * x);
        Optional<String> str1 = sqr1.map(x -> "#" + x);
        System.out.println(str1);

        if(str.isPresent()) {
            System.out.println(str.get());
        }

        str1.ifPresent(System.out::println);
    }
}
