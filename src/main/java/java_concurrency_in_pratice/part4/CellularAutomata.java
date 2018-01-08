package java_concurrency_in_pratice.part4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 通过CyclicBarrier协调细胞自动衍生系统中计算
 * Cellular 细胞
 * Automata 自动机
 *
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        // 当所有线程到达屏障点，就执行下面的Runable方法
        this.barrier = new CyclicBarrier(count, () -> mainBoard.commitNewValues());
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }

                try {
                    barrier.await();// 等待其他线程到达栅栏处，返回一个达到索引，可以用来指定下次处理谁谁是主要的
                } catch (InterruptedException e) {
                    return;
                } catch (BrokenBarrierException e) {
                    return;
                }
            }
        }

        private int computeValue(int x, int y) {
            return 0;
        }
    }

    public void start() {
        for (int i = 0; i < workers.length; i++) {
            new Thread(workers[i]).start();
        }
        mainBoard.waitForConvergence();
    }
}

/**
 * Board 板
 * Converged 融合
 * Partitions 分区
 */
interface Board {
    int getMaxX();

    int getMaxY();

    int getValue(int x, int y);

    int setNewValue(int x, int y, int value);

    void commitNewValues();

    boolean hasConverged();

    void waitForConvergence();

    Board getSubBoard(int numPartitions, int index);
}

