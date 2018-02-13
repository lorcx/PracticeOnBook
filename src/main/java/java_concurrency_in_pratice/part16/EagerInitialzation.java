package java_concurrency_in_pratice.part16;

import java_concurrency_in_pratice.anno.ThreadSafe;

/**
 * @Author lx
 * @Date 2018/2/13 17:35
 */
@ThreadSafe
public class EagerInitialzation {
    // 静态初始化 jvm 会保证加锁，不用担心线程安全
    private static Resource resource = new Resource();

    public static Resource getInstance() {
        return resource;
    }

    static class Resource {}
}
