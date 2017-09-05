package java_mult_thread_core_technology.part2.sync;

/**
 * Created by Administrator on 2017/8/21.
 */
public class ObjectService {
    public void serviceMethodA() {
        synchronized (this) {
            try {
                System.out.println("A begin time = " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("A end time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void serviceMethodB() {
        synchronized (this) {
            System.out.println("B begin time = " + System.currentTimeMillis());
            System.out.println("B end time = " + System.currentTimeMillis());
        }
    }
}

class ThreadA extends Thread {
    private ObjectService service;

    public ThreadA(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.serviceMethodA();
    }
}

class ThreadB extends Thread {
    private ObjectService service;

    public ThreadB(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.serviceMethodB();
    }
}

class Run12 {
    public static void main(String[] args) {
        ObjectService service = new ObjectService();
        Thread a = new ThreadA(service);
        a.setName("a");
        a.start();
        Thread b = new ThreadB(service);
        b.setName("b");
        b.start();
    }
}