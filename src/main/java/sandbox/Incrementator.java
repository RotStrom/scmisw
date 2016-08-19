package sandbox;

public class Incrementator extends Thread {
    private volatile boolean mIsIncrement = true;
    private volatile boolean mFinish = false;

    public void changeAction() {
        mIsIncrement = !mIsIncrement;
    }

    public void finish() {
        mFinish = true;
    }

    @Override
    public void run() {
        for (; ; ) {
            if (!mFinish) {
                if (mIsIncrement) Prog.mValue++;
                else Prog.mValue--;
                System.out.println(Prog.mValue);
            } else return;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }
    }
}
