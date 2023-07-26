package days02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import oracle.net.aso.r;

public class Test {

	public static void main(String[] args) {
		
		DTO dto = getEmp();
		addEmp(dto);

	}
	
	private static void addEmp(DTO dto) {
		
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String user = "scott";
		String password = "tiger";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int deptno = dto.getDeptno();
		String ename = dto.getEname();
		
		
		
	}
		



}
