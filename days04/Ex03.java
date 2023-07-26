package days04;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConn;

public class Ex03 {

	public static void main(String[] args) {
		// [오라클] 트랜잭션 처리
		// [자바] 트랜잭션 처리 -테스트~
		
		//트랜잭션 ? 하나의 논리적인 작업 단위
		//예) 계좌 이체
		//	  1) A - 인출 UPDATE
		//	  2) B - 입금 UPDATE
		
		//[1+2]
		//1) dept 새로운 부서 추가 50 'QC' 'SEOUL'		성공
		//2) dept 새로운 부서 추가 50 'QC' 'POHANG'		실패
		

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount=0;
		
		String sql = "INSERT INTO dept VALUES(?, ?, ?)";
		
		conn = DBConn.getConnection();
		//[1+2]
		//1) dept 새로운 부서 추가 50 'QC' 'SEOUL'		성공
		//2) dept 새로운 부서 추가 50 'QC' 'POHANG'		실패
		
		try {
			
			conn.setAutoCommit(false);//(1) 자동으로 DML문이 커밋되는 설정 false
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 50);
			pstmt.setString(2,  "QC");
			pstmt.setString(3,  "SEOUL");
			rowCount = pstmt.executeUpdate();
			if (rowCount==1) {
				System.out.println(">첫 번째 INSERT문 성공~");
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 50);
			pstmt.setString(2,  "QC");
			pstmt.setString(3,  "POHANG");
			rowCount = pstmt.executeUpdate();
			if (rowCount==1) {
				System.out.println(">두 번째 INSERT문 성공~");
			}
			conn.commit(); //모두 성공
		} catch (SQLException e) { //하나라도 실패하면 catch문으로 올꺼임
			e.printStackTrace();
			try {
				conn.rollback(); //모두 취소
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		DBConn.close();
		
		System.out.println("end");

	}

}
