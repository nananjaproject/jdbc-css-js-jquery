package days05;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.util.DBConn;

/**
 * @author genie
 * @date 2023. 4. 14. - 오전 9:36:49
 * @subject
 * @content
 */
public class Ex02 {

	public static void main(String[] args) {
		/*
		 * 1. 로그인 처리 아이디 : [ kenik ] 비밀번호 : [ 1234 ]
		 * 
		 * [로그인][회원가입]
		 * 
		 * 2. up_logon 회원테이블 = 아이디(PK), 비밀번호 X emp = empno(PK), ename
		 * 
		 * 3. 로그인 성공 : 0 로그인 실패 ㄴ 아이디 존재하지 않으면 : 1 ㄴ 비밀번호 틀리면 : -1
		 * 
		 */

		Scanner scanner = new Scanner(System.in);
		System.out.print("> 로그인 ID, PWD를 입력 ? ");
		int pid = scanner.nextInt();	//empno
		String ppwd = scanner.next();	//ename

		String sql = "{ call up_logon(?,?,?) }";
		Connection conn = null;
		CallableStatement cstmt = null;

		conn = DBConn.getConnection();
		//	7369	 SMITH
		
		try {
			cstmt = conn.prepareCall(sql);
			// ? (IN), ?(OUT)
			cstmt.setInt(1, pid);
			cstmt.setString(2, ppwd);
			cstmt.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
			cstmt.executeQuery();
			// Object -> Integer -> int
			int up_logon = (int) cstmt.getObject(3); // 0, 1

			if (up_logon == 0) {
				System.out.println("로그인 성공.");
			}else if (up_logon ==1) {
				System.out.println("로그인 실패 - ID 가 존재하지 않습니다.");
			} else {	// -1
				System.out.println("로그인 실패 - ID 가 존재하지만 PWD가 잘못되었습니다.. ");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // 이거 없으면 에러나도 에러 안나고 프로그램 종료됨. 그러니까 꼭 넣어두기!
		} finally {
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		DBConn.close();

		System.out.println("end");

	} // main

}// class
