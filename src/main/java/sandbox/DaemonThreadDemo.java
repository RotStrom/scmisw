package sandbox;

public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("hello from thread");
                    try {
                        sleep(1000);

                    } catch (InterruptedException e) {
                        System.out.println("interrupted");
                    }
                }
            }
        };

        t.setDaemon(true);
        System.out.println("our thread is daemon: " + t.isDaemon());
        t.start();
    }
}
