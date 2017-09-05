package java_mult_thread_core_technology.part7.group;


/**
 * Created by no_one on 2017/9/5.
 */
public class MyThread2 extends Thread {
    private Object lock;
    private String showChar;
    private int showNumPosition;
    private int printCount;
    private volatile static int addNumber;


    public MyThread2(Object lock, String showChar, int showNumPosition) {
        this.lock = lock;
        this.showChar = showChar;
        this.showNumPosition = showNumPosition;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                if (addNumber % 3 == showNumPosition) {
                    System.out.println("ThreadName" + Thread.currentThread().getName() + " runcCount=" + addNumber + " " + showChar);
                    lock.notifyAll();
                    addNumber++;
                    printCount++;
                    if (printCount == 3) {
                        break;
                    }
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Run5 {
    public static void main(String[] args) {
        Object lock = new Object();
        MyThread2 a = new MyThread2(lock, "A", 1);
        MyThread2 b = new MyThread2(lock, "B", 2);
        MyThread2 c = new MyThread2(lock, "C", 0);
        a.start();
        b.start();
        c.start();
    }
}