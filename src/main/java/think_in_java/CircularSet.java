package think_in_java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 20多线程
 * Circular 循环
 * Created by lx on 2016/2/13.
 */
@SuppressWarnings("all")
public class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int size) {
        array = new int[size];
        len = size;
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int i) {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int val) {
        for (int i = 0; i < len; i++) {
            if (array[i] == val) {
                return true;
            }
        }
        return false;
    }


}

@SuppressWarnings("all")
class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet cs = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static class SerialCher implements Runnable {

        public void run() {
            while(true){
                int serial = SerialNumberGenerator.nextSerialNumber();
                if(cs.contains(serial)){
                    System.out.println("重复duplicate：" + serial);
                    System.exit(0);
                }
                cs.add(serial);
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0;i < SIZE;i++){
            exec.execute(new SerialCher());
        }

//        for(int i = 0;i < 10;i++){
//            System.out.println(i % 5);
//        }
    }
}


