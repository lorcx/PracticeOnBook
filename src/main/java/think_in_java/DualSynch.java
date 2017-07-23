package think_in_java;

/**
 * 在另一个对象上同步
 * Created by lx on 2016/3/19.
 */
@SuppressWarnings("all")
public class DualSynch {
    private Object syncObject = new Object();

    public synchronized void f(){
        for(int i = 0;i < 5;i++){
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g(){
        synchronized (syncObject){ //同步外部对象
            for(int i = 0;i < 5;i++){
                System.out.println("g()");
                Thread.yield();
            }
        }
    }


}

class SyncObject{
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();

        new Thread(){

            @Override
            public void run() {
                ds.f();
            }

        }.start();

        ds.g();
    }
}