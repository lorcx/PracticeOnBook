package Java_classical_program300.di16zhang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2015-1-2 ����5:19:09
 *������
 */
public class getIpDiZhi {
	private Map map;
	
	public void getALLIp(){
		 try {
			 InetAddress address = InetAddress.getLocalHost();//���inetaddress ����
			 String hostAddress = address.getHostAddress();//��ñ���ip��ַ
			 int pointIndex = hostAddress.lastIndexOf(".");
			 String netWork = hostAddress.substring(0,pointIndex + 1);//��������
			 for (int i = 1; i <= 255; i++) {
				String ip = netWork + i;//����ip��ַ
				PingIpThread thread = new getIpDiZhi().new PingIpThread(ip);
				Thread t = new Thread(thread);
				t.start();
			}
//			 map = new HashMap();
//			 while(true){
//				 map.get();
//			 }
		} catch (UnknownHostException e) {
			e.printStackTrace();
		
		}
	}
	
	public class PingIpThread implements Runnable{
		private String ip;
		public PingIpThread(){
			
		}
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public PingIpThread(String ip){
			this.ip = ip;
		}
		@Override
		public void run() {
			try {//ping ip��ַ -w ��ʾ�ȴ�ʱ�� -n��ʾ���·��ʹ���
				Process process = Runtime.getRuntime().exec("ping "+ip+"-w280-n1");
				InputStream is = process.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String line = br.readLine();
				while(line != null){
					if(null != line && !br.equals("")){
						if(line.equals("����") || line.length() > 10 && line.substring(0,10).equals("Reply form")){//�ж�Ϊping����ip
							System.out.println(ip);
						}
					}
					line = br.readLine();//�ٴζ�ȡ��Ϣ
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		getIpDiZhi g = new getIpDiZhi();
		g.getALLIp();
	}
}
