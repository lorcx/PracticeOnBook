package think_in_java;

import java.util.concurrent.TimeUnit;

/**
 * 中断检查
 * Created by dell on 2016/3/24.
 */
public class NeedCleanup {
    private final int id;

    public NeedCleanup(int id) {
        this.id = id;
        System.out.println("NeedCleanup " + id);
    }

    public void cleanup(){
        System.out.println("cleanup " + id);
    }
}

class Blocks3 implements Runnable {
    private volatile double d = 0.0d;
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){//没被中断
                NeedCleanup n1 = new NeedCleanup(1);
                System.out.println("sleeping");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    NeedCleanup n2 = new NeedCleanup(2);
                    try {
                        System.out.println("calculating");
                        for(int i = 0;i < 250000;i++){
                           d = d + (Math.PI + Math.E )/ d;
                        }
                        System.out.println("finish time-consuming operation");
                    } finally {
                        n2.cleanup();
                    }
                }finally{
                    n1.cleanup();
                }

            }
            System.out.println("exit Blocks3.run()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Interrupting{
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocks3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(1100);
        t.interrupt();
    }
}

