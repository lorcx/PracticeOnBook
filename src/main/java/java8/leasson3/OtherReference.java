package java8.leasson3;

import java.util.function.Function;

/**
 * 数组引用
 *
 * @Author lx
 * @Date 2018/2/20 20:22
 */
public class OtherReference {
    public static void main(String[] args) {
        Function<Integer, String[]> fun = x -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        // 写法比上边更简化
        Function<Integer, String[]> fun1 = String[]::new;
        strs = fun1.apply(20);
        System.out.println(strs.length);

    }
}
