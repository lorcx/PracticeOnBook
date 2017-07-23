package think_in_java;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MyException {
	public static void main(String[] args) {
		System.err.println("aaa");
		System.out.println("aaa");
		//jdk中的日志
		Logger log = Logger.getLogger("MyException.class");
		log.log(Level.INFO, "aa");
		
//		StringWriter sw = new StringWriter();
		Rethrowing rt = new Rethrowing();
		try {
			rt.g();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		try {
			rt.h();
		} catch (Throwable e) {
			e.printStackTrace(System.out);
		}
		
	}
}

class Rethrowing{
	void f()throws Exception {
		throw new Exception("throw for f()");
	}
	
	void g() throws Exception {
		try {
			f();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw e;
		}
	}
	
	void h() throws Throwable {
		try {
			f();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw e.fillInStackTrace();//使用后原来的异常发生点就会丢失
		}
	}
}

