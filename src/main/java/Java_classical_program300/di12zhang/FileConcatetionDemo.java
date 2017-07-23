package Java_classical_program300.di12zhang;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-27 ����8:42:31
 *������ʹ�� SequenceInputStream�� XX�ļ����µ��ļ������� txt��
 *  ����д��־ ͨ�����Զ����־�������
 */
public class FileConcatetionDemo {
	public static void main(String[] args) {
		File file[] = new File("E:/demo").listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if(file.getAbsolutePath().endsWith("txt")){//��txt��β
					return true;
				}
				return false;
			}
		});
		////////////////////////////////////////////////////////////////////////////////
		List<FileInputStream> list = new ArrayList<FileInputStream>();
		SequenceInputStream sis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		for (File f : file) {
			try {
				list.add(new FileInputStream(f));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}	
		}
		
		try {
			sis = new SequenceInputStream(Collections.enumeration(list));
			bis = new BufferedInputStream(sis);
			fos = new FileOutputStream("E:/123.txt");
			int flag = 0;
			while((flag = bis.read())!= -1){
				fos.write(flag);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//�ر���
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(sis != null){
				try {
					sis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	}
}
