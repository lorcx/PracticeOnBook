package think_in_java;
/**
 * Shared共享的
 *   创建和销毁的顺序相反
 *   比较像栈
 * @author dell
 *
 */
public class Shared {
	private int refCount = 0;
	private static long count = 0; //long防止内存溢出
	private final long id = count++;
	public Shared(){
		System.out.println("create  "+this);
	}
	@Override
	public String toString() {
		return "Shared=id="+id;
	}
	/**
	 * 添加引用
	 */
	public void addRef(){
		refCount++;
	}
	/**
	 * 销毁
	 */
	public void dispose(){
		if(--refCount == 0){
			System.out.println("dispose  "+this);
		}
	}
}

/**
 * composing组成
 * @author dell
 *
 */
class Composing{
	private Shared shared;
	private static long count = 0;
	private final long id = count++;
	@Override
	public String toString() {
		return "Composing=id="+id;
	}
	public Composing(Shared shared){
		System.out.println("create  "+this);
		this.shared = shared;
		this.shared.addRef();
	}
	/**
	 * 销毁
	 */
	public void dispose(){
		System.out.println("dispose  "+this);
		shared.dispose();
	}
	public static void main(String[] args) {
		Shared sh = new Shared();
		Composing[] co = {new Composing(sh),
						  new Composing(sh),
						  new Composing(sh),
						  new Composing(sh),
						  new Composing(sh)
						};
		for (Composing c : co) {
			c.dispose();
		}
	}
}