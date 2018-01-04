package java_concurrency_in_pratice.part3;

import java_concurrency_in_pratice.common.EventListener;
import java_concurrency_in_pratice.common.EventSource;

import java.awt.*;

/**
 * @Author: lx
 * @Date: Created in 2018/1/4 0004
 */
public class SafeListener {
    private final EventListener listener;

    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }

            private void doSomething(Event e) {
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerLinstener(safe.listener);
        return safe;
    }

}
