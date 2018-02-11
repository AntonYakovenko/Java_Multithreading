package java8_streams_lambdas.AppsNewLection;

import java.util.Optional;
import java.util.stream.Stream;

public class App20 {
    public static void main(String[] args) {
        // Редукция на моноиде (ассоц. оператор + нейтр. элемент)
        Integer sum0 = Stream.of(1, 2, 3).reduce(0, (x, y) -> x + y);
        System.out.println(sum0);

        // Монада: Optional / Just
        // Редукция на ассоц. операторе
        Optional<Integer> sum1 = Stream.of(1, 2, 3).reduce((x, y) -> x + y);
        System.out.println(sum1);

        // Монада: Optional / Nothing
        // Редукция на ассоц. операторе
        Optional<Integer> sum2 = Stream.of(1, 2, 3).filter(x -> x > 10).reduce((x, y) -> x + y);
        System.out.println(sum2);
    }
}
