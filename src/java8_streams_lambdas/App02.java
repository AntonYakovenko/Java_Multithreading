package java8_streams_lambdas;

public class App02 {
    public static void main(String[] args) {
        I0 ref0 = App02::printHello;
        I1 ref1 = ref0::f;
    }

    public static int printHello() {
        System.out.println("Hello!");
        return 42;
    }
}

interface I0 {
    public int f();
}

interface I1 {
    public void g();
}
