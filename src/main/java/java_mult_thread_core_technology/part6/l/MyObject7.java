package java_mult_thread_core_technology.part6.l;

/**
 * Created by no_one on 2017/9/4.
 */
public class MyObject7 {

    public enum MyEnumSignleton {
        ConnectionFactory;

        private Object obj;

        private MyEnumSignleton() {
            try {
                System.out.println("调用构造");
                obj = new Object();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Object getObj() {
            return obj;
        }
    }

    /**
     * 为了符合srp
     * @return
     */
    public static Object getObjInstance() {
        return MyEnumSignleton.ConnectionFactory.getObj();
    }
}

class MyThread6 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(MyObject7.getObjInstance());
        }
    }
}

class Run6 {
    public static void main(String[] args) {
        MyThread6 m1 = new MyThread6();
        MyThread6 m2 = new MyThread6();
        MyThread6 m3 = new MyThread6();

        m1.start();
        m2.start();
        m3.start();
    }
}
