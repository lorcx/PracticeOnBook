package java_concurrency_in_pratice.part3;

import java_concurrency_in_pratice.anno.NoThreadSafe;

/**
 * @Author: lx
 * @Date: Created in 2017/12/27 0027
 */
@NoThreadSafe
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
