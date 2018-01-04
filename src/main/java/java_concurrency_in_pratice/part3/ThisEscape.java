package java_concurrency_in_pratice.part3;

import java_concurrency_in_pratice.anno.NoThreadSafe;
import java_concurrency_in_pratice.common.EventListener;
import java_concurrency_in_pratice.common.EventSource;

import java.awt.*;

/**
 * 隐式地使this逸出
 * @Author: lx
 * @Date: Created in 2018/1/4 0004
 */
@NoThreadSafe
public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerLinstener(new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }

            private void doSomething(Event e) {
            }
        });
    }
}
