package java_concurrency_in_pratice.part8;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 串行算法转化为并行算法
 * 只有循环的每个任务没有关联，并串行执行开销比新建一个任务大时有用
 * TransformingSequential 变换顺序
 * @Author lx
 * @Date 2018/1/28 16:33
 */
public abstract class TransformingSequential {
    public abstract void process(Element e);

    /**
     * 串行处理
     * @param elements
     */
    void processSequentially(List<Element> elements) {
        for (Element e : elements) {
            process(e);
        }
    }

    /**
     * 串行递归
     * @param nodes
     * @param results
     * @param <T>
     */
    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
        for (Node<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    /**
     * 并行递归
     * @param exec
     * @param nodes
     * @param results
     * @param <T>
     */
    public <T> void parallelRecursive(final Executor exec, List<Node<T>> nodes, final Collection<T> results) {
        for (final Node<T> n : nodes) {
            // 提交任务后就立刻返回了 如果是串行的话就要等到前一个完成再算后一个
            exec.execute(() -> {
                results.add(n.compute());// 只有compute是并行计算的
            });
        }
    }

    /**
     * 获取并行计算结果
     * @param nodes
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedQueue<>();
        parallelRecursive(exec, nodes, resultQueue);
        // 等待所有任务完成后关闭
        exec.shutdown();
        // 阻塞 在没有关闭前一直阻塞
        exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }
}

interface Element {

}

interface Node<T> {
    T compute();
    List<Node<T>> getChildren();
}
