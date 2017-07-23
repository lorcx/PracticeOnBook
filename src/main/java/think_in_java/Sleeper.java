package think_in_java;

/**
 * 加入一个线程
 * Created by dell on 2016/2/3.
 */
@SuppressWarnings("all")
public class Sleeper extends Thread {
    private int duration;//睡眠时间

    public Sleeper(String name, int sleepTime) {
        super(name);
        this.duration = sleepTime;
        start();
    }

    public void run() {
        try {
            sleep(duration);//睡眠
        } catch (InterruptedException e) {
            System.out.println(getName() + ": was interrupt " + isInterrupted()); //isInterrupted 总是返回false
        }
        System.out.println(getName() + ": has awakened");//awakened唤醒
    }
}

@SuppressWarnings("all")
class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join();//线程挂起 等待该线程终止
        } catch (InterruptedException e) {
            System.out.println("interrupt");
        }

        System.out.println(getName() + " : join complete!");
    }
}

class Joining{
    public static void main(String[] args) {
        Sleeper grumpy = new Sleeper("s2",1500);
        Sleeper sleepy = new Sleeper("s1",1500);
        Joiner dopey = new Joiner("j1",sleepy);
        Joiner doc = new Joiner("j1",grumpy);

        grumpy.interrupt();//中断线程


    }
}