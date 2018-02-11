package java8_streams_lambdas;

import java.lang.reflect.Field;
import java.util.function.Consumer;
import java.util.stream.Stream;

@FunctionalInterface
interface F<T> {
    T add(T m, T n);
}

interface XmlSerializer {
    default String toXml() throws IllegalAccessException {
        Class clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
        }
        StringBuilder result = new StringBuilder();
        result.append("<")
                .append(clazz.getSimpleName())
                .append(">");
        for (int i = 0; i < fields.length; i++) {
            if (i != fields.length - 1) {
                result.append(fields[i].getName())
                        .append(" = ")
                        .append(fields[i].get(this))
                        .append(", ");
            } else {
                result.append(fields[i].getName())
                        .append(" = ")
                        .append(fields[i].get(this));
            }
        }
        result.append("</")
                .append(clazz.getSimpleName())
                .append(">");
        return String.valueOf(result);
    }
}

class User implements XmlSerializer {
    int age = 42;
    int height = 170;
    String name = "Mike";
}

public class App01 {
    public static void main(String[] args) throws IllegalAccessException {
        System.out.println(new User().toXml());
//        Stream<Long> stream = Stream.iterate(2L, k -> k + 3);

//        F myF = (int x, int y) -> {return x + y;};
        // type inference
        F<Integer> myF = (x, y) -> x + y;

        Consumer<String> consumer0 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        Consumer<String> consumer1 = System.out::println;

        Stream.iterate(0L, k -> k + 1)
                .parallel()
                .filter(k -> k % 3 == 2)
                .map(k -> "--" + k)
                .limit(10)
                .forEach(System.out::println);
    }
}
