public class SingleElementBuffer {
    private Integer elem = null;
//    private final Object lock = new Object();

    public synchronized void put(int newElem) throws InterruptedException {
        while (this.elem != null) {
            this.wait();
        }
        this.elem = newElem;
        this.notifyAll();
    }

    public synchronized int get() throws InterruptedException {
        while (elem == null) {
            this.wait();
        }
        Integer result = this.elem;
        this.elem = null;
        this.notifyAll();
        return result;
    }

    //    public void put(int newElem) throws InterruptedException {
//        synchronized (lock) {
//            while (this.elem != null) {
//                lock.wait();
//            }
//            this.elem = newElem;
//            lock.notifyAll();
//        }
//    }

//    public int get() throws InterruptedException {
//        synchronized (lock) {
//            while (elem == null) {
//                lock.wait();
//            }
//            Integer result = this.elem;
//            this.elem = null;
//            lock.notifyAll();
//            return result;
//        }
//    }
}
