package java_mult_thread_core_technology.part2.volatife;

/**
 * Created by Administrator on 2017/8/23.
 */
public class MyThread2 extends Thread {
    public volatile static int count;
    // 使用synchronized static代码块
//    static Object o = new Object();

    @Override
    public void run() {
        addCount();
    }

    /**
     * 这里一定要加static
     * 这样synchronized 与 static 锁的内容就是 MyThread2.class类了
     */
    private synchronized static void addCount() {
//        Object o = new Object();
//        synchronized (o) {
            for (int i = 0; i < 100; i++) {
                count++;
                System.out.println(count);
            }
//        }
    }
}

class Run22 {
    public static void main(String[] args) {
        MyThread2[] mtArr = new MyThread2[100];
        for (int i = 0; i < 100; i++) {
            mtArr[i] = new MyThread2();
        }
        for (int i = 0; i < 100; i++) {
            mtArr[i].start();
        }
    }
}

