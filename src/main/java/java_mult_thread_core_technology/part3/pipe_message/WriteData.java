package java_mult_thread_core_technology.part3.pipe_message;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 使用管道完成线程间的通信
 * Created by Administrator on 2017/8/28.
 */
public class WriteData {
    public void writeMethod(PipedOutputStream out) {
        System.out.println("write: ");
        try {
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                // 写入管道
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData {
    public void readMethod(PipedInputStream in) {
        try {
            System.out.println("read: ");
            byte[] byteArr = new byte[20];
            int readLen = in.read(byteArr);
            while (readLen != -1) {
                String newData = new String(byteArr, 0 ,readLen);
                System.out.print(newData);
                readLen = in.read(byteArr);
            }
            System.out.println();
            in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}

class ThreadWrite extends Thread {
    private WriteData write;
    private PipedOutputStream out;

    public ThreadWrite(WriteData write, PipedOutputStream out) {
        this.write = write;
        this.out = out;
    }

    @Override
    public void run() {
        write.writeMethod(out);
    }
}

class ThreadRead extends Thread {
    private ReadData read;
    private PipedInputStream in;

    public ThreadRead(ReadData read, PipedInputStream in) {
        this.read = read;
        this.in = in;
    }

    @Override
    public void run() {
        read.readMethod(in);
    }
}

class RunPipe {
    public static void main(String[] args) {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();
        PipedOutputStream outputStream = new PipedOutputStream();
        PipedInputStream inputStream = new PipedInputStream();
        try {
            outputStream.connect(inputStream);
            ThreadRead readThread = new ThreadRead(readData, inputStream);
            readThread.start();

            Thread.sleep(2000);

            ThreadWrite writeThread = new ThreadWrite(writeData, outputStream);
            writeThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}