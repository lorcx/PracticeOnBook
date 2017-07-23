package think_in_java;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Explore : 探测
 * 反射 看  values()
 * Created by lx on 2015/12/20.
 */
public enum Explore {
    HRER,THERE
}

//reflection：反射
class Reflection {

    /**
     * 分解
     * @return
     */
    public static Set<String> analyze(Class<?> enumClass){
        System.out.println("-------" + enumClass + "-------");
        System.out.println("interface : ");
        for (Type t : enumClass.getGenericInterfaces()){//获取实现的接口
            System.out.println(t);
        }
        System.out.println("base" + enumClass.getSuperclass()); //获取父类
        System.out.println("method:");
        Set<String> method = new HashSet<String>();
        for (Method m : enumClass.getMethods()){  //获取方法
            method.add(m.getName());
        }
        System.out.println(method);
        return method;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("exploreMethods.containsAll(enumMethods) :\n" + exploreMethods.containsAll(enumMethods)); // 如果此 set 包含指定 collection 的所有元素，则返回 true。
        System.out.println("removeAll(enumMethods)");
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);
        OsExecute.command("javap Explore");


    }
}
