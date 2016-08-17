package d2;

import java.util.concurrent.locks.ReentrantLock;

public class Interruptible {
    public static void main(String[] args) throws InterruptedException {
        final ReentrantLock l1 = new ReentrantLock();
        final ReentrantLock l2 = new ReentrantLock();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    l1.lockInterruptibly();
                    Thread.sleep(2000);
                    l2.lockInterruptibly();
                } catch (InterruptedException e) {
                    System.out.println("t1 interrupted");
                }
            }
        };

        t1.start();
        t1.interrupt();
        t1.join();
    }
}
