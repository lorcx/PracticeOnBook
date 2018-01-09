package java_concurrency_in_pratice.part6;

import java.util.List;
import java.util.concurrent.*;

/**
 * 使用completionService实现页面渲染器，使页面元素在下载完成后立即显示出来、
 *
 *  使用blockqueue 存放queueFuturetask,当任务完成时放到阻塞队列中，再从阻塞队列中获取，调用get方法拿到数据。
 *
 * @Author: lx
 * @Date: Created in 2018/1/9 0009
 */
public abstract class Renderer {
    private final ExecutorService executor;

    Renderer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> info = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executor);
        for (final ImageInfo imageInfo : info) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }

        renderText(source);

        try {
            for (int t = 0, n = info.size(); t < n; t++) {
                // take 获得已完成的结果
                Future<ImageData> f = completionService.take();
                ImageData imageData = f.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    abstract void renderText(CharSequence s);

    abstract List<ImageInfo> scanForImageInfo(CharSequence s);

    abstract void renderImage(ImageData i);

}


