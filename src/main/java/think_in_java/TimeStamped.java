package think_in_java;

import java.util.Date;


/**
 * stamped:戳
 * serial:连续的
 * @author lx
 *
 */
public interface TimeStamped {
	long getStamped();
}

class TimeStampedImp implements TimeStamped{

	private long timeStamp;
	
	public TimeStampedImp() {
		timeStamp = new Date().getTime();
	}

	public long getStamped() {
		return timeStamp;
	}
	
}

/**********************************/
interface SerialNumbered{
	long getSerialNumbered();
}

class SerialNumberedImp implements SerialNumbered{
	private static long counter = 1;	
	private final  long serialNumber = counter++;
	
	public long getSerialNumbered() {
		return serialNumber;
	}
	
}

/**********************************/
interface Basic{
	public void set(String val);
	
	public String get();
}


class BasicImp implements Basic{
	public String val;
	
	public void set(String val) {
		this.val = val;
	}

	public String get() {
		return val;
	}
}

class Mixin extends BasicImp implements TimeStamped,SerialNumbered{//使用代理
	private TimeStamped ts = new TimeStampedImp();
	
	private SerialNumbered sn = new SerialNumberedImp();
	
	public long getSerialNumbered() {
		return sn.getSerialNumbered();
	}

	public long getStamped() {
		return ts.getStamped();
	}
}


class Mixins {
	public static void main(String[] args) {
		Mixin m1 = new Mixin(),m2 = new Mixin();
		m1.set("m1 .....");	
		m2.set("m2 .....");	
		System.out.println(m1.get()+"sn:"+m1.getSerialNumbered()+"ts:"+m1.getStamped());
		System.out.println(m2.get()+"sn:"+m2.getSerialNumbered()+"ts:"+m2.getStamped());
			
	}
}

/*******************************************************装饰器模式**************************************************************/

/**
 * decorator:装饰
 * @author lx
 *
 */
class Basic1{
	private String val;
	
	public void set(String val){
		this.val = val;
	}
	
	public String get(){
		return val;
	}
}

class Decorator extends Basic1{
	private Basic1 basic1;

	public Decorator(Basic1 basic1) {
		this.basic1 = basic1;
	}
	
	public void set(String val) {
		basic1.set(val);
	}
	
	public String get() {
		return basic1.get();
	}
}

class TimeStamped1 extends Decorator{
	private final long timeStamp;
	
	public TimeStamped1(Basic1 basic1) {
		super(basic1);
		timeStamp = new Date().getTime();
	}
	
	public long getTimeStamped1(){
		return timeStamp;
	}
}


class SerialNumbered1 extends Decorator{
	private static long counter = 1;
	
	private final long serialNumber = counter++;
	
	public SerialNumbered1(Basic1 basic1){
		super(basic1);
	}
	
	public long getSerialNumber(){
		return serialNumber;
	}
}


class Decoration {
	public static void main(String[] args) {
		Basic1 b1 = new Basic1();
		b1.set("11");
		TimeStamped1 ts1 = new TimeStamped1(b1);
		TimeStamped1 ts2 = new TimeStamped1(new SerialNumbered1(b1));
		SerialNumbered1 sn1 = new SerialNumbered1(b1);
		SerialNumbered1 sn2 = new SerialNumbered1(new SerialNumbered1(b1));
		System.out.println(ts1.get());
		System.out.println(ts2.get());
		System.out.println(sn1.get());
		System.out.println(sn2.get());
		System.out.println(sn1.getSerialNumber());
		System.out.println(sn2.getSerialNumber());
		System.out.println(ts2.getTimeStamped1());
		System.out.println(ts1.getTimeStamped1());
		
	
	}
}































