package sandbox;

public class Test {
    static class A1 implements Runnable {
        public void run() {
            System.out.println("hello from class implementing runnable");
        }
    }

    static class A2 extends Thread {
        @Override
        public void run() {
            System.out.println("hello from subclass of thread");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        A1 a1 = new A1();

        Thread t1 = new Thread(a1);
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("hello from anon class thread");
            }
        });
        Thread t3 = new A2();


        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("hello from main thread");
    }
}
