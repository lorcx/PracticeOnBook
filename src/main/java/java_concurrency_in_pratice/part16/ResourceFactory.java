package java_concurrency_in_pratice.part16;

import java_concurrency_in_pratice.anno.ThreadSafe;

/**
 * 延迟加载
 * 利用jvm静态初始化保证线程安全
 * @Author lx
 * @Date 2018/2/13 17:36
 */
@ThreadSafe
public class ResourceFactory {
    private static class ResourceHolder {
        static Resource resource = new Resource();
    }

    public static Resource getInstance() {
        return ResourceHolder.resource;
    }

    static class Resource {

    }

}
