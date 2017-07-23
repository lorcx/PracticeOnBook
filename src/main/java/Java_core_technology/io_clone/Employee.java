package codeJava.io_clone;

/**
 * 雇员实体类 ，继承serialization实现克隆
 * @author dell
 *
 */
public class Employee extends serialization{
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
