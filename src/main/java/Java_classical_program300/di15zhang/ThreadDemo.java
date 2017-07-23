package Java_classical_program300.di15zhang;
/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-29 ����11:00:49
 *�������߳�ͬ��
 */
public class ThreadDemo implements Runnable {

	@Override
	public void run() {
		int n = 10;
		while(true){
			synchronized ("") {//ͬ�������
				if(n > 0){
					System.out.println("ʣ�����"+ --n);
				}
			}
		}
	}
	/**
	 * ����һ���߳�ִ������������ִ��
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
		Thread thread1 = new Thread(td);
		Thread thread2 = new Thread(td);
		Thread thread3 = new Thread(td);
		Thread thread4= new Thread(td);
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
	}

}
