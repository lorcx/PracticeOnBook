package java_concurrency_in_pratice.part6;

import java_structure_arithmetic.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * html页面渲染 futuretask下载图片
 *
 * @Author: lx
 * @Date: Created in 2018/1/9 0009
 */
public abstract class FutureRenderer {
    private final ExecutorService exec = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };

        Future<List<ImageData>> future = exec.submit(task);
        renderText(source);

        try {
            List<ImageData> imageData = future.get();
        } catch (InterruptedException e) {
            // 重新设置线程中断状态
            Thread.currentThread().interrupt();
            // 不需要结果，取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            //getCause返回被封装的异常
            throw launderThrowable(e.getCause());
        }

    }

    private RuntimeException launderThrowable(Throwable t) {
        return null;
    }


    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);
}

interface ImageInfo {
    ImageData downloadImage();
}

interface ImageData {
}

