package java_mult_thread_core_technology.part7.state;

/**
 * Created by no_one on 2017/9/4.
 */
public class MyService {
    public static synchronized void serviceMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "进入了业务方法");
        Thread.sleep(100000);
    }

}

class MyThread1 extends Thread {
    @Override
    public void run() {
        try {
            MyService.serviceMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        try {
            MyService.serviceMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run3 {
    public static void main(String[] args) throws InterruptedException {
        MyThread1 m1 = new MyThread1();
        m1.setName("a");
        m1.start();
        MyThread2 m2 = new MyThread2();
        m2.setName("b");
        m2.start();
        Thread.sleep(2000);
        System.out.println("m1状态：" + m1.getState());
        System.out.println("m2状态：" + m2.getState());
    }
}

