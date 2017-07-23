package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool使用有限个数的线程集
 * Created by lx on 2015/12/25.
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0;i < 5;i++)
            service.execute(new LiftOff());
        service.shutdown();
    }
}
