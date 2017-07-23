package codeJava.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * 操作文件
 * @author dell
 *
 */
public class operationFile {
	/**
	 * 使用files 和 path 能简化读写 ，但只能处理中等长度的文件 
	 * 长度长的还是要使用流
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/* jdk 7 才添加的类*/
		Path path = Paths.get("/home/lixin93520","abcd");//绝对路径 可以写多个路径
		
		Path path1 = Paths.get("/java_300/src","se","demo.properties");
//		path1.resolve(path1);
		System.out.println(path1.getFileName());
		System.out.println(path1.getRoot());
		System.out.println(path1.getParent());
		path1.toFile();
		
		
		/*files jdk 7*/
		//需要写绝对路径
		/*从文件中读*/
		byte[] files = Files.readAllBytes(Paths.get("E:\\my_workspace\\java_300\\src\\123.dat"));
		//不能将数字转成char 
		System.out.println(Arrays.toString(files));
		for (int i = 0; i < files.length; i++) {
			System.out.print((char)files[i]);
		}
		System.out.println();
		
		//new String 可以将byte数字转成  
		String content = new String(files,"utf-8");
		System.out.println(content);
		
		
		/*从文件中写*/
		String str = "abcd1234";
		Files.write(Paths.get("E:\\my_workspace\\java_300\\src\\123.dat"), str.getBytes("UTF-8"));
		//想文件中追加内容
		Files.write(Paths.get("E:\\my_workspace\\java_300\\src\\123.dat"), str.getBytes("UTF-8"),StandardOpenOption.APPEND);
		
		InputStream is = Files.newInputStream(Paths.get(""), StandardOpenOption.APPEND);
		OutputStream os = Files.newOutputStream(Paths.get(""), StandardOpenOption.APPEND);
	    BufferedReader bis = Files.newBufferedReader(Paths.get(""),null);
	    BufferedReader bw = Files.newBufferedReader(Paths.get(""), null);
	    
	    
	    /* 复制和移动*/
	    Files.copy(Paths.get(""), Paths.get(""));
	   
	    //选项可以同时选多个
	    Files.move(Paths.get(""), Paths.get(""),StandardCopyOption.REPLACE_EXISTING);//移动并替换
	    Files.move(Paths.get(""), Paths.get(""),StandardCopyOption.COPY_ATTRIBUTES);//复制全部属性
	    
	    /*删除*/
	    Files.delete(Paths.get(""));
	    
	    /*创建文件*/
	    
	    Files.createDirectories(Paths.get(""));//创建目录 还会创建中间目录
	    Files.createDirectory(Paths.get(""));//创建目录
	    Files.createFile(Paths.get(""));//创建空文件
	    Path pa = Files.createTempFile("", "");//创建一个临时文件，并返回路径
	    
	    
	    
	}
}
