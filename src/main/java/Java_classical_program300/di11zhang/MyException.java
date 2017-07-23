package Java_classical_program300.di11zhang;
/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-23 ����11:36:37
 *�������Զ����쳣
 */
public class MyException extends Exception {
	private static final long serialVersionUID = 1L;

	public MyException (){
		
	}
	public MyException(String msg){
		super(msg);
	}
	public MyException(Throwable th){
		super(th);
	}
	public MyException(String msg,Throwable th){
		super(msg,th);
	}
}
