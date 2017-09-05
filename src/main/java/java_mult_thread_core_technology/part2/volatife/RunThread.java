package java_mult_thread_core_technology.part2.volatife;

/**
 * Created by Administrator on 2017/8/23.
 */
public class RunThread extends Thread{
    private boolean isRunning = true;
    // volatile 保证内存可见性
//    private volatile boolean isRunning = true;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("进入run了");
        while (isRunning) {

        }
        System.out.println("线程停止了！");
    }
}

class Run20 {
    public static void main(String[] args) {
        try {
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(1000);
            thread.setRunning(false);
            System.out.println("已经赋值为false");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


