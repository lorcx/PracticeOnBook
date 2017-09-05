package java_mult_thread_core_technology.part6.l;

/**
 * Created by no_one on 2017/9/4.
 */
public class MyObject {
    public static MyObject obj;

    private MyObject() {
    }

    /**
     * synchronized方法 （效率低）
     */
  /*  public synchronized static MyObject getInstance() {
        if (obj == null) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            obj = new MyObject();
        }
        return obj;
    }*/

    /**
     * synchronized 块（效率低）
     *
     * @return
     */
  /*  public static MyObject getInstance() {

        try {
            synchronized (MyObject.class) {
                if (obj == null) {
                    Thread.sleep(3000);
                    obj = new MyObject();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return obj;
    } */

    /**
     * synchronized 块对需要同步的代码加锁 （效果比前两个好，但无法保持单例）
     * @return
     */
  /*  public static MyObject getInstance() {
        try {
            if (obj == null) {
                Thread.sleep(3000);
                synchronized (MyObject.class) {
                    obj = new MyObject();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return obj;
    }  */

    /**
     * 使用dlc双检查
     * @return
     */
    public static MyObject getInstance() {
        try {
            if (obj == null) {
                Thread.sleep(3000);// 初始化
                synchronized (MyObject.class) {
                    if (obj == null) {
                        obj = new MyObject();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(MyObject.getInstance().hashCode());
    }
}

class Run {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
