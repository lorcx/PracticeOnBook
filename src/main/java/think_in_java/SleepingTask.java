package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 休眠
 * Created by lx on 2015/12/25.
 */
@SuppressWarnings("all")
public class SleepingTask extends LiftOff{

    @Override
    public void run() {
        while(countDown -- >0){
            try {//必须在这处理异常，异常不能跨越线程，返回main
                System.out.println(status());
                //旧的写法
                //Thread.sleep(500);
                //j2se5/j2se6的写法
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ;i < 5;i++){
            service.execute(new SleepingTask());
        }
        service.shutdown();
    }
}

