package think_in_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 装饰性花园
 * 计算每个公园门的进入人数和总人数
 * Created by lx on 2016/3/19.
 */
@SuppressWarnings("all")
public class Count {
    private int count = 0;
    private Random rand = new Random(47);

    public int increment(){
        int temp = count;
        if(rand.nextBoolean()){
           Thread.yield();
        }
        return count = ++temp;
    }

    public synchronized int value(){
        return count;
    }
}

/**
 * 公园的门
 */
@SuppressWarnings("all")
class Entrance implements Runnable {
    private static Count count = new Count();//公园的计数器
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private int number = 0;//当前这个门通过的人数
    private final int id;//每个门的唯一标识
    private static volatile boolean cancel = false;//关闭的标识符

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {
        while(!cancel){//没关门
            synchronized (this){
                ++number;
            }

            System.out.println(this + "total :" + count.increment());

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("stoping " + this);
        }
    }

    @Override
    public String toString() {//门id + 通过这个门的人数
        return "Entrance " + "id=" + id + "-" + getValue();
    }

    /**
     * 获取通过当前门的总人数
     * @return
     */
    public synchronized int getValue() {
        return number;
    }

    /**
     * 公园的总人数
     * @return
     */
    public static int getTotalValue(){
        return count.value();
    }

    /**
     * 关闭门
     */
    public static void cancel(){
        cancel = true;
    }

    /**
     * 计算公园所有门的总人数
     * @return
     */
    public static int sumEntrance(){
        int sum = 0;
        for(Entrance et : entrances){
            sum += et.getValue();
        }
        return sum;
    }

}

/**
 *  Oranmental装饰性的
 *  Garden花园
 */
class OranmentalGarden{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0;i < 5;i++){
            exec.execute(new Entrance(i));
        }

        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();

        //awaitTermination 所有任务如果不能在250毫秒内关闭
        if(!exec.awaitTermination(250, TimeUnit.MICROSECONDS)){
            System.out.println("有任务没能关闭");
        }

        System.out.println("公园总人数 " + Entrance.getTotalValue());
        System.out.println("所有通过门的人数和 " + Entrance.sumEntrance());
    }

}