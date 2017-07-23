package think_in_java;


import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 简单的文件读写工具
 * @author dell
 *
 */
public class TextFile extends ArrayList<String> {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(TextFile.class);
	public TextFile(String fileName) {
		this(fileName,"\n");  //调用当前类构造方法
	}
	
	public TextFile(String fileName,String splitter) {//arrayList中的方法
		super(Arrays.asList(read(fileName).split(splitter)));
		if(get(0).equals(""))
			remove(0);
	}
	
	/**
	 * 读取文件
	 * @param file
	 * @return
	 */
	public static String read(String file) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader;
		try {
		    reader = new BufferedReader(new FileReader(new File(file).getAbsolutePath()));
			String s;
			try{
				while((s = reader.readLine()) != null){
					sb.append(s);
					sb.append("\n");
				}
			}finally{
				reader.close();
			}
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException();//运行时的异常  不用try catch了
		}
		return sb.toString();
	}
	
	/**
	 * 写文件
	 * @param string
	 * @param file 写入的文件名
	 * @param text 写入的文本
	 */
	public static void write(String file, String text) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(file).getAbsolutePath()));
			try{
				pw.write(text);
			}finally{
				pw.close();
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}		
		
	}
	
	/**
	 *  写文件
	 * @param file
	 */
	public void write(String file) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(file).getAbsolutePath()));
			try{
				for(String item : this){//如果一个类实现了Iterable接口，就可以用这种语法来迭代自己这个容器。 容器的上下文
					pw.println(item);
				}
			}finally{
				pw.close();
			}
		} catch (IOException e) {
			throw new RuntimeException();
		}		
		
	}
	
	public static void main(String[] args) {
		String file = read("F:\\a.txt");
		write("F:\\b.txt",file);
		TextFile textFile = new TextFile("F:\\a.txt");
		textFile.write("F:\\b.txt");
		TreeSet<String> words = new TreeSet<String>(new TextFile("F:\\a.txt","\\w"));
		System.out.println(words.headSet("a"));
	}

	

}
