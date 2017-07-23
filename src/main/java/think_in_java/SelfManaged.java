package think_in_java;

/**
 * 20.2编码变体
 * selfManaged:自己管理
 * Created by dell on 2016/2/2.
 */
@SuppressWarnings("all")
public class SelfManaged implements Runnable {
    private int countDown = 5;
    private Thread th = new Thread(this);

    public SelfManaged() {
        th.start();
    }

    public String toString() {
        return th.currentThread().getName()+":"+countDown;
    }

    public void run() {
        while(true){
            System.out.println(this);
            if(--countDown == 0){
                return;
            }
        }
    }
}

class SelfManagedTest{
    public static void main(String[] args) {
        for(int i = 0;i < 5;i++){
            new SelfManaged();
        }
    }
}