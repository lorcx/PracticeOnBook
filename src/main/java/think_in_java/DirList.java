package think_in_java;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 18章 io file类
 * 查看目录列表
 * Matcher：匹配器
 * @author lx
 *
 */
public class DirList {
	public static void main(String[] args) {
		//*.xml  过滤
		File path = new File("E:\\my_workspace\\java_300\\src\\thiniinjava");// . 表示项目的根路径
		String[] list;
		if(args.length == 0)
			list = path.list();//返回制定路径下的文件列表
		else
			list = path.list(new DirFilter(args[0])); //正则表达式过滤列表  用了回调的方式，是策略模式的一种
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String st : list) {
			System.out.println(st);
		}
		/**
		 * output:
			  .classpath
				.git
				.gitignore
				.mymetadata
				.project
				.settings
				build.xml
				myclass
				myjar
				ReadMe.txt
				src
				WebRoot
		 */
	}
}

class DirFilter implements FilenameFilter {
	private Pattern pattern;

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);//将正则表达式编译到模式中
	}

	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();//matcher（）要匹配的  matches尝试是否匹配 就是验证
	}
	
}