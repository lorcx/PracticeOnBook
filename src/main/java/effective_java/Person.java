package effective_java;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 第五条避免创建不必要的对象
 * Created by dell on 2016/4/7.
 */
// 低效的方式  每次都要创建好多实例
public class Person {
    private final Date birthDate;//出生日期

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * 判断出生日期是否在生育高峰期
     * @return
     */
    public boolean isBabyBoomer(){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.set(1946,Calendar.JANUARY,1,0,0,0);
        Date boomerStart = cal.getTime();
        cal.set(1976,Calendar.JANUARY,10,0,0,0);
        Date boomerEnd = cal.getTime();
        return birthDate.compareTo(boomerStart) >= 0 &&
                birthDate.compareTo(boomerEnd) < 0;
    }

}

//减少对类的创建
class Person1{
    private static final Date birthDate = null;//出生日期
    private static final Date BOOMER_START;
    private static final Date BOOMER_END;

    static{
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        cal.set(1946,Calendar.JANUARY,1,0,0,0);
        BOOMER_START = cal.getTime();
        cal.set(1976,Calendar.JANUARY,10,0,0,0);
        BOOMER_END = cal.getTime();
    }

    /**
     * 判断出生日期是否在生育高峰期
     * @return
     */
    public boolean isBabyBoomer(){
        return birthDate.compareTo(BOOMER_START) >= 0 &&
                birthDate.compareTo(BOOMER_END) < 0;
    }
}

