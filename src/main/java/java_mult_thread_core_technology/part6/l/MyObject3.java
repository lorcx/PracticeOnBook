package java_mult_thread_core_technology.part6.l;

/**
 * Created by no_one on 2017/9/4.
 */
public class MyObject3 {
    private static MyObject3 instance = null;

    private MyObject3() {}

    static {
        instance = new MyObject3();
    }

    public static MyObject3 getInstance() {
        return instance;
    }
}

class MyThread3 extends Thread {
    @Override
    public void run() {
        System.out.println(MyObject3.getInstance().hashCode());
    }
}

class Run3 {
    public static void main(String[] args) {
        MyThread3 t1 = new MyThread3();
        MyThread3 t2 = new MyThread3();
        MyThread3 t3 = new MyThread3();
        t1.start();
        t2.start();
        t3.start();
    }
}
