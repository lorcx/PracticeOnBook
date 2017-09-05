package java_mult_thread_core_technology.part7.exception;

/**
 * Created by no_one on 2017/9/5.
 */
public class Main2 {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("线程1");
        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            // 线程默认异常处理器
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程：" + t1.getName() + " 出现了异常");
                e.printStackTrace();
            }
        });
        t1.start();


    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        String username = null;
        System.out.println(username.hashCode());
    }
}