package HomeWork3.Task2;

import java.util.concurrent.locks.Lock;

public class LockApiThread extends Thread {

    private Counter counter;
    private String name;
    private Lock lock;

    public LockApiThread(Counter counter, String name, Lock lock) {
        this.counter = counter;
        this.name = name;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
            try {
                for (int i = 0; i < 5; i++) {
                    counter.increment();
                    System.out.println("Thread " + name + ": counting " + counter.getCount());
                }
            } finally {
                lock.unlock();
            }
    }
}
