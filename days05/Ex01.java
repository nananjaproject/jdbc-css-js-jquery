package days05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

public class Ex01 {

	public static void main(String[] args) {
		/*	[ JQuery 사용해서 Ajax 처리 ]
		 * 1. UP_IDCHECK 저장 프로시저 생성
		 * 		회원가입
		 * 		이름
		 * 		아이디 : [ kenik ][ 중복체크버튼 ]
		 * 			사용가능합니다.
		 * 			이미 사용 중 입니다.
		 * 			
		 * 		주민번호
		 * 		주소
		 * 		연락처
		 * 		등등
		 * 2. emp(회원) 테이블의 empno(아이디)를 회원아이디라고 가정하고
		 * 
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.print("> 중복체크할 ID(empno)를 입력 ? ");
		int pid = scanner.nextInt();
		
		String sql = "{ call up_idcheck(?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;
		
		conn = DBConn.getConnection();
		
		try {
			cstmt = conn.prepareCall(sql);
			//? (IN), ?(OUT)
			cstmt.setInt(1, pid);
			cstmt.registerOutParameter(2, oracle.jdbc.OracleTypes.INTEGER);
			cstmt.executeQuery();
			// Object -> Integer -> int
			int idCheck = (int) cstmt.getObject(2);	// 0, 1
			
			if(idCheck == 0) {
				System.out.println("사용가능한 ID(empno)입니다.");
			} else {
				System.out.println("이미 사용중인 ID(empno)입니다. ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	// 이거 없으면 에러나도 에러 안나고 프로그램 종료됨. 그러니까 꼭 넣어두기!
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		DBConn.close();
		
		System.out.println("end");
		
		
	}	//main

}	//class
