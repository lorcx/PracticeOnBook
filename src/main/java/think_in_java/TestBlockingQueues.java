package think_in_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 阻塞队列
 * Created by lx on 2016/3/26.
 */
public class TestBlockingQueues {
    public static void main(String[] args) {
        test("LinkedBlockedQueue",new LinkedBlockingQueue<LiftOff>());
        test("ArrayBlockedQueue",new ArrayBlockingQueue<LiftOff>(3));
        test("SynchronousQueue",new SynchronousQueue<LiftOff>());
    }

    static void test(String msg,BlockingQueue queue){
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for(int i = 0;i < 5;i++){
            runner.add(new LiftOff(5));
        }
        getKey("Press 'Enter' (" + msg +")");
        t.interrupt();
        System.out.println("Finished " + msg + " test");
    }

    static void getKey(String msg){
        System.out.println(msg);
        getKey();
    }

    static void getKey(){
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}


class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
        this.rockets = rockets;
    }

    public void add(LiftOff lo){
        try {
            rockets.put(lo);
        } catch (InterruptedException e) {
            System.out.println("Interrupted during put()");
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                LiftOff rocket = rockets.take();
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("Waking from take()");
        }
        System.out.println("Exit liftOffRunner");
    }
}
