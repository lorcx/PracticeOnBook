package think_in_java;
/**
 * 显示环境属性
 * @author dell
 *
 */
public class showProperties {
	public static void main(String[] args) {
		System.getProperties().list(System.out);//获取系统环境列表
		//获取当前计算机的用户名
		System.out.println(System.getProperty("user.name"));
		//获取系统环境变量   path配置
		System.out.print(System.getProperty("java.library.path"));
	}
}
