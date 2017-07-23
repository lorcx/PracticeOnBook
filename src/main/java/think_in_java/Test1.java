package think_in_java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义注解
 * 没有元素的注解是标记注解
 * Created by lx on 2015/12/22.
 */
@SuppressWarnings("all")
@Target(ElementType.METHOD)//用于定义注解用在什么地方
@Retention(RetentionPolicy.RUNTIME) //Retention 保留 记忆 用于定义在那一个级别可用
public @interface Test1 {

}
