package days02;

import java.sql.Connection;

import com.util.DBConn;

/**
 * @author genie
 *	@date 2023. 4. 11. - 오전 11:04:52
 * @subject
 * @content
 */
public class Ex02 {

	public static void main(String[] args) {
		// com.util.DBConn.java
		// ㄴ getConnection() 메서드 구현
		Connection conn = DBConn.getConnection();
		System.out.println(conn);
		DBConn.close();
		
		// Ex01.java 
	}

}
