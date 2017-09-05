package java_mult_thread_core_technology.part1.stop;

/**
 * Created by Administrator on 2017/8/16.
 */
public class StopThread5 extends Thread{
    @Override
    public void run() {
        while (true) {
            if (this.isInterrupted()) {
                System.out.println("停止了。。。。。");
                return;
            }
            System.out.println("time" + System.currentTimeMillis());
        }
    }
}

class Run5 {
    public static void main(String[] args) {
        try {
            StopThread5 s5 = new StopThread5();
            s5.start();
            Thread.sleep(1);
            s5.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
