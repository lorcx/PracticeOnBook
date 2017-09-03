package Java_classical_program300.di12zhang;

import java.io.*;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-25 ����9:29:50
 *������
 */
public class xuliehua {
	public static void main(String[] args) {
		User u = new User("asdf","123",222);
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("e:/123.txt")));
			oos.writeObject(u);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(oos != null){
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("e:/123.txt")));
			User uu = (User)ois.readObject();
			System.out.println(uu.getName());
			System.out.println(uu.getAge());
			System.out.println(uu.getMoney());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			if(ois != null){
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}

class User {
	private String name;
	private String age;
	private int money;

	public User() {
	}

	public User(String name, String age, int money) {
		this.name = name;
		this.age = age;
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}