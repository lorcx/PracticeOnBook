package Java_classical_program300.di12zhang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-27 ����10:26:28
 *������ nio ������
 */
public class NIOfuzhi {
	public static void main(String[] args) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel read = null;//����ܵ�
		FileChannel write = null;//����ܵ�
		try {
			fis = new FileInputStream("E:/123.txt");
			fos = new FileOutputStream("E:/456.txt");
			read = fis.getChannel();
			write = fos.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(1024);//����������
			while(true){
				bb.clear();//���軺����
				if(read.read(bb)== -1){//���û�������˾�����ѭ��
					break;
				}
				bb.flip();
				write.write(bb);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(write != null){
				try {
					write.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(read != null){
				try {
					read.close();
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
