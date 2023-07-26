package days02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.util.DBConn;

/**
 * @author genie
 * @date 2023. 4. 11. - 오후 2:22:09
 * @subject [Prepared + Statement ] //성능 빠르다
 * @content
 */
public class Ex06 {

	public static void main(String[] args) {
		// days01.Ex03.java

		// Ex03.java dept 테이블 조회 (SELECT)

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * " + " FROM dept " + " ORDER BY deptno ASC ";
		try {

			conn = DBConn.getConnection();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");

				System.out.printf("%d \t %s \t %s \n", deptno, dname, loc);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {

			}

		}

	} // main

} // class
