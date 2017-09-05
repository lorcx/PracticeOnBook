package java_mult_thread_core_technology.part5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by no_one on 2017/9/4.
 */
public class Run3  {
    private static Timer timer = new Timer();
    private static int runCount = 0;

    public static class MyTask1 extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("i begin 运行了 时间为：" + new Date());
//                Thread.sleep(1000);
                Thread.sleep(2000);
                System.out.println("i end 运行了 时间为：" + new Date());
                runCount++;
                if (runCount == 5) {
                    timer.cancel();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        MyTask1 task1 = new MyTask1();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString1 = "2014-10-12 14:27:00";
        Date dateRef1 = sdf.parse(dateString1);
        System.out.println("字符串1时间：" + dateRef1.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
//        timer.schedule(task1, dateRef1, 3000);
        timer.scheduleAtFixedRate(task1, dateRef1, 3000);
    }
}
