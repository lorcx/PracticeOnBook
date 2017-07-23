package Java_classical_program300.di12zhang;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-27 ����11:17:18
 *����������ӳ�䵽�ڴ���
 *     ���Ƽ�ʹ��
 */
public class MemoryMapDemo {
	public static void main(String[] args) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("E:/123.txt");
			FileChannel fc = fis.getChannel();//�����ܵ�
			int size = (int)fc.size();
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, size);
			for (int i = 0; i < size; i++) {
				System.out.print((char)mbb.get());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
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
