import static java.lang.System.currentTimeMillis;


public class Producer implements Runnable {
    private final int id;
    private int value;
    private final int period;
    private final SingleElementBuffer buffer;

    public Producer(int id, int value, int period, SingleElementBuffer buffer) {
        this.id = id;
        this.value = value;
        this.period = period;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(period);
                buffer.put(value++);
                System.out.println(currentTimeMillis() + ": " + value + " produced by P#" + id);
            } catch (InterruptedException ignore) {/*NOP*/}
        }
    }
}
