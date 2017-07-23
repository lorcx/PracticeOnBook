package think_in_java;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 线程中断
 * 当底层资源释放是，线程解除阻塞,释放锁
 * Created by dell on 2016/3/23.
 */
@SuppressWarnings("all")
public class NioBlock implements Runnable {
    private SocketChannel sc;
    public NioBlock(SocketChannel sc) {
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.println("wait for reading +" + this);
        try {
            sc.read(ByteBuffer.allocate(1));
        } catch (ClosedByInterruptException e) {
            System.out.println("ClosedByInterruptException");
        } catch (AsynchronousCloseException e){
            System.out.println("AsynchronousCloseException");
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        System.out.println("exit nioSocket ..." + this);
    }
}

@SuppressWarnings("all")
class nioInterrupting{
    public static void main(String[] args) throws IOException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket socket = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost",8080);
        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);
        Future<?> f = exec.submit(new NioBlock(sc1));
        exec.execute(new NioBlock(sc2));
        exec.shutdown();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        f.cancel(true);

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sc2.close();
    }
}