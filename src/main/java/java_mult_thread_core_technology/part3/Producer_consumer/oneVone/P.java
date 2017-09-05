package java_mult_thread_core_technology.part3.Producer_consumer.oneVone;

/**
 * 一个生产者对一个消费者
 * Created by Administrator on 2017/8/28.
 */
public class P {
    private String lock;

    public P(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                if (!ValueObject.value.equals("")) {
                    lock.wait();//等待消费者
                }
                String v = System.currentTimeMillis() + "_" + System.nanoTime();
                System.out.println("set value = " + v);
                ValueObject.value = v;
                lock.notify();//通知消费者
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class C {
    private String lock;

    public C(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if (ValueObject.value.equals("")) {
                    lock.wait();//等待生产者
                }
                System.out.println("get value = " + ValueObject.value);
                ValueObject.value = "";
                lock.notify();//通知生产者
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ValueObject {
    public static String value = "";
}

class ThreadP  extends Thread {
    private P p;

    public ThreadP(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            p.setValue();
        }
    }
}

class ThreadC  extends Thread {
    private C c;

    public ThreadC(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true) {
            c.getValue();
        }
    }
}

class RunPc {
    public static void main(String[] args) {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadP tp = new ThreadP(p);
        ThreadC tc = new ThreadC(c);
        tp.start();
        tc.start();
    }
}