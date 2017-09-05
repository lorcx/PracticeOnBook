package java_mult_thread_core_technology.part1.suspend;

/**
 * Created by Administrator on 2017/8/18.
 */
public class SuspendResumeLockup extends Thread {
    private long i = 0;

    @Override
    public void run() {
        while (true) {
            i++;
            System.out.println(i);
        }
    }
}

class Run8 {
    public static void main(String[] args) {
        try {
            SuspendResumeLockup srl = new SuspendResumeLockup();
            srl.start();
            Thread.sleep(1000);
            srl.suspend();
            System.out.println("main end");
        } catch (InterruptedException | RuntimeException e) {//jdk 7 特性能抛出多个异常
            e.printStackTrace();
        }
    }
}