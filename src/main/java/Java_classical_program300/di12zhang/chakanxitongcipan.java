package Java_classical_program300.di12zhang;

import java.io.File;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2014-12-25 ����10:22:17
 *�������鿴ϵͳ�������
 */
public class chakanxitongcipan {
	private Icon icon;//����ϵͳ����ͼ��
	private String name;//�����������
	private String discription;//�������ʹ�����
	private int usagePrecent;//�������ʹ����
	
	public chakanxitongcipan(File disc){
		FileSystemView fsv = FileSystemView.getFileSystemView();
		icon = fsv.getSystemIcon(disc);//��ȡ����ͼ��
		name = "���ش���("+disc.getAbsolutePath().substring(0,2)+")";//��ȡ��������
		String totalSpace = byteToGigabyte(disc.getTotalSpace());//��ȡ���̵�ȫ���ռ�
		String freeSpace = byteToGigabyte(disc.getFreeSpace());//��ȡ���̿��ÿռ�
		discription = freeSpace + "����GB����"+totalSpace + "GB";//����
		usagePrecent = (int) (100*(disc.getTotalSpace() - disc.getFreeSpace())/disc.getTotalSpace());
	}
	
	public String byteToGigabyte(long byteNumber){
		double result = byteNumber/Math.pow(1024, 3);//����λbyteת����GB pow 1024 3�η�
		return String.format("%.1f", result);
		
	}
	
	
	
	
	
	public Icon getIcon() {
		return icon;
	}
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public int getUsagePrecent() {
		return usagePrecent;
	}
	public void setUsagePrecent(int usagePrecent) {
		this.usagePrecent = usagePrecent;
	}
}
