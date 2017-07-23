package think_in_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * DelayQueue无界的blockingQueue
 *
 * Delayed 元素的一个无界阻塞队列，只有在延迟期满时才能从中提取元素。
 * 该队列的头部 是延迟期满后保存时间最长的 Delayed 元素。如果延迟都还没有期满，
 * 则队列没有头部，并且 poll 将返回 null。当一个元素的 getDelay(TimeUnit.NANOSECONDS)
 * 方法返回一个小于等于 0 的值时，将发生到期。即使无法使用 take 或 poll 移除未到期的元素，
 * 也不会将这些元素作为正常元素对待。例如，size 方法同时返回到期和未到期元素的计数。此队列不允许使用 null 元素。
 * Created by dell on 2016/3/31.
 */
public class DelayedTaskDemo {
    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
        for(int i = 0;i < 20;i++){
            queue.add(new DelayedTask(rand.nextInt(5000)));
        }
        queue.add(new DelayedTask.EndSentinel(5000,exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}

class DelayedTask implements Delayed,Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;///三角洲delta
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();

    public DelayedTask(int delayMilliseconds) {
        this.delta = delayMilliseconds;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta,MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(),NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if(trigger < that.trigger) return -1;
        if(trigger > that.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delta) + "Task" + id;
    }

    public String summary(){
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask{
        private ExecutorService exec;
        public EndSentinel(int delayMilliseconds,ExecutorService e) {
            super(delayMilliseconds);
            exec = e;
        }

        @Override
        public void run() {
            for(DelayedTask pt : sequence){
                System.out.println(pt.summary());
            }
            System.out.println();
            System.out.println(this + "Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
               q.take().run();
            }
        } catch (Exception e) {

        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}