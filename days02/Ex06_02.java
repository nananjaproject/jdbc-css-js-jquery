package days02;

import com.util.DBConn;

import java.sql.*;

/**
 * @author genie
 *	@date 2023. 4. 11. - 오후 3:49:57
 * @subject
 * @content		DROP SEQUENCE   SEQ_DEPT ;

			CREATE SEQUENCE   SEQ_DEPT   
			MINVALUE 1 MAXVALUE 90 
			INCREMENT BY 10 
			START WITH 50 
			NOCACHE  
			NOORDER  
			NOCYCLE ;
 */
public class Ex06_02 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = String.format(
				"INSERT INTO dept(deptno, dname, loc) "
				+"VALUES(SEQ_DEPT.NEXTVAL,?,?) "); //여기가 물음표가 되는 것을 바인딩 변수라고 한다.
		//1+2
		conn=DBConn.getConnection();
		
		//3
		String pdname="QC"; //?
		String ploc="SEOUL"; //?
		
		try {
			pstmt=conn.prepareStatement(sql);
			//?, ?에 대한 파라미터 2개가 빠져있어서 오류가 난다.
			pstmt.setString(1,  pdname);
			pstmt.setString(2, ploc);
			int rowCount=pstmt.executeUpdate();
			if (rowCount==1) {
				System.out.println("부서추가성공~");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close();
		}
		System.out.println("=end=");
		
		
	}//main

}//class
