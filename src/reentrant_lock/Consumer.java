import static java.lang.System.currentTimeMillis;

public class Consumer implements Runnable {
    private final int id;
    private final SingleElementBuffer buffer;

    public Consumer(int id, SingleElementBuffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer elem = buffer.get();
                System.out.println(" " + currentTimeMillis() + ": " + elem + " consumed by C#" + id);
            } catch (InterruptedException ignore) {/*NOP*/}
        }
    }
}
