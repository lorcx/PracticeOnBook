package java_mult_thread_core_technology.part1.priority;

/**
 * 由一个线程启动另一个线程，优先级会继承。
 * Created by Administrator on 2017/8/18.
 */
public class T1 extends Thread {
    @Override
    public void run() {
        System.out.println("t1 " + this.getPriority());
        T2 t2 = new T2();
        t2.start();
    }
}

class T2 extends Thread {
    @Override
    public void run() {
        System.out.println("t2 " + this.getPriority());
    }
}

class Run {
    public static void main(String[] args) {
        System.out.println("begin priority " + Thread.currentThread().getPriority());
        T1 t1 = new T1();
        t1.setPriority(6);
        t1.start();
        System.out.println("end priority " + Thread.currentThread().getPriority());
    }
}