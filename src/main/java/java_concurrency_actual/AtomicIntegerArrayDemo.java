package java_concurrency_actual;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);
    public static class AddThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            for (int k = 0; k < 10000; k++) {
                arr.getAndIncrement(k % arr.length());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int k = 0; k < 10; k++) {
            ts[k] = new Thread(new AddThread());
        }
        for (int k = 0; k < 10; k++) {
            ts[k].start();
        }
        for (int k = 0; k < 10; k++) {
            ts[k].join();
        }
        System.out.println(arr);

    }
}
