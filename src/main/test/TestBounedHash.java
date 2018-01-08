import java_concurrency_in_pratice.part4.BoundedHashSet;
import org.junit.Test;

/**
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class TestBounedHash {
    @Test
    public void test() throws InterruptedException {
        BoundedHashSet bh = new BoundedHashSet(3);

        bh.add("a");
        bh.add("b");
        bh.add("c");
        Thread t = new Thread(() -> {
            try {
                System.out.println("执行线程");
                Thread.sleep(5000);
                System.out.println("睡眠结束");
                bh.remove("a");
                bh.remove("b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        bh.add("d");
        System.out.println("end");
    }
}
