package Java_classical_program300.di15zhang;
/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-30 ����8:34:41
 *������ ����
 */
public class DeadLock {
	private static final Object o1 = new Object();
	private static final Object o2 = new Object();
	private class run1 implements Runnable{
		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			synchronized (o1) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   System.out.println(threadName);
				synchronized (o2) {
					System.out.println("o2");
				}
			}
		}
		
	}
	private class run2 implements Runnable{

		@Override
		public void run() {
			String threadName = Thread.currentThread().getName();
			synchronized (o2) {
				try {
					Thread.sleep(1000);
					System.out.println(threadName);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					System.out.println("o1");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new DeadLock().new run1());
		Thread t2 = new Thread(new DeadLock().new run2());
	  run2 r =	new DeadLock().new run2();
		t1.start();
		t2.start();
	}
}
