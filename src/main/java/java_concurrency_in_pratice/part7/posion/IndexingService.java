package java_concurrency_in_pratice.part7.posion;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author lx
 * @Date 2018/1/27 13:26
 */
public class IndexingService {
    public static final int CAPACITY = 1000;
    private static final File POISON = new File("");
    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(final FileFilter fileFilter, File root) {
        this.fileFilter = f -> f.isDirectory() || fileFilter.accept(f);
        this.root = root;
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
    }

    private boolean alreadyIndexed(File f) {
        return false;
    }

    class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                craw(root);
            } catch (InterruptedException e) {
                /*fail through*/
            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                    } catch (InterruptedException e) {
                        /*retry*/
                    }
                }
            }
        }

        private void craw(File root) throws InterruptedException {
            File[] entries = root.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        craw(entry);
                    } else if (!alreadyIndexed(entry)) {
                        queue.put(entry);
                    }
                }
            }
        }
    }

    class IndexerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON) {
                        break;
                    } else {
                        indexFile(file);
                    }
                }
            } catch (InterruptedException consumed) {

            }
        }

        private void indexFile(File file) {

        }
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }

}



