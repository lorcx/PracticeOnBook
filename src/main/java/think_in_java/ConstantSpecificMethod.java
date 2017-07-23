package think_in_java;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 枚举方法
 * Created by lx on 2015/12/20.
 */
@SuppressWarnings("all")
public enum ConstantSpecificMethod {

    DATA_TIME{
        @Override
        String getInfo() {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
              return (sf.format(new Date())).toString();
        }
    },

    CLASSPATH{
        @Override
        String getInfo() {
            return System.getenv("CLASSPATH");
        }
    },

    VERSION{
        @Override
        String getInfo() {
            return System.getProperty("java.version");
        }
    };

    abstract String getInfo();

    public static void main(String[] args) {
        for (ConstantSpecificMethod c : values()){
            System.out.println(c.getInfo());
        }
    }
}
