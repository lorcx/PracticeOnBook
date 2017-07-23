package think_in_java;
/**
 * 协变类型
 * j2ee5 才有的 ，返回值类型可以是多态
 * @author dell
 *
 */
public class gr {
	@Override
	public String toString() {
		return "gr";
	}
}

class li extends gr{
	@Override
	public String toString() {
		return "li";
	}
}

class milk{
	public gr getOne(){
		return new gr();
	}
}

class dbc extends milk{
	public li getOne(){
		return new li();
	}
	public static void main(String[] args) {
		milk m = new milk();
		gr g = m.getOne();
		System.out.println(g);
		g = new dbc().getOne();
		System.out.println(g);
	}
}
