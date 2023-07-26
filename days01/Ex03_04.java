package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author genie
 * @date 2023. 4. 10. - 오후 4:25:52
 * @subject
 * @content dept 테이블 삭제 (DELETE)
 */
public class Ex03_04 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println(" 삭제할 부서번호 입력?"); // 50
		int deptno = scanner.nextInt();

		// Ex03.java			dept 테이블 조회 (SELECT)
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "scott";
        String password = "tiger";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String sql = "DELETE FROM dept "
                +" WHERE deptno = " + deptno;
        System.out.println(sql); // 출력확인

		try {
			// 1.
			Class.forName(className);
			// 2.
			conn = DriverManager.getConnection(url,user,password);
			// 3. CRUD 작업
			stmt = conn.createStatement();
			
			int rowCount = stmt.executeUpdate(sql);	
			if(rowCount == 1) {
				System.out.println("부서 삭제 완료!!");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				// 4.
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}// main

}
