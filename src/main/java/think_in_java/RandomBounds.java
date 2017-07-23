package think_in_java;

/**
 * usage:使用
 * math.random [0,1) 包含0 不·包含1
 * @author dell
 *
 */
public class RandomBounds {
	
	static void usage(){
		System.out.println("usage :");
		System.out.println("\t RandomBounds lower");
		System.out.println("\t RandomBounds upper");
		System.exit(1);
	}
	
	public static void main(String[] args) {
		if(args.length != 1)
			usage();
		if(args[0].equals("lower")){
			while(Math.random() != 0.0){
				System.out.println("produced !0.0");
			}
		}else if(args[0].equals("upper")){
			while(Math.random() != 1.0){
				System.out.println("produced !1.0");
			}
		}
	}
}
