package Java_classical_program300.di12zhang;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entity.User;

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
