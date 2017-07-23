package think_in_java;

import java.util.concurrent.TimeUnit;

/**
 * Created by lx on 2016/1/17.
 */
@SuppressWarnings("all")
public class SimpleDaemons implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0;i < 10;i++ ){
            Thread th = new Thread(new SimpleDaemons());
            th.setDaemon(true);
            th.start();
        }
        System.out.println("全部后台线程启动完成 ！");
        TimeUnit.MILLISECONDS.sleep(1750); //睡眠main线程 ，main是非守护线程，他结束程序就结束
    }

    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println(Thread.currentThread() + " : " + this);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }

        }
    }
}
