package Java_classical_program300.di15zhang;
/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-30 ����10:01:39
 *������
 */
public class shouhuxiancheng {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Worker());
		Thread t2 = new Thread(new guard());
		t2.setDaemon(true);
		t1.start();
		t2.start();
	}
}
class Worker implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i+"ci");
		}
	}
	
}
//houtaixiancheng
class guard implements Runnable{

	@Override
	public void run() {
		long millis = System.currentTimeMillis();
		long pressTime = 0;
		while(true){
			if((System.currentTimeMillis() - millis) > pressTime){
				pressTime = System.currentTimeMillis() - millis;
				System.out.println(pressTime);
			}
			
		}
	}
	
}
