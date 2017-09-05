package java_mult_thread_core_technology.part3.Producer_consumer.multVmult;

/**
 * 多个生产者对多个消费者
 * 用notifyALL通知所有
 * Created by Administrator on 2017/8/28.
 */
public class P1 {
    private String lock;

    public P1(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                while (!ValueObject1.value.equals("")) {
                    System.out.println("生产者" + Thread.currentThread().getName() + " WAITING了*");
                    lock.wait();//等待消费者
                }
                System.out.println("生产者" + Thread.currentThread().getName() + " RUNNABLE了*");
                String v = System.currentTimeMillis() + "_" + System.nanoTime();
                ValueObject1.value = v;
//                lock.notify();//通知消费者
                lock.notifyAll();// 不光唤醒消费者也唤醒 生产者 防止假死
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class C1 {
    private String lock;

    public C1(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObject1.value.equals("")) {
                    System.out.println("消费者" + Thread.currentThread().getName() + " WAITING了*");
                    lock.wait();//等待生产者
                }
                System.out.println("消费者" + Thread.currentThread().getName() + " RUNNABLE了*");
                ValueObject1.value = "";
//                lock.notify();// 通知生产者
                lock.notifyAll();// 不光唤醒消费者也唤醒 生产者 防止假死
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ValueObject1 {
    public static String value = "";
}

class ThreadP1  extends Thread {
    private P1 p;

    public ThreadP1(P1 p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            p.setValue();
        }
    }
}

class ThreadC1  extends Thread {
    private C1 c;

    public ThreadC1(C1 c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            c.getValue();
        }
    }
}

class RunPc1 {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        P1 p = new P1(lock);
        C1 c = new C1(lock);
        Thread[] pThread = new ThreadP1[2];
        Thread[] cThread = new ThreadC1[2];
        for (int i = 0; i < 2; i++) {
            pThread[i] = new ThreadP1(p);
            pThread[i].setName("生产者" + (i + 1));
            cThread[i] = new ThreadC1(c);
            cThread[i].setName("消费者" + (i + 1));
            pThread[i].start();
            cThread[i].start();
        }
        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        //把此线程组及其子组中的所有活动线程复制到指定数组中。
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for (int i = 0; i < threadArray.length; i++) {
            System.out.println(threadArray[i].getName() + " " + threadArray[i].getState());
        }
    }
}