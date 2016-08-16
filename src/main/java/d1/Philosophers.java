package d1;

public class Philosophers {
  public static void main(String[] args) throws InterruptedException {
    Chopstick c1 = new Chopstick(1);
    Chopstick c2 = new Chopstick(2);
    Philosopher p1 = new Philosopher(1, c1, c2);
    Philosopher p2 = new Philosopher(2, c2, c1);

    p1.start();
    p2.start();
    p1.join();
    p2.join();
  }
}
