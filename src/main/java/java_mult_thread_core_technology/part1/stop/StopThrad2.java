package java_mult_thread_core_technology.part1.stop;

/**
 * Created by Administrator on 2017/8/15.
 */
public class StopThrad2 extends Thread {
    @Override
    public void run() {
        try {
            System.out.println("run begin!");
            Thread.sleep(200000);
            System.out.println("run end!");
        } catch (InterruptedException e) {
            System.out.println("在睡眠中中断" + this.isInterrupted());
        }
    }
}

class StopThread2Run {
    public static void main(String[] args) {
        try {
            StopThrad2 st2 = new StopThrad2();
            st2.start();
            Thread.sleep(200);
            st2.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
        }
        System.out.println("end!");
    }
}
