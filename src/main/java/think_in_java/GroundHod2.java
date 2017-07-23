package think_in_java;

public class GroundHod2 extends GroundHod{

	public GroundHod2(int num) {
		super(num);
	}
	
	@Override
	public int hashCode() {
		return num;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof GroundHod2 && (num == ((GroundHod)o).num);
	}
}

class SpringDetector2 {
	public static void main(String[] args) {
		try {
			SpringDector.detectSpring(GroundHod2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}