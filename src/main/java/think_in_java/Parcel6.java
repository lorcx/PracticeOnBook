package think_in_java;
/**
 * parcel 包裹
 * internal 内部的
 * tracking 追踪的
 * slip 滑动的
 * track 轨道
 * @author dell
 *
 */
public class Parcel6 {
	/**
	 * 在方法中的的内部类，只能在这个方法中直接去new创建
	 * @param b
	 */
	private void internalTracking(boolean b){
		if(b){//b并不是内部类的创建条件，他会跟普通类一样一起编译
			class TrackingSlip{
				private String id;
			    TrackingSlip(String s) {
					this.id = s;
				}
				String getSlip(){
					return id;
				}
			}
			TrackingSlip ts = new TrackingSlip("slip");
			System.out.println(ts.getSlip());
		}
	}
	public void track(){
		internalTracking(true);
	}
	public static void main(String[] args) {
		Parcel6 p = new Parcel6();
		p.track();
	}
	
}
