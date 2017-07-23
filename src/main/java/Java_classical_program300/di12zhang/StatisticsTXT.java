package Java_classical_program300.di12zhang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-27 ����9:17:56
 *���������ı�����ͳ�� ���ʸ��� 
 *              ���ָ���
 *              �ո������
 *              ֻ�ܼ��㵥�ʸ��������Ҫ������ĸ������Ҫ�����ܳ��� ����ȥ�ո��� 
 */
public class StatisticsTXT {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		File file = new File("E:/123.txt");
		FileInputStream fis = null;
		StreamTokenizer st = null;
		int worldCount = 0;
		int numberCount = 0;
		int qita = 0;
		try {
			fis = new FileInputStream(file);
		    st = new StreamTokenizer(fis);
		    while(st.nextToken() != StreamTokenizer.TT_EOF ){//tt.eof��ʾ�ļ�����
		    	switch (st.ttype) {//switch ������Boolean���ʽ byte short int char
				case StreamTokenizer.TT_NUMBER:
					numberCount++;
					break;
				case StreamTokenizer.TT_WORD:
					worldCount++;
				default:
					qita++;
					break;
				}
		    }
		    System.out.println("���ʣ�"+worldCount);
		    System.out.println("���֣�"+numberCount);
		    System.out.println("���ո�"+qita);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//�ر���
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
