package java_mult_thread_core_technology.part3.Producer_consumer.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
public class MyStack {
    private List list = new ArrayList<>();

    public synchronized void push() {
        try {
            while (list.size() == 1) {
                System.out.println("push sub " + Thread.currentThread().getName() + "  WAITING");
                this.wait();
            }
            list.add("anyThing=" + Math.random());
            this.notifyAll();
            System.out.println("push=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void pop() {
        try {
            while (list.size() == 0) {
                System.out.println("pop sub " + Thread.currentThread().getName() + "  WAITING");
                this.wait();
            }
            list.remove(0);
            this.notifyAll();
            System.out.println("pop=" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class P3 {
    private MyStack myStack;

    public P3(MyStack myStack) {
        this.myStack = myStack;
    }

    public void pushService() {
        myStack.push();
    }

}

class C3 {
    private MyStack myStack;

    public C3(MyStack myStack) {
        this.myStack = myStack;
    }

    public void popService() {
        myStack.pop();
    }
}

class Pthread extends Thread{
    private P3 p;

    public Pthread(P3 p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            p.pushService();
        }
    }
}

class Cthread extends Thread{
    private C3 p;

    public Cthread(C3 p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            p.popService();
        }
    }
}

/**
 * 一个生产对一个消费
 */
//class RunService {
//    public static void main(String[] args) {
//        MyStack m = new MyStack();
//        P3 p = new P3(m);
//        C3 c = new C3(m);
//        Pthread pt = new Pthread(p);
//        Cthread ct = new Cthread(c);
//        pt.start();
//        ct.start();
//    }
//}

/**
 * 一个生产多个消费
 * 解决假死 将notify 改成notify all
 */
//class RunService {
//
//    public static void main(String[] args) {
//        MyStack m = new MyStack();
//        P3 p = new P3(m);
//        C3 c = new C3(m);
//        C3 c2 = new C3(m);
//        C3 c3 = new C3(m);
//        C3 c4 = new C3(m);
//        C3 c5 = new C3(m);
//        Pthread pt = new Pthread(p);
//        Cthread ct = new Cthread(c);
//        Cthread ct2 = new Cthread(c2);
//        Cthread ct3 = new Cthread(c3);
//        Cthread ct4 = new Cthread(c4);
//        Cthread ct5 = new Cthread(c5);
//        pt.start();
//        ct.start();
//        ct2.start();
//        ct3.start();
//        ct4.start();
//        ct5.start();
//    }
//}

/**
 * 多个生产一个消费
 */
//class RunService {
//    public static void main(String[] args) {
//        MyStack m = new MyStack();
//        P3 p = new P3(m);
//        P3 p2 = new P3(m);
//        P3 p3 = new P3(m);
//        P3 p4 = new P3(m);
//        P3 p5 = new P3(m);
//        C3 c = new C3(m);
//        Pthread pt = new Pthread(p);
//        Pthread pt2 = new Pthread(p2);
//        Pthread pt3 = new Pthread(p3);
//        Pthread pt4 = new Pthread(p4);
//        Pthread pt5 = new Pthread(p5);
//        Cthread ct = new Cthread(c);
//        pt.start();
//        pt2.start();
//        pt3.start();
//        pt4.start();
//        pt5.start();
//        ct.start();
//    }
//}


/**
 * 多个生产多个消费
 */
class RunService {
    public static void main(String[] args) {
        MyStack m = new MyStack();
        P3 p = new P3(m);
        P3 p2 = new P3(m);
        P3 p3 = new P3(m);
        P3 p4 = new P3(m);
        P3 p5 = new P3(m);
        C3 c = new C3(m);
        C3 c2 = new C3(m);
        C3 c3 = new C3(m);
        C3 c4 = new C3(m);
        C3 c5 = new C3(m);
        Pthread pt = new Pthread(p);
        Pthread pt2 = new Pthread(p2);
        Pthread pt3 = new Pthread(p3);
        Pthread pt4 = new Pthread(p4);
        Pthread pt5 = new Pthread(p5);
        Cthread ct = new Cthread(c);
        Cthread ct2 = new Cthread(c2);
        Cthread ct3 = new Cthread(c3);
        Cthread ct4 = new Cthread(c4);
        Cthread ct5 = new Cthread(c5);
        pt.start();
        pt2.start();
        pt3.start();
        pt4.start();
        pt5.start();
        ct.start();
        ct2.start();
        ct3.start();
        ct4.start();
        ct5.start();
    }
}