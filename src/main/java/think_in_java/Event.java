package think_in_java;

import java.util.ArrayList;
import java.util.List;

/**
 * 内部类与控制框架
 * @author dell
 *
 */
public abstract class Event {
	private long eventTime;
	protected final long delayTime;
	public Event(long delayTime){
		this.delayTime = delayTime;
		start();
	}
	
	public void start() {
		eventTime = System.nanoTime() + delayTime;
	}
	
	public boolean ready(){
		return System.nanoTime() >= eventTime;
	} 
	
	public abstract void action();
	
}

/**
 * 触发事件的控制框架
 */
class Controller{
	private List<Event> list = new ArrayList<Event>();
	
	public void addEvent(Event e){
		list.add(e);
	}
	
	public void run(){
		while(list.size() > 0){
			for (Event e1 : new ArrayList<Event>(list)) {
				if(e1.ready()){
					System.out.println(e1);
					e1.action();
					list.remove(e1);
				}
			}
		}
	}
}

/**
 * 应用类
 */
class GreenHouseControls extends Controller {
	private boolean light = false;
	
	public class LightOn extends Event{
		
		public LightOn(long delayTime) {
			super(delayTime);
		}

		public void action() {
			light = true;
		}
		
		public String toString() {
			return "light on ....";
		}
		
	}
	
	public class LightOff extends Event{
		
		public LightOff(long delayTime) {
			super(delayTime);
		}

		public void action() {
			light = false;
		}
		
		public String toString() {
			return "light off ....";
		}
		
	}
	
	public class Bell extends Event{

		public Bell(long delayTime) {
			super(delayTime);
		}

		public void action() {
			addEvent(new Bell(delayTime));
		}
		
		public String toString(){
			return "bell ....";
		}
	}
	
	
	public class Restart extends Event{
		
		private Event[] ev;
		public Restart(long delayTime,Event[] e) {//构造方法的参数可以必父类的多
			super(delayTime);
			this.ev = e;
			for (Event e1 : e) {
				addEvent(e1);
			}
		}

		public void action() {
			for (Event e1 : ev) {
				e1.start();  
				addEvent(e1);
			}
			start(); //父类的
			addEvent(this);
		}
		
	}
	
	public class Terminate extends Event{

		public Terminate(long delayTime) {
			super(delayTime);
		}

		public void action() {
			System.exit(0);
		}
		public String toString(){
			return "terminate....";
		}
		
	}
	
	public static void main(String[] args) {
		GreenHouseControls gc = new GreenHouseControls();
		gc.addEvent(gc.new Bell(900));
		Event[] ev = {
				gc.new LightOn(200),
				gc.new LightOff(400),
				gc.new Terminate(300)
		};
		gc.addEvent(gc.new Restart(500, ev));
		gc.run();
	}
}






