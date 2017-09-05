package java_mult_thread_core_technology.part3.wait_change;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
public class Add {
    private String lock;

    public Add(String lock) {
        this.lock = lock;
    }

    public void add() {
        synchronized (lock) {
            ValueObject.list.add("anyString");
            lock.notifyAll();
        }
    }
}

class Subtract {
    private String lock;

    public Subtract(String lock) {
        this.lock = lock;
    }

    public void subtract() {
        try {
            synchronized (lock) {
                while (ValueObject.list.size() == 0) {
                    System.out.println("wait begin ThreadName=" + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end ThreadName=" + Thread.currentThread().getName());
                }
                ValueObject.list.remove(0);
                System.out.println("list size = " + ValueObject.list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ValueObject {
    public static List list = new ArrayList<>();
}

class ThreadAdd extends Thread{
    private Add add;

    public ThreadAdd(Add add) {
        this.add = add;
    }

    @Override
    public void run() {
        add.add();
    }
}

class ThreadSubtract extends Thread{
    private Subtract subtract;

    public ThreadSubtract(Subtract subtract) {
        this.subtract = subtract;
    }

    @Override
    public void run() {
        subtract.subtract();
    }
}

class Run33 {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        ThreadSubtract subThread = new ThreadSubtract(subtract);
        subThread.setName("subtract1 sub");
        subThread.start();

        ThreadSubtract subThread2 = new ThreadSubtract(subtract);
        subThread2.setName("subtract2 sub");
        subThread2.start();

        Thread.sleep(1000);

        ThreadAdd addThread = new ThreadAdd(add);
        addThread.setName("addThread");
        addThread.start();
    }
}
