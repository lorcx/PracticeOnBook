package Java_classical_program300.di13zhang;
/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-28 ����9:35:11
 *������ ö���ඨ��
 *
 * �� static final 
 */
public enum EnumDemo3 {
	//ͨ��������ֵ ��Ҫ�д������Ĺ��췽�� �͸����Եķ���
	NORTH("12"),EAST("123"),SOUTH("123"),WEST("123");
	private final String value;//�����ж���ֵ������
	EnumDemo3(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public static void main(String[] args) {
		EnumDemo3 east2 = EnumDemo3.EAST;
		System.out.println(EnumDemo3.EAST);
		System.out.println(east2);
	}
}
