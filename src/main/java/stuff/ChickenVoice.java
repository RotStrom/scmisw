package stuff;

public class ChickenVoice {
    public static void main(String[] args) {
        EggVoice t1 = new EggVoice();
        System.out.println("starting disput...");
        t1.start();

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ...
            }
        }

        System.out.println("chicken");

        if (t1.isAlive()) {
            try {
                t1.join();
            } catch (InterruptedException e) {
                // ...
            }
            System.out.println("egg was first");
        } else {
            System.out.println("chicken was first");
        }

        System.out.println("disput has been ended");
    }
}
