package think_in_java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于追踪项目的用例
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id();
    public String desscription() default "no description";
}
