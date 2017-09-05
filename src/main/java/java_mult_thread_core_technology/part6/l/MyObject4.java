package java_mult_thread_core_technology.part6.l;



/**
 * Created by no_one on 2017/9/4.
 */
public enum MyObject4 {
    ConnectionFactory;

//    private Connection connection;

    private Object obj;

    private MyObject4() {
        try {
            System.out.println("调用构造");
//            String url = "";
//            String userName = "";
//            String password = "";
//            String driverName = "";
//            Class.forName(driverName);
//            connection = DriverManager.getConnection(url, userName, password);
            obj = new Object();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getObj() {
        return obj;
    }

    //    public Connection getConnection() {
//        return connection;
//    }
}

class MyThread5 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(MyObject4.ConnectionFactory.getObj().hashCode());
        }
    }
}

class Run5 {
    public static void main(String[] args) {
        MyThread5 m1 = new MyThread5();
        MyThread5 m2 = new MyThread5();
        MyThread5 m3 = new MyThread5();

        m1.start();
//        m2.start();
//        m3.start();
    }
}
