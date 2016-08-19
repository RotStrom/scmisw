package sandbox;


import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LuckyTicket {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);

        LocalTime start = LocalTime.now();

        for (int i = 0; i < 10; i++) {
            final int leftI = i;
            Thread t = new Thread() {
                @Override
                public void run() {
                    for (int leftJ = 0; leftJ < 10; leftJ++)
                        for (int leftK = 0; leftK < 10; leftK++)
                            for (int leftX = 0; leftX < 10; leftX++)
                                for (int leftY = 0; leftY < 10; leftY++)
                                    for (int rightI = 0; rightI < 10; rightI++)
                                        for (int rightJ = 0; rightJ < 10; rightJ++)
                                            for (int rightK = 0; rightK < 10; rightK++)
                                                for (int rightX = 0; rightX < 10; rightX++)
                                                    for (int rightY = 0; rightY < 10; rightY++)
                                                        if (leftI + leftJ + leftK + leftX + leftY == rightI + rightJ + rightK + rightX + rightY)
                                                            count.incrementAndGet();
                }
            };
            t.start();
            t.join();
        }


        LocalTime end = LocalTime.now();

        System.out.println("total count: " + count.get());
        System.out.printf("time spent: %ss", ChronoUnit.SECONDS.between(start, end));
    }

}
