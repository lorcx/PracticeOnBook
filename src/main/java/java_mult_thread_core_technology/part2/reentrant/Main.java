package java_mult_thread_core_technology.part2.reentrant;

/**
 * Created by Administrator on 2017/8/18.
 */
public class Main {
    public int i = 10;

    public synchronized void operateIMainMethod() {
        try {
            i--;
            System.out.println(" main i = " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
