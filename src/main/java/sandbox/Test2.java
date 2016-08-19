package sandbox;

/**
 * testing jvm running until all simple threads are alive
 * */
public class Test2 {
    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("hello from thread");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
            }
        };

        t1.start();
    }
}
