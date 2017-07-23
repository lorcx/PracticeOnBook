package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 赋值和返回虽然是原子性操作，但java中还是会被其他线程修改
 * Created by dell on 2016/3/17.
 */
public class AtomicityTest implements Runnable {
    private int i = 0;    //volitif   用于可视性
    public int getI(){   //synchronized  不会打断
        return i;        //虽然是原子性操作
    }

    public synchronized void Increment(){
        i++;
        i++;
    }

    @Override
    public void run() {
        while(true){
            Increment();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();//缓冲线程池
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);//线程执行
        while(true){
            int val = at.getI();
            if(val % 2 != 0){//原子性操作被打断
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
