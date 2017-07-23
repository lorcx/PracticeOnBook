package think_in_java;

import java.util.concurrent.TimeUnit;

/**
 * 使用内部类 thread
 * Created by dell on 2016/2/2.
 */
@SuppressWarnings("all")
public class InnerThread1 {
    private int countDown = 5;
    private Inner1 iner1;
    private class Inner1 extends Thread {

        Inner1(String name){
            super(name);
            start();
        }

        public void run() {
            while(true){
                try {
                    System.out.println(this);
                    if(--countDown == 0){
                        return;
                    }
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public String toString() {
            return "#" + getName() + ":" + countDown;//getName获取当前线程名称
        }
    }

    public InnerThread1(String name) {
        this.iner1 = new Inner1(name);
    }
}

//在构造方法中使用匿名内部类
@SuppressWarnings("all")
class InnerThread2{
    private int countDown = 5;
    private Thread thread;

    public InnerThread2(String name) {
        this.thread = new Thread(name){

            public void run() {
                while(true){
                    try {
                        System.out.println(this);
                        if(--countDown == 0){
                            return;
                        }
                        sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            public String toString() {
                return "#" + getName() + ":" + countDown;//getName获取当前线程名称
            }
        };

        thread.start();
    }
}

//runable
@SuppressWarnings("all")
class InnerThread3{
    private int countDown = 5;
    private Inner2 iner2;
    private class Inner2 implements Runnable {
        Thread th;
        Inner2(String name){
            th = new Thread(this,name);
            th.start();
        }

        public void run() {
            while(true){
                try {
                    System.out.println(this);
                    if(--countDown == 0){
                        return;
                    }
                    TimeUnit.MILLISECONDS.sleep(10);//thread中才有sleep
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }
        }

        public String toString() {
            return "#" + th.getName() + ":" + countDown;//getName获取当前线程名称
        }
    }

    public InnerThread3(String name) {
        this.iner2 = new Inner2(name);
    }
}

//runable2
@SuppressWarnings("all")
class InnerThread4{
    private int countDown = 5;
    private Thread thread;

    public InnerThread4(String name) {
        this.thread = new Thread(new Runnable() {

            public void run(){
                while(true){
                    try {
                        System.out.println(this);
                        if(--countDown == 0){
                            return;
                        }
                        TimeUnit.MILLISECONDS.sleep(10);//thread中才有sleep
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            public String toString() {
                return "#" + thread.getName() + ":" + countDown;//getName获取当前线程名称
            }
        }, name);

        thread.start();
    }
}

//不是主要任务 复制任务 不再构造方法中启动
@SuppressWarnings("all")
class ThreadMethod{
    private int countDown = 5;
    private Thread thread;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask(){
        if(thread == null){
            thread = new Thread(new Runnable() {

                public void run() {
                    while(true){
                        try {
                            System.out.println(this);
                            if(--countDown == 0){
                                return;
                            }
                            TimeUnit.MILLISECONDS.sleep(10);//thread中才有sleep
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public String toString() {
                    return "#" + thread.getName() + ":" + countDown;//getName获取当前线程名称
                }
            }, name);

            thread.start();
        }
    }
}

/**
 * varients变体
 */
class ThreadVarients{
    public static void main(String[] args) {
        new InnerThread1("th1");
        new InnerThread2("th2");
        new InnerThread3("th3");
        new InnerThread4("th4");
        new ThreadMethod("th5").runTask();
    }
}
