package stuff;

import java.io.OutputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {
    // lock
    // synchronized?
    // reentrant lock
    // atomic string?
    // thread yield?
    // condition

    public static void main(String[] args) {
        runnable();
    }

    public static void lock() {
        OutputStream out = System.out;

    }

    /**
     * creating threads via implementing Runnable interface
     * and passing instances to Thread constructor
     * locking via Reentrant fair lock
     * */
    private static void runnable() {
        class R implements Runnable {
            private String name;
            private String message;
            private Lock lock;

            private R(String name, String message, Lock lock) {
                this.name = name;
                this.message = message;
                this.lock = lock;
            }

            @Override
            public void run() {
                while(true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(name + ": " + message);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        ReentrantLock lock = new ReentrantLock(true);

        Thread t1 = new Thread(new R("t1", "hey", lock));
        Thread t2 = new Thread(new R("t2", "eh?", lock));
        t1.start();
        t2.start();
    }

    public static void reentrantLock() {
        ReentrantLock lock = new ReentrantLock(true); // fair

        class T extends Thread {
            private String s;

            public T(String s) {
                this.s = s;
            }

            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        sleep(1000);
                        System.out.println(getName() + ": " + s);
                    } catch (InterruptedException e) {
                        System.out.println("ping was interrupted");
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        Thread ping = new T("ping");
        ping.setName("t1");

        Thread pong = new T("pong");
        pong.setName("t2");

        Thread hey = new T("hey");
        hey.setName("t3");

        ping.start();
        pong.start();
        hey.start();
    }
}
