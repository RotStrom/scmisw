package d2;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher extends Thread {
    private ReentrantLock left, right;
    private Random random;

    public Philosopher(ReentrantLock left, ReentrantLock right) {
        this.left = left;
        this.right = right;
        random = new Random();
    }

    private void say(String message) {
        System.out.println(getName() + ": " + message);
    }

    @Override
    public void run() {
        try {
            while (true) {
                say("thinking");
                Thread.sleep(random.nextInt(1000));
                left.lock();
                try {
                    if (right.tryLock(1000, TimeUnit.MILLISECONDS)) {
                        try {
                            say("eating");
                            Thread.sleep(random.nextInt(1000));
                        } finally {
                            right.unlock();
                        }
                    } else {
                        say("give up");
                    }
                } finally {
                    left.unlock();
                }
            }
        } catch (InterruptedException e) {
            // ...
        }
    }

}

public class Philosophers {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock c1 = new ReentrantLock();
        ReentrantLock c2 = new ReentrantLock();
        Philosopher p1 = new Philosopher(c1, c2);
        p1.setName("p1");
        Philosopher p2 = new Philosopher(c2, c1);
        p2.setName("p2");

        p1.start();
        p2.start();
        p1.join();
        p2.join();
    }
}
