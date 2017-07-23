package think_in_java;

public interface Interface {
	void doSomeThing();
	void someThingElse(String arg);
}

class RealObject implements Interface{

	public void doSomeThing() {
		System.out.println("doSomeThing();");
	}

	public void someThingElse(String arg) {
		System.out.println("someThingElse"+arg);
	}
	
}

class simpleProxy implements Interface{
	private Interface inter;
	
	public simpleProxy(Interface inter) {
		this.inter = inter;
	}
	
	public void doSomeThing() {
		System.out.println("simpleProxy doSomeThing");
		inter.doSomeThing();
	}

	public void someThingElse(String arg) {
		System.out.println("someThingElse");
		inter.someThingElse(arg);
	}
}

@SuppressWarnings("all")
class custorm {
	
	public static void custorm(Interface inter){
		inter.doSomeThing();
		inter.someThingElse("bonbonb");
	}
	
	public static void main(String[] args) {
		custorm(new RealObject());
		custorm(new simpleProxy(new RealObject()));
	}
}
