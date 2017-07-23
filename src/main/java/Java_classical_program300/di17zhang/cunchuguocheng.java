package Java_classical_program300.di17zhang;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2015-1-2 ����9:44:14
 *�����������޲εĴ洢����
 */
public class cunchuguocheng {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","llxx","lorcx");
			String sql = "{call demo_pro()}";
			CallableStatement cs = conn.prepareCall(sql);
			cs.execute();
			cs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
