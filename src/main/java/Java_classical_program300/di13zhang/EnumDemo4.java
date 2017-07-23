package Java_classical_program300.di13zhang;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-28 ����9:35:11
 *������ ö���ඨ��
 *
 * �� static final 
 */
public enum EnumDemo4 {
	DE1(1,12.1,"abc"),DE2(2,32.1,"asd"),DE3(3,43.1,"ff"),DE4(4,54.1,"er");
	private int size;//��С
	private double money;//�۸�
	private String name;//����
	private EnumDemo4(int size,double money,String name){
		this.size = size;
		this.money = money;
		this.name = name;
	}
	@Override
	public String toString() {
		return name+money+size;
	}
	public static void main(String[] args) {
		for (EnumDemo4 string : EnumDemo4.values()) {
			System.out.println(string);
		}
		
	}
	
}
