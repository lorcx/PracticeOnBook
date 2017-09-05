package java_mult_thread_core_technology.part2.dead;

/**
 * Created by Administrator on 2017/8/22.
 */
public class DeadLock implements Runnable {
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();

    public void setFlag(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try {
            if ("a".equals(username)) {
                synchronized (lock1) {
                    System.out.println("username=" + username);
                    Thread.sleep(3000);

                    synchronized (lock2) {
                        System.out.println("lock1 -> lock2 顺序执行代码");
                    }
                }
            }

            if ("b".equals(username)) {
                synchronized (lock2) {
                    System.out.println("username=" + username);
                    Thread.sleep(3000);

                    synchronized (lock1) {
                        System.out.println("lock2 -> lock1 顺序执行代码");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Run15 {
    public static void main(String[] args) throws InterruptedException {
        DeadLock s = new DeadLock();
        s.setFlag("a");
        Thread t1 = new Thread(s);
        t1.start();
        Thread.sleep(100);
        s.setFlag("b");
        Thread t2 = new Thread(s);
        t2.start();
    }
}

