package java_mult_thread_core_technology.part2.lock;

/**
 * Created by Administrator on 2017/8/22.
 */
public class MyService {
    private String lock = "123";

    public void testMethod() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + " begin " + System.currentTimeMillis());
                lock = "456";
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

class ThreadA4 extends Thread{
    MyService service;

    public ThreadA4(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class ThreadC4 extends Thread{
    MyService service;

    public ThreadC4(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class Run16 {
    public static void main(String[] args) throws InterruptedException {
        MyService s = new MyService();
        Thread t1 = new ThreadA4(s);
        t1.setName("a2");
        t1.start();
        Thread t3 = new ThreadC4(s);
        t3.setName("c2");
//        Thread.sleep(200);
        t3.start();
    }
}