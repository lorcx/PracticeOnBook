package java_mult_thread_core_technology.part2.sync;

/**
 * Created by Administrator on 2017/8/22.
 */
public class Service {
    public synchronized static void printA() {
        try {
            System.out.println("线程名称：" + Thread.currentThread().getName() + "进入printA");
            Thread.sleep(3000);
            System.out.println("线程名称：" + Thread.currentThread().getName() + "离开printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void printB() {
        System.out.println("线程名称：" + Thread.currentThread().getName() + "进入printB");
        System.out.println("线程名称：" + Thread.currentThread().getName() + "离开printB");
    }

    public synchronized  void printC() {
        System.out.println("线程名称：" + Thread.currentThread().getName() + "进入printC");
        System.out.println("线程名称：" + Thread.currentThread().getName() + "离开printC");
    }

}

class ThreadA1 extends Thread{
    Service service;

    public ThreadA1(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.printA();
    }
}

class ThreadB1 extends Thread{
    Service service;

    public ThreadB1(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.printB();
    }
}

class ThreadC1 extends Thread{
    Service service;

    public ThreadC1(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.printC();
    }
}

class Run13 {
    public static void main(String[] args) {
        Service s = new Service();
        Thread t1 = new ThreadA1(s);
        t1.setName("a1");
        t1.start();
        Thread t2 = new ThreadB1(s);
        t2.setName("b1");
        t2.start();
        Thread t3 = new ThreadC1(s);
        t3.setName("c1");
        t3.start();
    }
}