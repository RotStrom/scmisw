package sandbox;

public class Prog2 {
    private static class Incremenator extends Thread {
        private volatile boolean mIsIncrement = true;
        private volatile int i = 0;

        public void changeAction() {
            mIsIncrement = !mIsIncrement;
        }

        @Override
        public void run() {
            for (; ; ) {
                if (!Thread.interrupted()) {
                    if (mIsIncrement) i++;
                    else i--;

                    System.out.println(i);
                } else return;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("thread was interrupted");
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Incremenator inc = new Incremenator();
        inc.start();

        for (int i = 1; i < 4; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("main thread was interrupted");
            }
            inc.changeAction();
        }

        inc.interrupt();
    }

}
