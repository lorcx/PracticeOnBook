package java_mult_thread_core_technology.part1.stop;

/**
 * 停止线程：try catch exception
 * Created by Administrator on 2017/8/15.
 */
public class StopThread1 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 500000; i++) {
                if (this.isInterrupted()) {
                    throw new InterruptedException();
                }
                System.out.println("i = " + (i + 1));
            }
            System.out.println("for右继续运行，线程还未停止！");
        } catch (InterruptedException e) {
            System.out.println("Mythread run catch");
//            e.printStackTrace();
        }
    }
}

class Run {
    public static void main(String[] args) {
        try {
            StopThread1 m = new StopThread1();
            m.start();
            System.out.println("main -> sub sleep 2000");
            m.sleep(2000);
            m.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end!");
    }
}