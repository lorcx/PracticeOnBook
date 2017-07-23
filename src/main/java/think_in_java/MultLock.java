package think_in_java;

/**
 * 被互斥所阻塞
 * Created by dell on 2016/3/23.
 */
@SuppressWarnings("all")
public class MultLock {
    public synchronized void f1(int count){
        if(count -- > 0){
            System.out.println("f1() call f2() count: " + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if(count -- > 0){
            System.out.println("f2() call f1() count: " + count);
            f1(count);
        }
    }

    public static void main(String[] args) {
        final MultLock ml = new MultLock();
        new Thread(){
            @Override
            public void run() {
                ml.f1(10);
            }
        }.start();
    }
}
