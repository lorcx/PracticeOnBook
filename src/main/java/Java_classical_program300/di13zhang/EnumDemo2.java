package Java_classical_program300.di13zhang;

/**
 *
 */
public class EnumDemo2 {
	public enum Demo{
		NORTH{public String getName(){return "北";}},
		EAST{public String getName(){return "东";}},
		SOUTH{public String getName(){return "南";}},
		WEST{public String getName(){return "西";}};
		public abstract String getName();
	}
	public static void main(String[] args) {
		Demo east = Demo.EAST;
		int i = Demo.NORTH.ordinal();
		System.out.println(i);
		System.out.println(east.getName());
		
		for (Demo d : Demo.values()) {
			System.out.println(d);
		}
		System.out.println(Demo.EAST.compareTo(Demo.SOUTH));
	}
}
