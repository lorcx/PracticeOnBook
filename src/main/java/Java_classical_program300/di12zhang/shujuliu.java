package Java_classical_program300.di12zhang;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-24 ����11:29:59
 *������������
 */
public class shujuliu {
	public static void main(String[] args) {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("e:/123.txt")));
			for (int i = 0; i < 10; i++) {
				dos.writeDouble(Math.random()*1000);//д�븡��
			}
			dos.flush();//���
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(dos != null){
				try {
					dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		double dou = 0.0d;
		try {
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream("e:/123.txt")));
			while(true){
				dou += dis.readDouble();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			if(dos != null){
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(dou);
	}
}
