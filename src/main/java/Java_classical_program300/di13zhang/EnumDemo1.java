package Java_classical_program300.di13zhang;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-28 ����9:35:11
 *������ ö���ඨ��
 *
 * �� static final 
 */
public enum EnumDemo1 {
	NORTH,EAST,SOUTH,WEST;
	private static final String position = "test";
	public static EnumDemo1 demo(){
		if("test".equals(position)){
			return NORTH;
		}
			return EAST;
		
	}
}
