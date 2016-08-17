package stuff;

public class Prog {
    public static int mValue = 0;

    public static void main(String[] args) {
        Incrementator mInc = new Incrementator();
        mInc.start();

        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(i * 2 * 1000);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            mInc.changeAction();
        }

        mInc.finish();
    }
}
