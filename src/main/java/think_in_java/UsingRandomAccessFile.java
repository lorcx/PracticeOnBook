package think_in_java;

import java.io.RandomAccessFile;


/**
 * 使用随机访问存储
 * @author dell
 *
 */
public class UsingRandomAccessFile {

	static String file = "F:\\a.txt";
	static void display (){
		try {
			RandomAccessFile access = new RandomAccessFile(file, "r");
			for (int i = 0; i < 7; i++) {
				System.out.println(access.readDouble());
			}
			System.out.println(access.readUTF());
			access.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			RandomAccessFile access = new RandomAccessFile(file, "rw");
			for (int i = 0; i < 7; i++) {
				access.writeDouble(i * 1.414);
			}
			access.writeUTF("this is String");
			access.close();
			display();
			access = new RandomAccessFile(file, "rw");
			access.seek(5 * 8);//因为double是8字节，所以移动到第5个位置后
			access.writeDouble(32.23);//在第5个位置后插入
			access.close();
			display();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
