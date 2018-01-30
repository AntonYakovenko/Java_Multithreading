package monitor_synchronized;

public class App00 {
    public static void main(String[] args) throws InterruptedException {
        Object ref = new Object();
        synchronized (ref) {
            ref.notify();
            System.out.println(Thread.holdsLock(ref));
        }
    }
}
