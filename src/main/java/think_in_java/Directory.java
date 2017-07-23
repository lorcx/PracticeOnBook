package think_in_java;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 目录实用工具
 * 
 * @author lx
 * 
 */
public class Directory {

	public File[] local(File dir, final String regex) {
		return dir.listFiles(new FilenameFilter() {
			Pattern pattern = Pattern.compile(regex);// 编译正则

			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();// 验证
			}
		});
	}

	public File[] local(String dir, final String regex) {
		return local(new File(dir), regex);
	}

	public static class TreeInfo implements Iterable<File> {
		public List<File> files = new ArrayList<File>();
		public List<File> dirs = new ArrayList<File>();

		@Override
		public Iterator<File> iterator() {
			return files.iterator();
		}

		void addAll(TreeInfo other) {
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}

		@Override
		public String toString() {
			return "dirs:" + PPrint.printFormt(dirs) + "\n files:"
					+ PPrint.printFormt(files);
		}
	}

	public static TreeInfo walk(File start, String regex) {
		return recuresDirs(start, regex);
	}

	public static TreeInfo walk(String start, String regex) {
		return recuresDirs(new File(start), regex);
	}

	public static TreeInfo walk(File start) {
		return recuresDirs(start, ".*");
	}

	public static TreeInfo walk(String start) {
		return recuresDirs(new File(start), ".*");
	}

	private static TreeInfo recuresDirs(File start, String regex) {
		TreeInfo ti = new TreeInfo();
		for (File file : start.listFiles()) {
			if (file.isDirectory()) {// 递归遍历目录
				ti.dirs.add(file);
				ti.addAll(recuresDirs(file, regex));
			} else if (file.getName().matches(regex)) {
				ti.files.add(file);
			}
		}
		return ti;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			TreeInfo tt = walk(".");
			System.out.println(tt);// walk(".") toString
			System.out.println("一共找到" + tt.files.size() + "文件！");
		} else {
			for (String start : args) {
				System.out.println(walk(start));
			}
		}
	}

}
