package think_in_java;
/**
 * 子对象调用构造方法之前调用draw方法，里边的变量为0，因为编译器会初始化为二进制的0 
 * @author dell
 *
 */
public class fu {
	public fu(){
		System.out.println("fu.before");
		draw();
		System.out.println("fu.after");
	}
	public void draw(){
		System.out.println("fu.draw");
	}
}

class zi extends fu{
	private int i = 1;
	public zi(int r){
		i = r;
		System.out.println(i);
	}
	@Override
	public void draw() {
		System.out.println("zi.draw ===i==="+i);
	}
	public static void main(String[] args) {
		new zi(5);
	}
	
}