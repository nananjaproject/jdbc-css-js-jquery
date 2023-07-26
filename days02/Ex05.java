package days02;

import java.sql.SQLException;

import com.util.DataHandler;

/**
 * @author genie
 *	@date 2023. 4. 11. - 오후 2:07:39
 * @subject
 * @content
 */
public class Ex05 {

	public static void main(String[] args) {
		
		DataHandler dataHandler = new DataHandler();
		   
		   dataHandler.jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		   dataHandler.userid = "scott";
		   dataHandler.password = "tiger";
		   
		   try {
		      dataHandler.getDBConnection();
		      System.out.println( dataHandler.conn );
		      dataHandler.conn.close();
		   } catch (SQLException e) { 
		      e.printStackTrace();
		   }

		
	}	// main

}	// class
