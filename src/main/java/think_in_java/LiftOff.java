package think_in_java;

/**
 * liftoff发射
 * Created by lx on 2015/12/24.
 */
@SuppressWarnings("all")
public class LiftOff implements Runnable {

    protected int countDown = 10;//倒计时数
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    @Override
    public void run() {
        while(countDown -- > 0){
            System.out.println(status());
            Thread.yield(); //暂停当前正在执行的线程对象，并执行其他线程。
        }

    }

    public String status(){
        return "#" + id + "(" + (countDown > 0 ? countDown : "liftOff !" ) + ")";
    }
}

class liftOffTest{
    public static void main(String[] args) {
        LiftOff l = new LiftOff();
        Thread t  = new Thread(l);
        t.start();
    }
}

/**
 * 多个线程启动
 */
class MorBasicThreads{
    public static void main(String[] args) {
        for (int i = 0;i < 5;i++){
            new Thread(new LiftOff()).start();
            System.out.println("waiting for liftoff");
        }
    }
}