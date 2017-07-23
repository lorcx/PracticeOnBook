package think_in_java;

import java.util.Map;

/**
 * 输出系统环境环境变量
 * as
 * Environment环境
 * Variables变量
 * @author dell
 *
 */
public class EnvironmentVariables {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		for(Map.Entry entry: System.getenv().entrySet()){
			System.out.println(entry.getKey()+"=="+entry.getValue());
		}
	}
}
