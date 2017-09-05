package java_mult_thread_core_technology.part3.notify;

/**
 * Created by Administrator on 2017/8/28.
 */
public class NotifyOne {

    public void testMethod(Object lock) {
        synchronized (lock) {
            try {
                System.out.println("begin wait() ThreadName =" + Thread.currentThread().getName());
                lock.wait();
                System.out.println("end wait() ThreadName =" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TheadA6 extends Thread {
    private Object lock;

    public TheadA6(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyOne no = new NotifyOne();
        no.testMethod(lock);
    }
}

class TheadB6 extends Thread {
    private Object lock;

    public TheadB6(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyOne no = new NotifyOne();
        no.testMethod(lock);
    }
}

class TheadC6 extends Thread {
    private Object lock;

    public TheadC6(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        NotifyOne no = new NotifyOne();
        no.testMethod(lock);
    }
}

class NotifyThread extends Thread {
    private Object lock;

    public NotifyThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
//            lock.notifyAll();
            lock.notify();
//            lock.notify();
//            lock.notify();
        }
    }
}


class Run32 {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Thread ta = new TheadA6(lock);
        ta.start();
        Thread tb = new TheadB6(lock);
        tb.start();
        Thread tc = new TheadC6(lock);
        tc.start();
        Thread.sleep(1000);
        NotifyThread nt  = new NotifyThread(lock);
        nt.start();
    }
}