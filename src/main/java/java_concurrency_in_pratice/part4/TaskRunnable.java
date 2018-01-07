package java_concurrency_in_pratice.part4;

import java.util.concurrent.BlockingQueue;

/**
 * 恢复被中断的状态以避免屏蔽中断
 * @Author: lx
 * @Date: Created in 2018/1/7 0007
 */
public class TaskRunnable implements Runnable {
    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // 恢复被中断的状态
            Thread.currentThread().interrupt();
        }
    }

    private void processTask(Task take) {

    }
}


interface Task {

}
