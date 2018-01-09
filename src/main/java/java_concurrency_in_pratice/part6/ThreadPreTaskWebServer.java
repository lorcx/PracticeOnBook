package java_concurrency_in_pratice.part6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 为每个请求创建一个线程
 * 可以并发的处理请求
 * 在高负载的生产环境下，会导致内存溢出
 * @Author: lx
 * @Date: Created in 2018/1/9 0009
 */
public class ThreadPreTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };

            new Thread(task).run();
        }
    }

    private static void handleRequest(Socket connection) {
    }
}
