package think_in_java;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 获取目录3
 * @author lx
 *
 */
public class DirList3 {
	public static void main(final String[] args) {
		File path = new File("E:\\my_workspace\\java_300\\src\\thiniinjava");// . 表示项目的根路径
		String[] list;
		if(args.length == 0)
			list = path.list();//返回制定路径下的文件列表
		else
			list = path.list(new FilenameFilter() {
				Pattern pattern = Pattern.compile(args[0]);
				@Override
				public boolean accept(File dir, String name) {
					return pattern.matcher(name).matches();
				}
			}); //正则表达式过滤列表  用了回调的方式，是策略模式的一种
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for (String st : list) {
			System.out.println(st);
		}
	}
}
