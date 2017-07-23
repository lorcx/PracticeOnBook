package think_in_java;

import java.util.List;

/**
 * 注解实例
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
public class PasswordUtils {

    @UseCase(id = 1,desscription = "validate pwd")
    public boolean validatePassword(String pwd){
        return  pwd.matches("\\w*\\d\\w");
    }

    @UseCase(id = 2)
    public String encryptPassword(String pwd){
        return new StringBuilder(pwd).reverse().toString();
    }

    @UseCase(id = 3,desscription = "checkNewPassword")
    public boolean checkNewPassword(List<String> pwds,String pwd){
        return !pwds.contains(pwd);
    }
}
