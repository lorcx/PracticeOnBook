package Java_classical_program300.di12zhang;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-27 ����10:13:09
 *������ʹ���µ���дȡ����
 */
public class NIOWriteDemo {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		FileChannel fc = null;//�ܵ�
		String msg = "helloworld";
		try {
			fos = new FileOutputStream("E:/123.txt");
			fc = fos.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(1024);//����������
			bb.put(msg.getBytes());//д��������
			bb.flip();//�ӻ���������Ҫ���ô˷���
			fc.write(bb);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fc != null){
				try {
					fc.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
