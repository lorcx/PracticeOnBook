package think_in_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 18章系统进程所用
 * @author lx
 *
 */
public class OsExecute {

	/**
	 * 执行命令
	 * @param command :命令
	 */
	public static void command(String command){
		boolean err = false;
		try {
			Process process = new ProcessBuilder(command.split(" ")).start();
			String s; //redundant多余   =“” 初始化多余
			//普通输出
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while((s = reader.readLine()) != null){
				System.out.println(s);
			}
			//错误输出
			BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while((s = errReader.readLine()) != null){
				System.out.println(s);
				err = true;
			}
		} catch (IOException e) {
			if(!command.startsWith("CMD /C")){
				command("command:" + command);
			}else{
				System.out.println(e);
				throw new RuntimeException();
			}
		}
		if(err){
			throw new OsException("err:" + command);
		}
		
	}
}

class OsExecuteDemo{
	public static void main(String[] args) {
		OsExecute.command("javap OsExecuteDemo");
	}
}
