package java_concurrency_in_pratice.part4.craw;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 桌面搜索
 * 生产者线程
 *
 * @Author lx
 * @Date 2018/1/7 16:45
 */
public class ProducerConsumer {
    static class FileCrawler implements Runnable {
        private final BlockingQueue<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingQueue<File> fileQueue, final FileFilter fileFilter, File root) {
            this.fileQueue = fileQueue;
            this.root = root;
            this.fileFilter = f -> f.isDirectory() || fileFilter.accept(f);
        }

        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        crawl(entry);
                    } else if (!areadyIndexed(entry)) {
                        System.out.println("put = " + entry);
                        fileQueue.put(entry);
                    }
                }
            }
        }

        private boolean areadyIndexed(File f) {
            if (fileFilter.accept(f)) {
                System.exit(0);
            }
            return false;
        }
    }


    static class Indexer implements Runnable {
        private final BlockingQueue<File> queue;

        public Indexer(BlockingQueue<File> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    indexFile(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void indexFile(File file) {
            // index the file ....
        }
    }

    private static final int BOUND = 10; // 范围
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();// 返回可用处理器的数目

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingDeque<>(BOUND);

        FileFilter filter = f -> f.getName().equals("sha256sum.txt.!ut");

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }
        System.out.println("N_CONSUMERS = " + N_CONSUMERS);

        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }

    public static void main(String[] args) {
        File[] files = new File[]{new File("D:\\")};
        startIndexing(files);
    }

}
