package java_mult_thread_core_technology.part3.lock_release;

/**
 * 验证sleep
 * Created by Administrator on 2017/8/25.
 */
public class SleepTest {
    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("begin sleep()");
                Thread.sleep(400000);
                System.out.println("end sleep()");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThreadA4 extends Thread{
    private Object lock;

    public MyThreadA4(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        SleepTest st = new SleepTest();
        st.testMethod(lock);
    }
}

class MyThreadB4 extends Thread{
    private Object lock;

    public MyThreadB4(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        SleepTest st = new SleepTest();
        st.testMethod(lock);
    }
}

class Run27 {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread a = new MyThreadA4(o);
        a.start();
        Thread b = new MyThreadB4(o);
        b.start();
//
//        char c = (char)100;
//        System.out.println(c);

//        float f = 20f;
//        System.out.println(Short.MAX_VALUE);
    }
}

