package java_mult_thread_core_technology.part3.pipe_message;

import java.io.*;

/**
 * 使用管道完成线程间的通信 (字符)
 * Created by Administrator on 2017/8/28.
 */
public class WriteData2 {
    public void writeMethod(PipedWriter out) {
        System.out.println("write: ");
        try {
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                // 写入管道
                out.write(outData);
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData2 {
    public void readMethod(PipedReader in) {
        try {
            System.out.println("read: ");
            char[] charArr = new char[20];
            int readLen = in.read(charArr);
            while (readLen != -1) {
                String newData = new String(charArr, 0 ,readLen);
                System.out.print(newData);
                readLen = in.read(charArr);
            }
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadWrite2 extends Thread {
    private WriteData2 write;
    private PipedWriter out;

    public ThreadWrite2(WriteData2 write, PipedWriter out) {
        this.write = write;
        this.out = out;
    }

    @Override
    public void run() {
        write.writeMethod(out);
    }
}

class ThreadRead2 extends Thread {
    private ReadData2 read;
    private PipedReader in;

    public ThreadRead2(ReadData2 read, PipedReader in) {
        this.read = read;
        this.in = in;
    }

    @Override
    public void run() {
        read.readMethod(in);
    }
}

class RunPipe2 {
    public static void main(String[] args) {
        WriteData2 writeData = new WriteData2();
        ReadData2 readData = new ReadData2();
        PipedWriter outputStream = new PipedWriter();
        PipedReader inputStream = new PipedReader();
        try {
            outputStream.connect(inputStream);
            ThreadRead2 readThread = new ThreadRead2(readData, inputStream);
            readThread.start();

            Thread.sleep(2000);

            ThreadWrite2 writeThread = new ThreadWrite2(writeData, outputStream);
            writeThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}