package labs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Army {
    public static void main(String[] args) {
        BlockingQueue<Integer> IvanovPetrov = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> PetrovNechiporuk = new ArrayBlockingQueue<>(10);

        new Thread(() -> {
            int property = 0;
            while (true) {
                try {
                    Thread.sleep(1000);
                    IvanovPetrov.put(++property);
                    System.out.println("Ivanov takes property from stock: " + property);
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }, "Ivanov").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    int property = IvanovPetrov.take();
                    System.out.println("Petrov takes property from Ivanov: " + property);
                    PetrovNechiporuk.put(property);
                    System.out.println("Petrov put property into truck: " + property);
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }, "Petrov").start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    int property = PetrovNechiporuk.take();
                    System.out.println("Nechiporuk takes property from Petrov: " + property);
                } catch (InterruptedException ignore) {/*NOP*/}
            }
        }, "Nechiporuk").start();
    }
}
