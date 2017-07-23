package think_in_java;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * 文件锁
 * @author dell
 *
 */
public class FileLocking {
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("E:\\2.txt");
			FileLock fileLock = fos.getChannel().tryLock();
			if(fileLock != null){
				System.out.println("locak file");
				TimeUnit.MILLISECONDS.sleep(1000);
				fileLock.release();
				System.out.println("release file");
			}
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
