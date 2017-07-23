package think_in_java;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * tracker ：追踪
 * Created by lx on 2015/12/24.
 */
public class UseCaseTracker {


    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<Integer>();
        Collections.addAll(useCases, 1, 2, 3);
        trackerUserCase(useCases,PasswordUtils.class);
    }

    /**
     * 用例追踪
     * 反射获取注解中的值
     * @param useCases
     * @param cl
     */
    private static void trackerUserCase(List<Integer> useCases, Class cl) {

        for (Method m : cl.getDeclaredMethods()){
            UseCase u = m.getAnnotation(UseCase.class);
            if(u != null){
                System.out.println("id : " + u.id());
                System.out.println("declared : " + u.desscription());
                useCases.remove(new Integer(u.id()));
            }
        }

        for (Integer in : useCases){
            System.out.println(in);
        }

    }

}
