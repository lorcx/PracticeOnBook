package think_in_java;

/**
 * Generic:一般的
 * with:用
 * 模板设计模式
 */
public abstract class GenericWithCreate<T> {
	final T element;
	public GenericWithCreate() {
		element = create();
	}
	public abstract T create();
}

class X{}

class Creator extends GenericWithCreate<X> {
	public X create() {
		return new X();
	}
	
	void f(){
		System.out.println(element.getClass().getSimpleName());
	}
}

class CreatorGeneric {
	public static void main(String[] args) {
		Creator c = new Creator();
		c.f();
	}
}




