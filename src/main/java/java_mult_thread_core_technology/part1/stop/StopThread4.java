package java_mult_thread_core_technology.part1.stop;

/**
 * stop 暴力中断线程，stop会造成数据不一致
 * Created by Administrator on 2017/8/16.
 */
public class StopThread4 extends Thread {
    @Override
    public void run() {
        try {
            this.stop();
        } catch (ThreadDeath e) {
            System.out.println(e);
        }
    }
}

class Run4 {
    public static void main(String[] args) {
        StopThread4 st4 = new StopThread4();
        st4.start();
    }
}