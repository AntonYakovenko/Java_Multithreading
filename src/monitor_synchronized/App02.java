package monitor_synchronized;

public class App02 {
    public static void main(String[] args) {
//        synchronized () {}
//        f();
        fff();
        new App02().ggg();
    }

    private synchronized static void fff() {
        System.out.println(Thread.holdsLock(App02.class));
    }

    synchronized void ggg() {
        System.out.println(Thread.holdsLock(this));
    }
}
