package think_in_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 赛马游戏
 * cyclicBarrier的使用
 * 一个同步辅助类，它允许一组线程互相等待，
 * 直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，
 * 这些线程必须不时地互相等待，
 * 此时 CyclicBarrier 很有用。因为该 barrier
 * 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 *
 *   CyclicBarrier
 *    支持一个可选的 Runnable 命令，
 *   在一组线程中的最后一个线程到达之后
 *   （但在释放所有线程之前），
 *    该命令只在每个屏障点运行一次。
 *   若在继续所有参与线程之前更新共享状态，
 *    此屏障操作 很有用。
 *
 *
 *    执行顺序
 *    调用构造方法进入后，初始化barrier但不是立即执行里面的runnable
 *    执行execute从线程池中获取线程，并运行线程。
 *    当这个线程运行完毕后调用await()方法 后进入runnable方法
 * Created by dell on 2016/3/29.
 */
public class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;//跨过
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        Horse.barrier = barrier;
    }

    /**
     * 返回所跨的步数
     * @return strides
     */
    public synchronized int getStrides(){
        return strides;
    }


    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                synchronized (this){
                    strides += rand.nextInt(3);//0,1,2
                }
                /**
                 * 在所有参与者都已经在此 barrier
                 * 上调用 await 方法之前，将一直等待。
                 * 然后执行屏障点
                 *
                 */
                barrier.await();//每个线程都执行一次等待
            }
        } catch (InterruptedException e) {

        }catch (BrokenBarrierException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    /**
     *
     * @return 显示马的运行轨迹
     */
    public String tracks(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < getStrides();i++){
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}

class HorseRace{
    static final int FINISH_LINE = 75;//终点线
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    /**
     * nHorse几匹马
     * pause暂停时间
     */
    public HorseRace(int nHorse,final int pause){
        System.out.println(11111111);
        barrier = new CyclicBarrier(nHorse, new Runnable() {

            /**
             * 所有任务都await后调用此方法
             */
            @Override
            public void run() {
                System.out.println(33333333);

                //画出路程线
                StringBuilder sb = new StringBuilder();
                for(int i = 0;i < FINISH_LINE;i++){
                    sb.append("=");
                }
                System.out.println(sb);
                //输出每条马的运行轨迹
                for(Horse horse : horses){
                    System.out.println(horse.tracks());
                }

                //判断是否有马跑到终点
                for(Horse horse : horses){
                    if(horse.getStrides() >= FINISH_LINE){
                        System.out.println(horse + "WON!");
                        exec.shutdownNow();
                        return;
                    }
                }
                //模拟跑
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(2222222);
        for(int i = 0;i < nHorse;i++){
            System.out.println(4444444);
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);//执行
        }
    }

    public static void main(String[] args) {
        int nHorse = 7;//马的个数
        int pause = 200;//暂停时间
        if(args.length > 0){
            int n = new Integer(args[0]);
            nHorse = n > 0 ? n : nHorse;
        }

        if(args.length > 1){
            int p = new Integer(args[1]);
            pause = p > 0 ? p : pause;
        }

        new HorseRace(nHorse,pause);
    }
}