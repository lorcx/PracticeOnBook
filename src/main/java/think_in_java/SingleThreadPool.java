package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用一个线程
 * Created by lx on 2015/12/25.
 */
public class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5;i++)
            service.execute(new LiftOff());
        service.shutdown();
    }
}
