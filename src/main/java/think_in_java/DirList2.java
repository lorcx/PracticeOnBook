package think_in_java;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 匿名内部类
 * @author lx
 *
 */
public class DirList2 {
	public static FilenameFilter filter(final String regex){//要用这个变量个匿名内部类必须是final
		return new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		};
		
	}
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
//		args = new String[1];
//		args[0] = "\\*";
		//*.xml  过滤
		File path = new File("E:\\my_workspace\\java_300\\src\\thiniinjava");// . 表示项目的根路径
		String[] list;
		if(args.length == 0)
			list = path.list();//返回制定路径下的文件列表
		else
			list = path.list(new DirList2().filter(args[0])); //正则表达式过滤列表  用了回调的方式，是策略模式的一种
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String st : list) {
			System.out.println(st);
		}
		 
	}
}
