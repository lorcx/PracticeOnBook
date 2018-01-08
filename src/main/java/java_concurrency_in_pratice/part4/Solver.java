package java_concurrency_in_pratice.part4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * JDK cyclicbarrier 实例
 * @Author: lx
 * @Date: Created in 2018/1/8 0008
 */
public class Solver {
    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        public void run() {
            while (!done()) {
                processRow(myRow);
                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private void processRow(int myRow) {

        }

        private boolean done() {
            return false;
        }
    }

    public Solver(float[][] matrix) throws InterruptedException {
        data = matrix;
        N = matrix.length;
        Runnable barrierAction = new Runnable() {
            public void run() {
                mergeRows(null);
            }
        };
        barrier = new CyclicBarrier(N, barrierAction);
        List<Thread> threads = new ArrayList<Thread>(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threads.add(thread);
            thread.start();
        } // wait until done
        for (Thread thread : threads)
            thread.join();
    }

    private void mergeRows(Object p0) {

    }
}
