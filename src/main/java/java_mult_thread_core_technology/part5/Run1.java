package java_mult_thread_core_technology.part5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by no_one on 2017/9/4.
 */
public class Run1 {
    private static Timer timer = new Timer();

    public static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("运行时间：" + new Date());
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2017-9-04 11:18:00";
        try {
            Date dateRef = sdf.parse(dateString);
            System.out.println("字符串时间：" + dateRef.toLocaleString() + " 当前时间：" + new Date().toLocaleString());
            // 超过dateRef时间 执行 立即执行 ，没有超过则在改时间到达后执行
            //timer.schedule(myTask, dateRef);
            // 间隔4s执行一次
            //timer.schedule(myTask, dateRef, 4000);
            // 当前时间+3s 后运行 间隔4s执行一次
            timer.schedule(myTask, 3000, 4000);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}


