package java_mult_thread_core_technology.part1.suspend;

/**
 * 线程暂停和恢复
 * Created by Administrator on 2017/8/16.
 */
public class MyThread6 extends Thread{
    private long i = 0;

    @Override
    public void run() {
        while (true)
            i++;
    }

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }
}

class Run6 {
    public static void main(String[] args) {
        try {
            MyThread6 m6 = new MyThread6();
            m6.start();
            Thread.sleep(5000);
            // A段
            m6.suspend();
            System.out.println("A= " + System.currentTimeMillis() + " i = "+ m6.getI());
            Thread.sleep(5000);
            System.out.println("A= " + System.currentTimeMillis() + " i = "+ m6.getI());

            //B段
            m6.resume();
            Thread.sleep(5000);
            System.out.println("B= " + System.currentTimeMillis() + " i = "+ m6.getI());
            Thread.sleep(5000);
            System.out.println("B= " + System.currentTimeMillis() + " i = "+ m6.getI());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

