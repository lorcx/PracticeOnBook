package think_in_java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解中的默认元素不能是null
 * retention保留
 * Created by lx on 2015/12/24.
 */
@SuppressWarnings("all")
@Target(ElementType.METHOD)//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时都有
public @interface SimulatingNull {
    public int id() default -1;
    public String msg() default "";
}
