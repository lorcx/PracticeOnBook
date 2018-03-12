package java_concurrency_in_pratice.part16;

import java_concurrency_in_pratice.anno.ThreadSafe;

/**
 * @Author lx
 * @Date 2018/2/13 17:33
 */
@ThreadSafe
public class SafeLazyIntialization {
    private static Resource resource;

    public synchronized static Resource getInstance() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }

    static class Resource{}
}
