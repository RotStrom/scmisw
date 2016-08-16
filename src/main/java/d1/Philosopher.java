package d1;

import java.util.Random;

class Philosopher extends Thread {
  private int id;
  private Chopstick left, right;
  private Random random;

  public Philosopher(int id, Chopstick left, Chopstick right) {
    this.id = id;
    this.left = left;
    this.right = right;
    random = new Random();
  }

  public void run() {
    try {
      while (true) {
        System.out.println(id + ": thinking");
        Thread.sleep(random.nextInt(1000)); // Think for a while
        System.out.println(id + ": grabbing left chopstick");
        synchronized (left) { // Grab left chopstick
          System.out.println(id + ": grabbing right chopstick");
          synchronized (right) { // Grab right chopstick
            Thread.sleep(random.nextInt(1000)); // Eat for a while
            System.out.println(id + ": eating");
          }
        }
      }
    } catch (InterruptedException e) {
      // ...
    }
  }
}
