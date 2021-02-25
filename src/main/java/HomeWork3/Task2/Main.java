package HomeWork3.Task2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {

        Counter counter = new Counter();
        Lock lock = new ReentrantLock();

        new LockApiThread(counter, "one", lock).start();
        new LockApiThread(counter, "two", lock).start();
        new LockApiThread(counter, "three", lock).start();

    }
}
