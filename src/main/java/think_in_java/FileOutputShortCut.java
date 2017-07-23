package think_in_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * 文本输出快捷方式
 * @author dell
 *
 */
public class FileOutputShortCut {
	static String file = "F:\\BasicFileOutput.txt";
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new StringReader(BufferedInputFile.read("E:\\mye_workspace\\java_300\\src\\thiniinjava\\BasicFileOutput.java")));
			PrintWriter writer = new PrintWriter(file);//printWriter有个辅助构造器,写入时执行所有的装饰工作.
			int lineNum = 0;
			String str = "";
			while((str = reader.readLine()) != null){
				System.out.println(lineNum++  + ":" + str + "\n");
				writer.write(lineNum + ":" + str + "\n");
			}
			writer.close();//不关闭 就不能刷新或清空缓冲区
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
