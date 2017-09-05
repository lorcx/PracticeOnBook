package java_mult_thread_core_technology.part2.reentrant;

/**
 * Created by Administrator on 2017/8/18.
 */
public class Sub extends Main{
    @Override
    public synchronized void operateIMainMethod() {
        try {
            while (i > 0) {
                i--;
                System.out.println("sub i = " + i);
                Thread.sleep(100);
                super.operateIMainMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
