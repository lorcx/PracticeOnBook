package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 偶数检查
 * 线程 消费者
 * 21.3.1共享受限制的资源
 * Created by dell on 2016/2/4.
 */
@SuppressWarnings("all")
public class EvenCheck implements Runnable {
    private IntGenerator intGenerator;
    private final int id;

    public EvenCheck(int id, IntGenerator intGenerator) {
        this.id = id;
        this.intGenerator = intGenerator;
    }


    public void run() {
        while(!intGenerator.isCancel()){
            int val = intGenerator.next();
            if(val % 2 != 0){
                System.out.println(val + "not even!");
                intGenerator.cancel();
            }
        }
    }

    public static void test(IntGenerator intGenerator,int count){
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i = 0;i < count;i++){
            es.execute(new EvenCheck(count,intGenerator));
        }
        es.shutdown();
    }

    //默认的 10次
    public static void test(IntGenerator intGenerator){
        test(intGenerator,10);
    }

}
