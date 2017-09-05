package java_mult_thread_core_technology.part1.suspend;

import javax.management.remote.SubjectDelegationPermission;

/**
 * suspend 会导致独占
 * Created by Administrator on 2017/8/18.
 */
public class SuspendErrorUse {
    public synchronized void printStr() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("a线程被suspend");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }
}

class Run7 {
    public static void main(String[] args) {
        try {
            final SuspendErrorUse seu = new SuspendErrorUse();
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    seu.printStr();
                }
            };
            t1.setName("a");
            t1.start();
            Thread.sleep(1000);

            Thread t2 = new Thread(){
                @Override
                public void run() {
                    System.out.println("Thread 2 启动了，但进入不了printStr方法中");
                    System.out.println("因为printStr 被 a线程锁定并且永远suspend了");
                    seu.printStr();
                }
            };
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}