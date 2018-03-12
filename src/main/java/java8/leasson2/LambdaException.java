package java8.leasson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;

/**
 * lambda 中异常
 * 任何函数式接口不允许抛出受检查异常
 *
 * @Author lx
 * @Date 2018/2/20 19:57
 */
public class LambdaException {
    public static void main(String[] args) {
        Function<BufferedReader, String> f = (BufferedReader b) -> {
            try {
                return b.readLine();
            } catch (IOException e) {
//                throw new Exception(); // 编译错误
                throw new RuntimeException();
            }
        };
    }
}
