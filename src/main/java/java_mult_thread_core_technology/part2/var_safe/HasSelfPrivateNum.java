package java_mult_thread_core_technology.part2.var_safe;

/**
 * 线程私有变量是安全的
 * Created by Administrator on 2017/8/18.
 */
public class HasSelfPrivateNum {
    public void addI(String username) {
        try {
            /**
             * 方法内部变量是私有的不存在线程安全问题
             */
            int num = 0;
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

class ThreadA extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreadA(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("a");
    }
}

class ThreadB extends Thread{
    private HasSelfPrivateNum numRef;

    public ThreadB(HasSelfPrivateNum numRef) {
        this.numRef = numRef;
    }

    @Override
    public void run() {
        numRef.addI("b");
    }
}

class Run9 {
    public static void main(String[] args) {
        HasSelfPrivateNum numRef = new HasSelfPrivateNum();
        ThreadA ta = new ThreadA(numRef);
        ThreadB tb = new ThreadB(numRef);

        ta.start();
        tb.start();
    }
}