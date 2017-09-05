package java_mult_thread_core_technology.part6.e;

/**
 * Created by no_one on 2017/9/4.
 */
public class MyObject {
    public static MyObject obj = new MyObject();

    private MyObject() {}

    public static MyObject getInstance() {
        return obj;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(MyObject.getInstance().hashCode());
    }
}

class Run {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
