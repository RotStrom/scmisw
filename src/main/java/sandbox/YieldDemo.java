package sandbox;

import java.util.Stack;

public class YieldDemo {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (;;) {
                    if (s.empty()) Thread.yield();
                    while(!s.empty()) System.out.println(s.pop());
                }
            }
        };

        t1.setDaemon(true);
        t1.start();

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("main thread was interrupted");
            }
            s.push(i);
        }

    }
}
