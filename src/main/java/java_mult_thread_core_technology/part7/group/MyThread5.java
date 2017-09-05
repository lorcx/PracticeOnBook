package java_mult_thread_core_technology.part7.group;

import java_mult_thread_core_technology.part3.thread_local.sub.InheritableThreadLocalExt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by no_one on 2017/9/5.
 */
public class MyThread5 extends Thread {
    private SimpleDateFormat sdf;
    private String dateString;

    public MyThread5(SimpleDateFormat sdf, String dateString) {
        this.sdf = sdf;
        this.dateString = dateString;
    }

    @Override
    public void run() {
        try {
            Date dateRef = DateTools.getSimpleDateFormat("yyyy-MM-dd").parse(dateString);
            String newDateString = DateTools.getSimpleDateFormat("yyyy-MM-dd").format(dateRef).toString();
            if (!newDateString.equals(dateString)) {
                System.out.println("ThreadName=" + this.getName() + "日期不对");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

class DateTools {
    private static ThreadLocal<SimpleDateFormat> t1 = new InheritableThreadLocalExt();
    public static SimpleDateFormat getSimpleDateFormat(String fmt) {
        SimpleDateFormat sdf = null;
        sdf = t1.get();
        if (t1 == null) {
            sdf = new SimpleDateFormat(fmt);
            t1.set(sdf);
        }
        return sdf;
    }
}