package think_in_java;

public class Parcel11 {
	public fu getFu(final String des,final float f){
		return new fu(){
			private int i = 1;
			
			{
				int n =	Math.round(f);
				if(n > 100){
					System.out.println("over bedget");//超出预算
				}
			}
			
			private String label = des;
		};
	}
	
	public static void main(String[] args) {
		Parcel11 p = new Parcel11();
		p.getFu("abcd",103.123f);
	}
}
