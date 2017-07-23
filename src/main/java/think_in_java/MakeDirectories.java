package think_in_java;

import java.io.File;

/**
 * file类中的方法
 * @author dell
 *
 */
public class MakeDirectories {
	/**
	 * 文件信息
	 * +
	 */
	private static void fileData(File f){
		System.out.println(
				"abso path:\n" + f.getAbsolutePath() + "\n" +
				"cand read:\n" + f.canRead() + "\n" +
				"cand write:\n" + f.canWrite() + "\n" +
				"get name:\n" + f.getName() + "\n" +
				"get parent:\n" + f.getParent() + "\n" +
				"get path:\n" + f.getPath() + "\n" +
				"length:\n" + f.length() + "\n" +     //文件大小按照字节算的
				"lastmodify:\n" + f.lastModified() 
					);
		if(f.isFile())
			System.out.println("this is a file");
		else if(f.isDirectory())
			System.out.println("this is a directory");
	}
	/**
	 * 错误异常
	 */
	private static void usage() {
		System.err.println("error .....");
		System.exit(1);//非0为异常终止
	}
	
	public static void main(String[] args) {
		if(args.length < 1)
			usage();
		if(args[0].equals("-r")){
			if(args.length != 3)//array
				usage();
			File old = new File(args[1]),
				 rname = new File(args[2]);
			old.renameTo(rname); //被命名的文件名不能有重复的
			fileData(old);
			fileData(rname);
			return;//exit main
		}
		int count = 0;
		boolean del = false;
		if(args[0].equals("-d")){
			count++;
			del = true;
		}
		count--;
		while(++count < args.length){
			File f = new File(args[count]);
			if(f.exists()){
				System.out.println(f + "exist");
				if(del){   //param : -d F:\2.txt
					System.out.println("deleteing ...." + f);
					f.delete();
				}
			}else{
				if(!del){  //param : F:\aaa
					f.mkdir();
					System.out.println("create" + f);
				}
			}
			fileData(f);
		}
	}
}
