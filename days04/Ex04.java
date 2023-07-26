package days04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

/**
 * @author genie
 *	@date 2023. 4. 13. - 오후 3:46:55
 * @subject
 * @content
 */
public class Ex04 {

	public static void main(String[] args) {
		//CallableStatement cstmt = null;		// 저장 프로시저, 저장 함수, 익명 프로시저 호출
		// up_insemp
		
		int empno = 9999;
		String ename = "hong";
		int deptno = 10;
		
		String sql = "{ CALL UP_INSEMP(pempno=>?,pename=>?,pdeptno=>?)}";
		
		Connection conn = null;
		CallableStatement cstmt = null;		//저장 프로시저, 저장 함수, 익명 프로시저 호출
		int rowCount = 0;
		
		conn = DBConn.getConnection();
		//stmt = conn.createStatement();
		//pstmt = conn.createStatement();
		try {
			//
			cstmt = conn.prepareCall(sql);
			// ? , ? , ?
			cstmt.setInt(1, empno);
			cstmt.setString(2, ename);
			cstmt.setInt(3, deptno);
			rowCount = cstmt.executeUpdate();
			if(rowCount == 1) {
				System.out.println("사원 추가 성공 ~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		

	}	//main

} //class

/*
class Parent{
	String name;
}

class Child extends Parent{
	int age;
}

*/