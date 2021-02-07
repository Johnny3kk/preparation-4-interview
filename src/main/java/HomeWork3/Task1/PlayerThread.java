package HomeWork3.Task1;

public class PlayerThread extends Thread {

    private Object lock;
    private String role;

    public PlayerThread(Object lock, String role) {
        this.lock = lock;
        this.role = role;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    System.out.println(role);
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
