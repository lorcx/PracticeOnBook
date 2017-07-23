package think_in_java;

import java.io.File;
import java.io.IOException;

/**
 * 打印指定的文件
 * @author dell
 * Process:过程
 * Strategy:策略
 */
public class ProcessFiles {
	
	public interface Strategy{
		void process(File file);
	}
	
	private Strategy strategy;
	private String ext;
	
	public ProcessFiles(Strategy strategy,String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}
	
	public void start(String[] args){
		if(args.length == 0)
			processdireDirectoryTree(new File("."));
		else
			for (String arg : args) {
				File file = new File(arg);
				if(file.isDirectory())
					processdireDirectoryTree(file);
				else 
					if(arg.endsWith("." + ext))
						try {
							strategy.process(new File(arg).getCanonicalFile());//返回此抽象路径名的规范形式
						} catch (IOException e) {
							e.printStackTrace();
						}
			}
	}
	
	public void processdireDirectoryTree(File root){
		for (File file : Directory.walk(root.getAbsolutePath(),".*\\." + ext)) {
			try {
				strategy.process(file.getCanonicalFile());//返回此抽象路径名的规范形式
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public static void main(String[] args) {
		//匿名内部类用到的时候才执行 先执行processFiles的构造方法
		new ProcessFiles(new Strategy() {
			
			@Override
			public void process(File file) {
				System.out.println(file);
			}
		}, "java").start(args);
		
	}
}
