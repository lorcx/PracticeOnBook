package Java_classical_program300.di12zhang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-27 ����10:04:04
 *������ʹ���µ�����ȡ����
 */
public class NIOReadDemo {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileChannel fc = null;
		try {
			fis = new FileInputStream("E:/123.txt");
			fc = fis.getChannel();//��ȡ�ܵ�
			ByteBuffer bb = ByteBuffer.allocate(1024);//����������
			fc.read(bb);//���ܵ��е�����д����������
			System.out.println(new String(bb.array()));
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
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
