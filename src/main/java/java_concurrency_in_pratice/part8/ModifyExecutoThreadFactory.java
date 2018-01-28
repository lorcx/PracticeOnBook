package java_concurrency_in_pratice.part8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 修改已经创建好的ThradPoolFactory
 *
 * @Author lx
 * @Date 2018/1/28 15:54
 */
public class ModifyExecutoThreadFactory {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        if (exec instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor)exec).setCorePoolSize(10);
        } else {
            throw new  AssertionError("Oops, Bad assumption");
        }
    }
}
