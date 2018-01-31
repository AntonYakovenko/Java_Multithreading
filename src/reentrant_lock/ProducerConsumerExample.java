package reentrant_lock;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SingleElementBuffer buffer = new SingleElementBuffer();


        new Thread(new Producer(1, 1, 300, buffer)).start();
        new Thread(new Producer(2, 1, 1000, buffer)).start();
        new Thread(new Producer(3, 1, 3000, buffer)).start();
//
        new Thread(new Consumer(1, buffer)).start();
//        new Thread(new Consumer(2, buffer)).start();
    }
}
