package think_in_java;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 读取2进制文件的工具
 * @author dell
 *
 */
public class BinaryFile {
	/**
	 * 读取文件
	 * @param file
	 * @return
	 */
	public byte[] read(File file){
		byte[] by = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			by = new byte[bis.available()];
			bis.read(by);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return by;
	}
	
	public byte[] read(String fileName) {
		return read(new File(fileName).getAbsolutePath());
	}
}
