import java.util.EmptyStackException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Atomics {
    private static AtomicInteger index = new AtomicInteger(0);
//    private static final Object mutex = new Object();
//    private static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
//        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();
        new Thread(() -> {
            while (true) {
                while (true) {
                    int oldValue = index.get();
                    int newValue = oldValue + 1;
                    if (index.compareAndSet(oldValue, newValue)) {
                        System.out.println(newValue);
                        break;
                    }
                }

            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println(index.getAndIncrement());
            }
        }).start();
    }
}

class TraiberStack<T> {
    private AtomicReference<Node<T>> tail = null;

    public void push(T newElem) {
        Node<T> newTail = new Node<>(newElem, null);
        while (true) {
            Node<T> oldTail = this.tail.get();
            newTail.next = oldTail;
            if (tail.compareAndSet(oldTail, newTail)) {
                break;
            }
        }
//        this.tail = new Node<T>(newElem, tail);
    }

    public T pop() {
        while (true) {
            if (tail == null) {
                throw new EmptyStackException();
            }
            Node<T> oldTail = tail.get();
            Node<T> newTail = oldTail.next;
            if (tail.compareAndSet(oldTail, newTail)) {
                return oldTail.value;
            }
        }
//        T result = tail.value;
//        this.tail = tail.next;
//        return result;
    }

    private static class Node<E> {
        final E value;
        Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

}
