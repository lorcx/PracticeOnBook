package Java_classical_program300.di8zhang;
/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-21 ����4:15:39
 *�����ж������Ļ���Ӣ��
 */
public class ChineseOrEnglish {
	public static void main(String[] args) {
		char c = 'a';
		System.out.println(	ChineseOrEnglish.checkZiFu(c));
	}
	//�ж��ַ�
	public  static String checkZiFu(char c){
		if(c >= 0 && c<= 9){
			return "����";
		}else if(c >= 'a' && c <= 'z'){
			return "Сд��ĸ";
		}else if(c >= 'A' && c <= 'Z'){
			return "��д��ĸ";
		}else if(Character.isLetter(c)){
			return "�Ǻ���";
		}else{
			return "����ַ�";
		}
	}
	
}
