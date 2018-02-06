package java_concurrency_in_pratice.part8.puzzle;

import java.util.List;
import java.util.concurrent.*;

/**
 * 并行解决
 * @Author: lx
 * @Date: Created in 2018/2/6 0006
 */
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P, Boolean> seen;
    protected final ValueLatch<PuzzleNode<P, M>> solution = new ValueLatch<>();


    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
        this.exec = initThreadPool();
        this.seen = new ConcurrentHashMap<>();
        if (exec instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor tpe = (ThreadPoolExecutor) exec;
            // 设置拒绝策略为 去掉未执行的任务
            tpe.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    private ExecutorService initThreadPool() {
        return Executors.newCachedThreadPool();
    }

    public List<M> solve() throws InterruptedException {
        P p = puzzle.initialPosition();
        exec.execute(newTask(p, null, null));
        PuzzleNode<P, M> solnPuzzleNode = solution.getValue();
        return (solnPuzzleNode == null) ? null : solnPuzzleNode.asMoveList();
    }

    protected Runnable newTask(P p, M m, PuzzleNode n) {
        return new SolverTask(p, m, n);
    }

    protected class SolverTask extends PuzzleNode<P, M> implements Runnable {

        public SolverTask(P pos, M move, PuzzleNode<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            // 已经找到解或已经遍历到这里
            if (solution.isSet() || seen.putIfAbsent(pos, true) != null) {
                return;
            }

            if (puzzle.isGoal(pos)) {
                // 找到解了
                solution.setValue(this);
            } else {
                //  将每个没有找到的任务添加到线程池中
                for (M m : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos, m), m, this));
                }
            }
        }
    }
}
