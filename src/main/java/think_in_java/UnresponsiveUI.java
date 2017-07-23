package think_in_java;

import java.io.IOException;

/**
 * 线程： 创建有响应用户界面
 * Created by dell on 2016/2/3.
 */
@SuppressWarnings("all")
public class UnresponsiveUI {
    private volatile double d = 1;

    public UnresponsiveUI() throws IOException {
        while(d > 0){
            d = d + (Math.PI + Math.E) / d;
            System.in.read(); //从控制台读取一个字节
        }
    }
}

/**
 * 有响应的界面
 * main线程接收控制台的结果
 * responseUI线程作为后台线程 自动计算
 *
 * 当main线程 运行结束时 所有的非后台线程运行完成 ，退出 。
 */
@SuppressWarnings("all")
class responsiveUI extends Thread {
    private static volatile double d = 1;

    public responsiveUI() {
        setDaemon(true);//守护线程
        start();
    }

    public void run() {
        while(true){
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws IOException {
//        new UnresponsiveUI();  死循环

        new responsiveUI();
        System.in.read();
        System.out.println(d);
    }
}


