package think_in_java;

/**
 * 简单的javabean用来使用自定义的 生成数据库的注解
 * Created by lx on 2015/12/24.
 */
@SuppressWarnings("all")
@DBTable(name = "MEMBER")
public class Member {

    @SQLString(30)
    String firstName;
   
    @SQLString(30)
    String lastName;
   
    @SQLString
    int age;
    
    @SQLString(value = 30,constraints = @Constraints(primaryKey = true))//嵌套注解
            String handle;
    static int memberCount;

    //方法
    public String getHandle() { return handle; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String toString() { return handle; }
    public Integer getAge() { return age; }
}
