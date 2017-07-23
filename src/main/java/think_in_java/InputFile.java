package think_in_java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("all")
public class InputFile {
	private BufferedReader in;
	
	/**
	 * 读取文件
	 * @param fileName
	 */
	public void inputFile(String fileName){
		try {
			in = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				//don't close it here!!
			}
		}
	}
	
	/**
	 * 读取一行
	 * @return
	 */
	public String getLine(){
		String s = "";
		try {
			s = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * dispose处理
	 */
	public void dispose(){
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("关闭失败",e.getCause());
		}
	}
	
	/**
	 * 读取全部
	 */
	public void getAll(){
		String result = "";
		try {
			while((result = in.readLine()) != null){
				System.out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		InputFile file = new InputFile();
		file.inputFile("E://password.txt");
		System.out.println(file.getLine());
		file.getAll();
		file.dispose();
	}
	
}
