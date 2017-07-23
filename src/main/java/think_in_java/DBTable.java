package think_in_java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 告诉编辑器用来生成数据库表
 * Created by lx on 2015/12/24.
 */
@SuppressWarnings("all")
@Target(ElementType.TYPE)//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时都有
public @interface DBTable {
    public String name() default "";
}


/**
 *
 * 用来修饰javaBean域的注解
 *
 */
@SuppressWarnings("all")
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Constraints {
    boolean primaryKey() default false;
    boolean allowNull() default true;
    boolean unique() default false;
}

/**
 * sql注解
 */
@SuppressWarnings("all")
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLString{
    int value() default 0;
    String name()default "";
    Constraints constraints()default @Constraints;
}

@SuppressWarnings("all")
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLInteger{
    String name()default "";
    Constraints constraints()default @Constraints;
}


/**
 * 如果需要unique返回的元素为true 并是constraint的元素
 */
@SuppressWarnings("all")
@interface Uniqueness{
    Constraints constraints() default @Constraints(unique = true);
}
