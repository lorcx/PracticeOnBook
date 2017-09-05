package java_mult_thread_core_technology.part2.sync;

/**
 * Created by Administrator on 2017/8/22.
 */
public class Service3 {
    public synchronized void methodA() {
        System.out.println("methodA begin");
        boolean isContinueRun = true;
        while (isContinueRun) {

        }
        System.out.println("methodA end");
    }

    public synchronized void methodB() {
        System.out.println("methodB begin");
        System.out.println("methodB end");
    }
}


class ThreadA3 extends Thread{
    Service3 service;

    public ThreadA3(Service3 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodA();
    }
}

class ThreadC3 extends Thread{
    Service3 service;

    public ThreadC3(Service3 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.methodB();
    }
}

class Run15 {
    public static void main(String[] args) {
        Service3 s = new Service3();
        Thread t1 = new ThreadA3(s);
        t1.setName("a2");
        t1.start();
        Thread t3 = new ThreadC3(s);
        t3.setName("c2");
        t3.start();
    }
}
