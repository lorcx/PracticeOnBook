package think_in_java;
/**
 * Parcel 包裹
 *  内部类
 * @author dell
 *
 */
public class Parcel {
	class Contents{
		private int i = 11;
		public int value(){
			return i;
		}
	}
	/**
	 * Destination目的地
	 * @author dell
	 *
	 */
	class Destination{
		private String label;
		Destination(String label){
			this.label = label;
		}
		String readLabel(){
			return label;
		}
	}
	
	public void Ship(String dest){
		Contents con = new Contents();
		System.out.println(con.value());
		Destination des = new Destination(dest);
		System.out.println(des.readLabel());
		
	}
	/**
	 * 返回内部类的引用
	 * @return
	 */
	public Contents getContents(){
		return new Contents();
	}
	
	public Destination getDestination(String label){
		return new Destination(label);
	}
	
	public static void main(String[] args) {
		Parcel pa = new Parcel();
		pa.Ship("hahaha");
		Parcel.Contents pc = pa.getContents();
		Parcel.Destination pd = pa.getDestination("dddd");
		System.out.println(pc.value());
		
		
	}
}
