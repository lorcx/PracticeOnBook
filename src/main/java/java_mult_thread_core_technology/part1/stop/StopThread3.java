package java_mult_thread_core_technology.part1.stop;

/**
 * Created by Administrator on 2017/8/16.
 */
public class StopThread3 extends Thread{
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i = " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Run3 {
    public static void main(String[] args) {
        try {
            StopThread3 st3 = new StopThread3();
            st3.start();
            st3.sleep(8000);
            st3.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
