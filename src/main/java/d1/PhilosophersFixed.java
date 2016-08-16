package d1;

public class PhilosophersFixed {
  public static void main(String[] args) throws InterruptedException {
    Chopstick c1 = new Chopstick(1);
    Chopstick c2 = new Chopstick(2);

    PhilosopherFixed p1 = new PhilosopherFixed(c1, c2);
    PhilosopherFixed p2 = new PhilosopherFixed(c2, c1);

    p1.start();
    p2.start();
    p1.join();
    p2.join();
  }
}
