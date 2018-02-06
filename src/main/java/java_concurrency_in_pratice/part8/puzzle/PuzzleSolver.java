package java_concurrency_in_pratice.part8.puzzle;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决当找不到解时，如何多线程如何退出
 * 因为如果找不到解 闭锁会一直阻塞
 *
 * 扩展类增加一个任务计数器，当计数器为0设置value null
 * @Author: lx
 * @Date: Created in 2018/2/6 0006
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
    public PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    private final AtomicInteger taskCount = new AtomicInteger(0);

    @Override
    public Runnable newTask(P p, M m, PuzzleNode n) {
        return new CountingSolverTask(p, m, n);
    }

    class CountingSolverTask extends SolverTask {

        CountingSolverTask(P pos, M move, PuzzleNode<P, M> prev) {
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                if (taskCount.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }
        }
    }
}
