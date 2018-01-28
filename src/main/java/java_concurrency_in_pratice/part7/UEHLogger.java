package java_concurrency_in_pratice.part7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 该类用来处理线程中发生RuntimeException导致报错，影响应用运行
 *
 * 将异常写入到日志中
 *
 *
 * 使用：
 *  Thread.setDefaultUncaughtExceptionHandler()
 * @Author lx
 * @Date 2018/1/27 15:33
 */
public class UEHLogger implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.SEVERE, "Thread terminated with exception: " + t.getName());
    }
}
