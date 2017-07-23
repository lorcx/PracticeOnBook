package think_in_java;
/**
 * 使用内部类的闭包功能，实现多重继承
 *  
 * Incrementable : 概念
 * increment : 增量
 * callee : 被召唤者
 */
public interface Incrementable {
	
	void increment();
	
}

/**
 * 简单的实现
 * @author dell
 *
 */
class Callee1 implements Incrementable {

	private int i = 0; 
	public void increment() {
		 i++;
		 System.out.println("Callee1:"+i);
	}
	
}

class MyIncrement{
	
	void increment(){
		System.out.println("MyIncrement.increment  .....");
	}
	
	static void f(MyIncrement mi){
		mi.increment();
	}
}


class Callee2 extends MyIncrement{
	
	private int i = 0;
	
	void increment() {
		super.increment();
		i++;
		System.out.println("Callee2:"+i);
	}
	
	public class closure implements Incrementable{

		public void increment() {
			Callee2.this.increment();
		}
		
	}
	
	Incrementable getCallBackRefersence(){
		return new closure();
	}
}

class Callee{
	private Incrementable callBackrefresence;
	
	Callee(Incrementable cbr){
		callBackrefresence = cbr;
	}
	
	void go(){
		callBackrefresence.increment();
	}
}

class callBacks{
	public static void main(String[] args) {
		Callee1 c1 = new Callee1();
		Callee2 c2 = new Callee2();
		MyIncrement.f(c2);
		Callee cc1 = new Callee(c1);
		Callee cc2 = new Callee(c2.getCallBackRefersence());
		cc1.go();
		cc2.go();
	}
}


