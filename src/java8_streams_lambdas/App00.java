package java8_streams_lambdas;

import java.util.Arrays;
import java.util.List;

public class App00 {
    public static void main(String[] args) {

        new Thread(App00::printHello).start();

        new Thread(() -> System.out.println("Hello!")).start();

        List<String> strings = Arrays.asList("A", "BB", "CCC");
        strings.parallelStream()
                .map(String::length)    // Function
                .filter(k -> k % 2 == 1)    // Predicate
                .forEach(System.out::println);  // Consumer
    }

    public static void printHello() {
        System.out.println("Hello!");
    }
}
