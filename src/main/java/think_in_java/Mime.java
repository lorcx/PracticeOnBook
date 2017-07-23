package think_in_java;

import java.lang.reflect.Method;

/**
 * 潜在的类型机制1：反射
 * @author lx
 * 
 * mime:小丑
 *pretend:假装
 *reproduce:复制
 *community:社区
 *reflectively：反映地
 *latent:潜在的
 *Reflection:反射
 */
public class Mime {
	public void walkAgainstTheWind(){}
	
	public void sit(){
		System.out.println("pretend sit ..");
	}
	
	//推隐形的墙
	public void pushInvisiableWalls(){}
	
	public String toString() {
		return "Mime";
	}
}

class SmartDog{
	public void speak(){
		System.out.println("woolf!");
	}
	
	public void sit(){
		System.out.println("sitting!");
	}
	
	public void reproduce(){}
}

class communicateReflectively{
	public static void perform(Object speaker){
		Class<?> c = speaker.getClass();
		try {
			Method m = c.getMethod("speak");
			m.invoke(speaker);
	
			Method m1 = c.getMethod("sit");
			m1.invoke(speaker);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
	}
}

class LatentReflection {
	public static void main(String[] args) {
		communicateReflectively.perform(new SmartDog());
		communicateReflectively.perform(new Mime());
	}
}
















