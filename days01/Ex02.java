package days01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author genie
 * @date 2023. 4. 10. - 오전 11:50:32
 * @subject 자바 + 오라클 서버 연결
 * @content
 */
public class Ex02 {

	public static void main(String[] args) {
		try {
	         // 1. JDBC 드라이버 로딩 - [ojdbc6.jar] 파일 추가해주어ㅑ하는데 없어서 에러뜨는거임
	         Class.forName("oracle.jdbc.driver.OracleDriver");   
	         // 2. Connection 객체 ㅁㅊ
	         // Connection String ( 연결 문자열 )
	         String url = "jdbc:oracle:thin:@localhost:1521:xe";
	         String user = "scott";
	         String password = "tiger";
	         Connection conn =  DriverManager.getConnection(url, user, password);
	         // 3. CRUD
	         System.out.println( conn );
	         // 4. Connection 객체 닫기
	         conn.close();
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      } catch (SQLException e) { 
	         e.printStackTrace();
	      }
	      
	      System.out.println("= END =");

		

	} // main

} // class
