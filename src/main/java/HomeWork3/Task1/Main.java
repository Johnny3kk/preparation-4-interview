package HomeWork3.Task1;

public class Main {

    public static void main(String[] args) {

        Object lock = new Object();

        new PlayerThread(lock, "Ping").start();
        new PlayerThread(lock, "Pong").start();

    }
}
