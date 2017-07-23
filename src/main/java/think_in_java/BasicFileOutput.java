package think_in_java;

import java.io.*;

public class BasicFileOutput {
	static String file = "F:\\BasicFileOutput.txt";
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new StringReader(BufferedInputFile.read("E:\\mye_workspace\\java_300\\src\\thiniinjava\\BasicFileOutput.java")));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
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
