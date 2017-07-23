package think_in_java;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 文件读取
 * @author dell
 *
 */
public class BufferedInputFile {
	public static String read(String fileName){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String s;
			StringBuilder sb = new StringBuilder();
			while((s = br.readLine()) != null){
//				System.out.println(s);
				sb.append(s + "\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		read("E:\\mye_workspace\\java_300\\src\\thiniinjava\\BufferedInputFile.java");
	}
}
