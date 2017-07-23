package think_in_java;

public interface FactoryI<T> {
	T create();
}

class Foo2<T>{
	private T x;
	public <F extends FactoryI<T>> Foo2(F factory){
		x = factory.create();
	}
}

class IntegerFactory implements FactoryI<Integer> {
	public Integer create() {
		System.out.println("Integer create ...........");
		return new Integer(0);
	}
}

/**
 * widget:装饰物
 */
class Widget {
	public static class Factory implements FactoryI<Widget> {
		public Widget create(){
			System.out.println("Widget create ...........");
			return new Widget();
		}
	}
}

class FactoryConstraint{
	public static void main(String[] args) {
		new Foo2<Integer>(new IntegerFactory());
		new Foo2<Widget>(new Widget.Factory());// 创建内部类语法：new 外部类.内部类（）
	}
}


