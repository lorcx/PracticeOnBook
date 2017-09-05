package java_mult_thread_core_technology.part3.lock_release;

/**
 * 验证wait会释放锁
 * Created by Administrator on 2017/8/25.
 */
public class WaitTest {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin lock()");
                lock.wait();
                System.out.println("end lock()");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThreadA3 extends Thread{
    private Object lock;

    public MyThreadA3(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitTest wt = new WaitTest();
        wt.testMethod(lock);
    }
}

class MyThreadB3 extends Thread{
    private Object lock;

    public MyThreadB3(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        WaitTest wt = new WaitTest();
        wt.testMethod(lock);
    }
}

class Run26 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread a = new MyThreadA3(o);
        a.start();
        Thread b = new MyThreadB3(o);
        b.start();
    }
}

