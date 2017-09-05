package java_mult_thread_core_technology.part3.lock_release;

/**
 * 调用notify后 执行完synchronized 代码 才释放锁
 * Created by Administrator on 2017/8/28.
 */
public class NotifyTest {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin wait () time" + System.currentTimeMillis());
                lock.wait();
                System.out.println("end wait () time" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void synNotifyMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin notify() ThreadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
                lock.notify();
                Thread.sleep(5000);
                System.out.println("end notify() ThreadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThreadA5 extends Thread{
    private Object lock;

    public MyThreadA5(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyTest wt = new NotifyTest();
        wt.testMethod(lock);
    }
}

class NotifyThread extends Thread {
    private Object lock;

    public NotifyThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyTest wt = new NotifyTest();
        wt.synNotifyMethod(lock);
    }
}

class synNotifyMethodThread extends Thread{
    private Object lock;

    public synNotifyMethodThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyTest wt = new NotifyTest();
        wt.synNotifyMethod(lock);
    }
}

class Run30{
    public static void main(String[] args) {
        Object lock = new Object();
        Thread a5 = new MyThreadA5(lock);
        a5.start();
        Thread notifyThread = new NotifyThread(lock);
        notifyThread.start();
        Thread synNotifyMethodThread = new synNotifyMethodThread(lock);
        synNotifyMethodThread.start();
    }
}



