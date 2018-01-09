package java_concurrency_in_pratice.part6;

import javax.xml.rpc.Call;
import java.util.concurrent.*;

/**
 * 在指定时间内获取广告信息
 * @Author: lx
 * @Date: Created in 2018/1/9 0009
 */
public class RenderWithTimeBudget {
    private static final Ad DEFAULT_AD = new Ad();// 默认广告
    private static final long TIME_BUDGET = 1000;//超时
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    Page renderPageWithAd() {
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Future<Ad> task = exec.submit(new FetchAdTask());
        // 渲染页面
        Page page = renderPageBody();
        Ad ad;

        long timeLeft = endNanos - System.nanoTime();
        try {
            ad = task.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            ad = DEFAULT_AD;
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            // 超时就抛异常
            ad = DEFAULT_AD;
            // 取消超时任务
            task.cancel(true);
        }

        page.setAd(ad);
        return page;
    }

    private Page renderPageBody() {
        return null;
    }

    static class Ad {

    }

    static class Page {
        public void setAd(Ad ad) {

        }
    }

     static class FetchAdTask implements Callable<Ad> {
        public FetchAdTask() {
        }

        @Override
        public Ad call() throws Exception {
            return new Ad();
        }
    }
}
