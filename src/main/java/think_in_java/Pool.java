package think_in_java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * semaphore信号量
 * 21.7.6
 * 一个计数信号量。从概念上讲，
 * 信号量维护了一个许可集。
 * 如有必要，在许可可用前会阻塞每一个 acquire()，
 * 然后再获取该许可。每个 release() 添加一个许可，
 * 从而可能释放一个正在阻塞的获取者。
 * 但是，不使用实际的许可对象，
 * Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * Created by lx on 2016/4/3.
 */
public class Pool<T> {
    private int size;
    private List<T> items = new ArrayList<T>();
    private volatile boolean[] checkout;
    private Semaphore available;

    public Pool(Class<T> classObejct,int size){
        this.size = size;
        checkout = new boolean[size];
        available = new Semaphore(size,true);
        try {
            for(int i = 0;i < size;i++){
                items.add(classObejct.newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public T checkOut() throws InterruptedException {
        /**
         *从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
         */
        available.acquire();//阻塞并获得许可
        return getItem();
    }

    /**
     * 信号量封装所需的同步，以限制对池的访问，
     * 这同维持该池本身一致性所需的同步是分开的
     * @return
     */
    private synchronized T getItem(){
        for(int i = 0;i < size;i++){
            if(!checkout[i]){
                checkout[i] = true;
                return items.get(i);
            }
        }
        return null;
    }

    public void checkIn(T x){
        /**
         * 释放一个许可，将其返回给信号量。
         */
        if(releaseItem(x)){
            available.release();//相当于颁布许可证 无法保持同步锁，因为这会阻止将项返回到池中
        }
    }

    private synchronized boolean releaseItem(T item){
        int index = items.indexOf(item);
        if(index == -1){
            return false;
        }
        if(checkout[index]){
            checkout[index] = false;
            return true;
        }
        return false;
    }
}

/**
 * 测试对象
 */
class Fat{
    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat(){
        for(int i = 0;i < 10000;i++){
            d += (Math.PI + Math.E)/(double)i;
        }
    }

    public void operation(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Fat id: " + id;
    }
}

class CheckoutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + "check out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + "checking in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}

/**
 * test
 * semaphore信号量
 */
class SemaphoreDemo{
    final static int SIZE = 25;//池的初始大小

    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool = new Pool<Fat>(Fat.class,SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0;i < SIZE;i++){
            exec.execute(new CheckoutTask<Fat>(pool));
        }

        System.out.println("All checkoutTasks created");
        List<Fat> list = new ArrayList<Fat>();

        for(int i = 0;i < SIZE;i++){
            Fat f = pool.checkOut();
            System.out.println(i + "：main() thread checkout out ");
            f.operation();
            list.add(f);
        }

        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    pool.checkOut();
                }catch(InterruptedException e){
                    System.out.println("checkout() Interrupted");
                }
            }
        });

        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);//true如果应该中断执行此任务的线程
        System.out.println("checking in objects in "+ list);
        for(Fat f : list){
            pool.checkIn(f);
        }
        for(Fat f : list){//第二次被忽略
            pool.checkIn(f);
        }
        exec.shutdown();//所有任务都完成则中断
    }
}
