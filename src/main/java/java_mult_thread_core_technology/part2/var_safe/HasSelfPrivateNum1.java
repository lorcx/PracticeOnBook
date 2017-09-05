package java_mult_thread_core_technology.part2.var_safe;

/**
 * 不安全的实例变量
 * Created by Administrator on 2017/8/18.
 */
public class HasSelfPrivateNum1 {
    private int num = 0;

    public void addI(String username) {
//    public synchronized void addI(String username) {// 解决不同步
        try {
            /**
             * 方法内部变量是私有的不存在线程安全问题
             */
            if (username.equals("a")) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num = " + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadA1 extends Thread{
    private HasSelfPrivateNum1 numRef;

    public ThreadA1(HasSelfPrivateNum1 numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }
}

class ThreadB1 extends Thread{
    private HasSelfPrivateNum1 numRef;

    public ThreadB1(HasSelfPrivateNum1 numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("b");
    }
}

class Run8 {
    public static void main(String[] args) {
        HasSelfPrivateNum1 numRef = new HasSelfPrivateNum1();
        HasSelfPrivateNum1 numRef1 = new HasSelfPrivateNum1();
        /***
         * 如果 多个线程访问多个对象 则执行是异步的
         */
        Thread ta = new ThreadA1(numRef);
        Thread tb = new ThreadB1(numRef1);

        ta.start();
        tb.start();
    }
}