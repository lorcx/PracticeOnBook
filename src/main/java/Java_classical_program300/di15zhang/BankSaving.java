package Java_classical_program300.di15zhang;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-30 ����10:42:07
 *���������д����
 *    ��ͬ����ʵ�����д��
 */
public class BankSaving {
	private double money = 10000;
	private final ReentrantLock lock = new ReentrantLock();//������
	/**
	 * ���еĴ�Ǯ
	 * @param money
	 */
	public void save(double SaveMoney){
		lock.lock();//����
		try{
			if(SaveMoney > 0){
				System.out.println(money);
				money = money + SaveMoney;
				System.out.println(queryMoney());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			lock.unlock();//�ͷ���
		}
	}
	/**
	 * ��ѯ
	 * @return
	 */
	public double queryMoney(){
		return money;
	}
	
}
