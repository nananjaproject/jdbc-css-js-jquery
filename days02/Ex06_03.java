package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

import days01.DeptDTO;



/**
 * @author genie
 *	@date 2023. 4. 11. - 오후 3:53:35
 * @subject		pstmt 사용해서  dept 테이블  수정(UPDATE)
 * @content
 */
public class Ex06_03 {

   public static void main(String[] args) {
       Connection conn = null;
       conn =  DBConn.getConnection();
      
      // 1. 검색  ( 부서명, 지역명 ) -> list 반환
      ArrayList<DeptDTO> list = searchDept(conn);
      if( list.size() == 0 ) {
         System.out.println( "검색 결과는 없다. ");
         return ;
      }
      
      // 검색 결과 출력...
      int 검색결과갯수 =  list.size();
      System.out.println("검색결과 갯수 : " + 검색결과갯수);
      Iterator<DeptDTO> ir = list.iterator();
      while (ir.hasNext()) {
         DeptDTO dto =  ir.next();
         System.out.println(  dto );
      }
            
      // 2. 수정
      // System.out.println(  conn.isClosed() );
      //
      int rowCount = updateDept( conn );
      
      if( rowCount == 1 ) {
         System.out.println("부서 수정 성공~");
      }
       
      DBConn.close();
      System.out.println(" end ");

   } // main

   private static int updateDept(Connection conn) {
      int pdeptno = 50;
      String pdname = "X";
      String ploc = "Y";
      
      /*
      if( conn.isClosed() ) {
         conn = DBConn.getConnection();
      }
      */
      
      PreparedStatement pstmt = null;
      String sql = "UPDATE dept"
                       + " SET dname = ? , loc = ? "
                       + " WHERE deptno = ? ";
      int rowCount = 0;
      try {
         
         pstmt =  conn.prepareStatement(sql);
         // ? ? ? 파라미터값 설정
         pstmt.setString(1, pdname);
         pstmt.setString(2, ploc ) ;
         pstmt.setInt(3, pdeptno);
         rowCount = pstmt.executeUpdate();
         
      } catch (SQLException e) { 
         e.printStackTrace();
      } 
      
      return rowCount ;
   }

   private static ArrayList<DeptDTO> searchDept(Connection conn) {
      System.out.print("> 검색조건,  검색어 입력 ? ");
      Scanner scanner = new Scanner(System.in);
      int searchCondition = scanner.nextInt();  // 검색조건 1,2
      String searchWord = scanner.next();   // 검색어
      
      String sql = " SELECT * "
                      + " FROM dept ";
      if( searchCondition == 1 ) {
         sql += " WHERE dname LIKE ?";   //    '%검색어%'
      }else {
         sql += " WHERE loc LIKE ?";
      }      
      
      ResultSet rs = null;
      PreparedStatement pstmt = null;
      ArrayList<DeptDTO> list = new ArrayList<DeptDTO>();
      
      try {
         pstmt = conn.prepareStatement(sql);
         // ? 파라미터
         pstmt.setString(1, "%" + searchWord + "%");
         rs = pstmt.executeQuery();
         while ( rs.next() ) {
             int deptno = rs.getInt("deptno");
             String dname = rs.getString("dname");
             String loc = rs.getString("loc");
             list.add( new DeptDTO(deptno, dname, loc) ) ;
         } // while
      } catch (SQLException e) { 
         e.printStackTrace();
      } finally {
         try {
            rs.close();
            pstmt.close();
         } catch (SQLException e) { 
            e.printStackTrace();
         }
      
      }
      
      return list;
   }

} // class



















