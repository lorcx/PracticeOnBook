package java_mult_thread_core_technology.part2.reentrant;

/**
 * Created by Administrator on 2017/8/18.
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        Main s = new Sub();
        s.operateIMainMethod();
    }
}

class Run12{
    public static void main(String[] args) {
        MyThread m = new MyThread();
        m.start();
    }
}