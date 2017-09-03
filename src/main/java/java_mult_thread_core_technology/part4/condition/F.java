package java_mult_thread_core_technology.part4.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lx on 2017/9/3.
 */
public class F {
   public volatile static int nextPrintWho = 1;
}

class RunF {
    private static ReentrantLock lock = new ReentrantLock();
    private static final Condition c1 = lock.newCondition();
    private static final Condition c2 = lock.newCondition();
    private static final Condition c3 = lock.newCondition();

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (F.nextPrintWho != 1) {
                        c1.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadA " + (i + 1));
                    }

                    F.nextPrintWho = 2;
                    c2.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (F.nextPrintWho != 2) {
                        c2.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadB " + (i + 1));
                    }

                    F.nextPrintWho = 3;
                    c3.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (F.nextPrintWho != 3) {
                        c3.await();
                    }

                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadC " + (i + 1));
                    }

                    F.nextPrintWho = 1;
                    c1.signalAll();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        };

        Thread[] aArr = new Thread[5];
        Thread[] bArr = new Thread[5];
        Thread[] cArr = new Thread[5];

        for (int i = 0; i < 5; i++) {
            aArr[i] = new Thread(t1);
            bArr[i] = new Thread(t2);
            cArr[i] = new Thread(t3);

            aArr[i].start();
            bArr[i].start();
            cArr[i].start();
        }
    }
}