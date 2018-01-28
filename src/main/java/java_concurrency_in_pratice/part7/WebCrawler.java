package java_concurrency_in_pratice.part7;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * TrackingExecutorService来保存未完成的任务以备后续使用
 *
 * @Author lx
 * @Date 2018/1/27 14:32
 */
public abstract class WebCrawler {
    private volatile TrackingExecutor exec;
    private final Set<URL> urlsToCrawl = new HashSet<>();

    private final ConcurrentMap<URL, Boolean> seen = new ConcurrentHashMap<>();
    private static final long TIMEOUT = 500;
    private static final TimeUnit UNIT = TimeUnit.MICROSECONDS;

    public WebCrawler(URL startUrl) {
        urlsToCrawl.add(startUrl);
    }

    public synchronized void start() {
        exec = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl) {
            submitCrawTask(url);
        }
        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            // shutdownNow返回已经提交还未开始的任务
            saveUncrawled(exec.shutdownNow());
            if (exec.awaitTermination(TIMEOUT, UNIT)) {
                // getCancelledTasks 返回中断正在执行的任务
                saveUncrawled(exec.getCancelledTasks());
            }
        } finally {
            exec = null;
        }
    }

    private void saveUncrawled(List<Runnable> runnables) {
        for (Runnable task : runnables) {
            urlsToCrawl.add(((CrawTask) task).getPage());
        }
    }

    private void submitCrawTask(URL url) {
        exec.execute(new CrawTask(url));
    }

    protected abstract List<URL> processPage(URL url);

    private class CrawTask implements Runnable {
        private final URL url;
        private int count = 1;

        CrawTask(URL url) {
            this.url = url;
        }

        boolean alreadyCrawled() {
            return seen.putIfAbsent(url, true) != null;
        }

        void markuncrawled() {
            seen.remove(url);
            System.out.printf("marking %s uncrawled %n", url);
        }

        public URL getPage() {
            return url;
        }

        @Override
        public void run() {
            for (URL link : processPage(url)) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                submitCrawTask(link);
            }
        }

    }
}
