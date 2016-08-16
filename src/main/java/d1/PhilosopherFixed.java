package d1;

import java.util.Random;

class PhilosopherFixed extends Thread {
    private Chopstick first, second;
    private Random random;

    public PhilosopherFixed(Chopstick left, Chopstick right) {
        if (left.getId() < right.getId()) {
            first = left;
            second = right;
        } else {
            first = right;
            second = left;
        }
        random = new Random();
    }

    public void run() {
        try {
            while(true) {
                Thread.sleep(random.nextInt(1000));
                synchronized(first) {
                    synchronized(second) {
                        Thread.sleep(random.nextInt(1000));
                    }
                }
            }
        } catch(InterruptedException e) {
            // ...
           }
    }
}

