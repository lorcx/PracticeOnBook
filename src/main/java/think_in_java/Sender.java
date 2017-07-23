package think_in_java;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用管道 在多线程
 * Created by lx on 2016/3/27.
 */
public class Sender implements Runnable {
    private Random rand = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPiedWriter(){
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for(char c = 'A';c < 'z';c++){
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                    System.out.println("sleep end");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;

    public Receiver(Sender send) throws IOException {
        in = new PipedReader(send.getPiedWriter());
    }

    @Override
    public void run() {
       try {
           while(true){
               System.out.println("Read : " + (char)in.read());
           }
       }catch (Exception e){
           e.printStackTrace();
       }

    }
}

class PipedIo{

    public static void main(String[] args) throws IOException, InterruptedException {
        Sender send = new Sender();
        Receiver receiver = new Receiver(send);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(send);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}


