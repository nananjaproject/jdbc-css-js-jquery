 package days02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;


/**
 * @author genie
 *	@date 2023. 4. 11. - 오전 10:52:53
 * @subject
 * @content
 */
public class Ex01_02 {

   public static void main(String[] args) throws ClassNotFoundException, SQLException {

     
      Connection conn  = DBConn.getConnection() ;
      Statement stmt = null;
      ResultSet rs = null;

      ArrayList<EmpDTO> list = null;
      int searchCondition = 1;
      String searchWord = null;
      Scanner scanner = new Scanner(System.in);

      
      // 3. CRUD
      System.out.print("> 검색조건, 검색어 입력 ? ");
      searchCondition = scanner.nextInt();
      searchWord = scanner.next();
      String sql = " SELECT * "
            + " FROM emp ";
      if ( searchCondition == 1 ) {
         // sql += " WHERE ename LIKE '%UPPER("+ searchWord +")%'";
         sql += " WHERE ename LIKE '%"+  searchWord.toUpperCase() +"%'";
      }else if (searchCondition == 2 ) {
         sql += " WHERE job LIKE '%"+ searchWord.toUpperCase() +"%'";
      }else {
         sql += " WHERE deptno IN ( " + searchWord + " )";
      }
      System.out.println( sql ); // 쿼리 확인
      stmt = conn.createStatement();          
      rs = stmt.executeQuery(sql); // select 문

      if(  rs.next() ) {         
         list = new ArrayList<EmpDTO>();

         do {
            int empno = rs.getInt( "empno" );    
            String ename = rs.getString("ename"); 
            String job = rs.getString("job");   
            int mgr = rs.getInt("mgr");
            Date hiredate = rs.getDate("hiredate");   
            double sal = rs.getDouble("sal");  
            double comm = rs.getDouble("comm");  
            int deptno = rs.getInt("deptno");         
            EmpDTO dto = new EmpDTO(empno, ename, job, mgr, hiredate, sal, comm, deptno);         
            list.add(dto);
         } while (rs.next()); 

      } // if

      dispEmp(  list );

      // 4. Connection 객체 닫기
      rs.close();
      stmt.close();	
      DBConn.close();		//*********

   } // main

   private static void dispEmp(ArrayList<EmpDTO> list) {
      
      Iterator<EmpDTO> ir = list.iterator();
      while (ir.hasNext()) {
         EmpDTO dto =  ir.next();
         System.out.println(  dto  );
      }
            
   }

} // class




