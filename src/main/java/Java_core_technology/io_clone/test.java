package codeJava.io_clone;
/**
 * 使用序列化实现对象的克隆
 * @author dell
 *
 */
public class test {
	public static void main(String[] args) throws Exception {
		Employee e1 = new Employee("abc",1);
		Employee e2 = (Employee) e1.clone();
		System.out.println("true");
		if(e1 instanceof Employee){
			System.out.println("true");
		}
		if(e2 instanceof Employee){
			System.out.println("true");
		}	
		if(e1 == e2){
			System.out.println("true");
		}	
		System.out.println(e2.getName()+"="+e2.getAge());
	}
}
