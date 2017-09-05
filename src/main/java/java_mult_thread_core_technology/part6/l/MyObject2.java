package java_mult_thread_core_technology.part6.l;

/**
 * Created by no_one on 2017/9/4.
 */
public class MyObject2 {
    private static class MyObjectHandler {
        private static MyObject2 obj = new MyObject2();
    }

    private MyObject2() {}

    public static MyObject2 getInstance() {
        return MyObjectHandler.obj;
    }
}


class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println(MyObject2.getInstance().hashCode());
    }
}

class Run2 {
    public static void main(String[] args) {
        MyThread2 t1 = new MyThread2();
        MyThread2 t2 = new MyThread2();
        MyThread2 t3 = new MyThread2();
        t1.start();
        t2.start();
        t3.start();
    }
}
