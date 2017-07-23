package Java_classical_program300.di12zhang;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-27 ����9:39:05
 *����������prop�ļ�
 */
public class jiazai_prop {
	public void a(){
		Properties prop = new Properties();
		try {
			//1����
			prop.load(	this.getClass().getClassLoader().getResourceAsStream("demo.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();
		FileReader fr = null;
		try {
			//2����
			fr = new FileReader("src/demo.properties");
			prop.load(fr);
			Set set = prop.keySet();
			Iterator it = set.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println(key+"="+prop.getProperty(key));
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fr != null){
				fr.close();
			}
		}
		
	}
}
