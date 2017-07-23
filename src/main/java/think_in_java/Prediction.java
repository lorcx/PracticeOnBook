package think_in_java;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Prediction:预报
 * GroundHod:土波鼠
 * 
 * @author dell
 *
 */
public class Prediction {
	private static Random ran = new Random(47);
	private boolean shadow = ran.nextInt() > 0.5;
	
	@Override
	public String toString() {
		if(shadow)
			return "111";
		else
			return "222";
	}
}

class GroundHod{
	protected int num;
	
	public GroundHod(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "GroundHod:#" + num;
	}
}

class SpringDector{
	public static <T extends GroundHod> void detectSpring(Class<T> type)throws Exception {
		Constructor<T> ghog = type.getConstructor(int.class);
		Map<GroundHod, Prediction> map = new HashMap<GroundHod, Prediction>();
		for (int i = 0; i < 10; i++) {
			map.put(ghog.newInstance(i),new Prediction());
		}
		System.out.println(map);
		GroundHod gh = ghog.newInstance(3);//相当于new 个对象 调用构造方法
		if(map.containsKey(gh)){//object的hashcode每一次生成的都不一样，所以第一次和第二次不一样，就找不到
			System.out.println(map.get(gh));
		}else{
			System.out.println("key not found");
		}
	}
	
	public static void main(String[] args) {
		try {
			detectSpring(GroundHod.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

