package think_in_java;

/**
 * 20.2编码的变体
 * Created by dell on 2016/2/2.
 */
@SuppressWarnings("unchecked")
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int count = 0;//记录线程数

    public SimpleThread() {
       super(Integer.toString(++count));//分配新的 Thread 对象
       start();//启动线程
    }

    public String toString() {
        return "#" + getName() + ":" + countDown;//getName获取当前线程名称
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

class SimpleThreadTest{
    public static void main(String[] args) {
        for(int i = 0;i < 5;i++){
            new SimpleThread();
        }

    }
}
