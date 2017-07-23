package think_in_java;

/**
 * 简单的个管理枚举的方法
 * 枚举嵌套枚举
 *security 安全
 * category种类
 * Created by lx on 2015/12/20.
 */
@SuppressWarnings("all")
public enum SecurityCategoy {

    STOCK(Security.Stock.class),
    BOND(Security.Bond.class);

    private Security[] values;

    //构造方法跟stock(xxx)有关
    private SecurityCategoy(Class<? extends Security> c) {
        this.values = c.getEnumConstants();
    }

    public  Security randomSelect(){
        return Enums.random(values);
    }

    //内部枚举
    interface Security{
        //stock股份
        enum Stock implements Security{
            SHORT,LONG,MARGIN //利润
        }

        //Bond债券
        enum Bond implements Security{
            MUNICIPAL,JUNK//市政    垃圾
        }
    }

    public static void main(String[] args) {
        for (int i = 0;i < 10;i++){
            SecurityCategoy sc = Enums.random(SecurityCategoy.class);
            System.out.println(sc.randomSelect());
        }
    }
}
