package think_in_java;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * 有三个任务
 * 制作土司
 * 给土司涂黄油
 * 给涂了黄油的土司涂果酱
 * Created by lx on 2016/3/26.
 */
public class ToastMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),//制作土司
                   butteredQueue = new ToastQueue(),//给土司涂黄油
                   finishedQueue = new ToastQueue();//给涂了黄油的土司涂果酱

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue,butteredQueue));
        exec.execute(new Jammer(butteredQueue,finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}


class Toast {
    private final int id;
    private static Status status = Status.DRY;

    public enum Status{
        DRY,BUTTERED,JAMMED
    }

    public Toast(int id) {
        this.id = id;
    }

    public void butter(){
        status = Status.BUTTERED;
    }

    public void jam(){
        status = Status.JAMMED;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);

    public Toaster(ToastQueue toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }

        System.out.println("ToastQueue off");
    }
}

class Butterer implements Runnable {
    private ToastQueue dryQueue,butteredQueue;

    public Butterer(ToastQueue toastQueue, ToastQueue butteredQueue) {
        this.dryQueue = toastQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }

        System.out.println("Butterer off");
        
    }
}

class Jammer implements Runnable {
    private ToastQueue butteredQueue,finishedQueue;

    public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }

        System.out.println("Jammer off");

    }
}

class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int counter = 0;
    
    public Eater(ToastQueue finishedQueue) {
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Toast t = finishedQueue.take();
                if(t.getId() != counter++ || t.getStatus() != Toast.Status.JAMMED){
                    System.out.println(">>>>>error:" + t);
                    System.exit(1);
                }else{
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }

        System.out.println("Eater off");

    }
}