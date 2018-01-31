package reentrant_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of SingleElementBuffer using ReentrantLock
 * Advantages:
 * 1. fairness
 * 2. lock/unlock - independent
 * 3. can use tryLock instead lock (synchronized)
 **/

public class SingleElementBufferRL {
    private final Lock lock = new ReentrantLock(true);
    private final Condition producers = lock.newCondition();
    private final Condition consumers = lock.newCondition();
    private Integer elem = null;

    public void put(int newElem) throws InterruptedException {
        lock.lock();
        try {
            while (this.elem != null) {
                producers.await(); // await not wait
            }
            this.elem = newElem;
            consumers.signal(); // signal not signalAll
        } finally {
            lock.unlock();
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        try {
            while (elem == null) {
                consumers.await(); // await not wait
            }
            Integer result = this.elem;
            this.elem = null;
            producers.signal(); // signal not signalAll
            return result;
        } finally {
            lock.unlock();
        }
    }

    public boolean tryPut(int newElem) throws InterruptedException {
        if (lock.tryLock()) { // can call tryLock with timeout
            try {
                while (this.elem != null) {
                    producers.await(); // await not wait
                }
                this.elem = newElem;
                consumers.signal(); // signal not signalAll
            } finally {
                lock.unlock();
                return true;
            }
        } else {
            return false;
        }
    }
}
