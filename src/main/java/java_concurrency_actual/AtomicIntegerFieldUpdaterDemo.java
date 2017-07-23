package java_concurrency_actual;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater 可以将普通的字段编程线程安全的
 * 假设某地要进行一场选举。现在模拟这个投票场景，如果选民投了候选人一票，就记为1，
 * 否则计为0，最终统计选票的结果。
 *
 * 底层实现使用了cas操作
 * Created by Administrator on 2017/5/8 0008.
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate {
        int id;
        volatile int score;// 得分
    }

    // 将score字段转换成原子性的
    public static final AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

    // 检查Updater是否工作成功
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread(){
                @Override
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();
                    }
                }
            };

            t[i].start();
        }

        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }
        System.out.println("score =" + stu.score);
        System.out.println("allScore =" + allScore);
    }
}
