package java_concurrency_in_pratice.part6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 串行处理请求
 *
 * 缺点明显，吞吐性能差，无法同时处理多个请求，后一个请求必须等前一个请求执行完。
 * @Author: lx
 * @Date: Created in 2018/1/9 0009
 */
public class SingleThreadWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {

    }
}
