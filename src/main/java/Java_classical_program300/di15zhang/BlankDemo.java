package Java_classical_program300.di15zhang;


/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-30 ����10:53:27
 *������
 */
public class BlankDemo {
	private BankSaving bs = new BankSaving();
	public static void main(String[] args) {
		 BlankDemo bd = new BlankDemo();
		 MoneyThread mt = bd.new MoneyThread();
		 MoneyThread mt1 = bd.new MoneyThread();
		 mt.start();
		 mt1.start();
	}
	public class MoneyThread extends Thread{

		@Override
		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bs.save(20000);
			
		}
		
	}
}
