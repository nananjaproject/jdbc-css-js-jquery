package days02;

import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

import com.util.DBConn;

/**
 * @author genie
 *	@date 2023. 4. 11. - 오전 11:33:15
 * @subject
 * @content
 */
public class Ex03 {

	public static void main(String[] args) {
		// Connection String (연결문자열)을 파일로 따로 저장. : 
		//ConnectionString.properties
		// [Properties 컬렉션 클래스 ]
		// ㄴ Map 인터페이스를 구현한 클래스
		// ㄴ Entry(String key + String value )
		
		//C:\Class\WorkSpace\JDBCClass\jdbcPro
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		
		Properties p = new Properties();
		String fileName = dir +"\\src\\com\\util\\ConnectionString.properties";
		try(FileReader reader = new FileReader(fileName) ){
			
			p.load(reader);
			
			String user  =p.getProperty("user");
			String password = p.getProperty("password");
			String url = p.getProperty("url");
			// String className = p.getProperty("className");
			
			Connection conn = DBConn.getConnection(url, user,password);
			
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close();
		}
		
		
		
	}	//main

}	//class
