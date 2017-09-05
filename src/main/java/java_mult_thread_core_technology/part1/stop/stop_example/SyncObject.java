package java_mult_thread_core_technology.part1.stop.stop_example;

/**
 * Created by Administrator on 2017/8/16.
 */
public class SyncObject {
    private String username = "a";
    private String password = "aa";

    public synchronized void printString(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(100000);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class MyThread5 extends Thread {
    private SyncObject object;

    public MyThread5(SyncObject object) {
        this.object = object;
    }

    @Override
    public void run() {
        object.printString("b", "bbb");
    }
}

class Run {
    public static void main(String[] args) {
        try {
            SyncObject object = new SyncObject();
            MyThread5 m5 = new MyThread5(object);
            m5.start();
            Thread.sleep(500);
            m5.stop();
            System.out.println(object.getUsername() + " " + object.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}