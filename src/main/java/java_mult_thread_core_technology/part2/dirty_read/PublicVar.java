package java_mult_thread_core_technology.part2.dirty_read;

/**
 * 脏读
 * Created by Administrator on 2017/8/18.
 */
public class PublicVar {
    private String username = "A";
    private String password = "AA";

    public synchronized void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method sub name= " + Thread.currentThread().getName() + " username=" + username + " password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读方法没有同步，会产生脏读
     * synchronized
     */
    public void getValue() {
        System.out.println("getValue method sub name= " + Thread.currentThread().getName() + " username=" + username + " password=" + password);
    }
}

class Thread11 extends Thread{
    private PublicVar publicVar;

    public Thread11(PublicVar pv) {
        this.publicVar = pv;
    }

    @Override
    public void run() {
        publicVar.setValue("qwer","bb");
    }
}

class Run11{
    public static void main(String[] args) {
        try {
            PublicVar pv = new PublicVar();
            Thread t11 = new Thread11(pv);
            t11.start();
            Thread.sleep(200);
            pv.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}