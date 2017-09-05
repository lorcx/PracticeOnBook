package java_mult_thread_core_technology.part5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by no_one on 2017/9/4.
 */
public class Run2 {
    private static Timer timer = new Timer();

    public static class MyTask1 extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行 时间为" + new Date());
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MyTask2 extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行 时间为" + new Date());
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTask1 task = new MyTask1();
        MyTask2 task2 = new MyTask2();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2017-9-04 11:32:00";
        String dateString2 = "2017-9-04 11:32:10";
        Date dateRef = sdf.parse(dateString);
        Date dateRef2 = sdf.parse(dateString2);
        System.out.println("字符串1时间" + dateRef.toLocaleString() + "当前时间" + new Date().toLocaleString());
        System.out.println("字符串2时间" + dateRef2.toLocaleString() + "当前时间" + new Date().toLocaleString());
        timer.schedule(task, dateRef);
        timer.schedule(task2, dateRef2);
    }
}
