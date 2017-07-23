package codeJava.io_clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * 实现对象的克隆
 * @author dell
 *
 */
public class serialization implements Serializable ,Cloneable{
	private static final long serialVersionUID = 1L;

	public Object clone() throws CloneNotSupportedException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		Object obj = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(in);
			obj = ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
