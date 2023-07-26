package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.util.DBConn;

public class Ex06_04 {

	public static void main(String[] args) {
		String sql = "DELETE FROM dept " + " WHERE deptno = ? ";
		int pdeptno = 50;

		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pdeptno);
			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		DBConn.close();
		System.out.println(" end ");

	}

}
