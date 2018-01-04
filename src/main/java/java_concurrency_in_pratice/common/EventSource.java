package java_concurrency_in_pratice.common;

import java_concurrency_in_pratice.part3.ThisEscape;

/**
 * @Author: lx
 * @Date: Created in 2018/1/4 0004
 */
public interface EventSource {
    void registerLinstener(EventListener listener);
}
