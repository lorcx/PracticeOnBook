package Java_classical_program300.di17zhang;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2015-1-3 ����3:36:14
 *��������ȡ�ֶ���
 */
public class huoquziduanming {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","llxx","lorcx");
			String sql = "select * from demo";
		    PreparedStatement ps = conn.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    ResultSetMetaData rsm = rs.getMetaData();
		    int columnCount = rsm.getColumnCount();//����ֶ�����
		    for (int i = 1; i < columnCount; i++) {//���ݿ��1��ʼ
				System.out.println(rsm.getColumnName(i)+"||"+rsm.getColumnTypeName(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
