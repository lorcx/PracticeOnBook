package Java_classical_program300.di17zhang;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *�����ˣ�lixin     
 *����ʱ�䣺2015-1-2 ����10:02:12
 *�����������в����Ĵ洢����
 */
public class youcande {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","llxx","lorcx");
			String sql = "{call demo2_pro('9','8','9')}";
			CallableStatement cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery();
			while(rs.next()){
				System.out.println("success");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
