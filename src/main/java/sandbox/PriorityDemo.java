package sandbox;

public class PriorityDemo {
    private static class Counter extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
                System.out.println(getName() + ":" + i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Counter();
        t1.setName("t1");
        t1.setPriority(Thread.MAX_PRIORITY);

        Thread t2 = new Counter();
        t2.setName("t2");
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }
}
