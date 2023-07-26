package days02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.util.DBConn;

/**
 * @author genie
 *	@date 2023. 4. 11. - 오후 4:54:58
 * @subject
 * @content
 */
public class Ex07_02 {

   public static void main(String[] args) {
      
      String sql =   " SELECT grade  , losal,  hisal   , COUNT(*) cnt  "
                     + " FROM salgrade s JOIN emp e ON e.sal BETWEEN s.losal AND s.hisal "
                     + " GROUP BY grade  , losal,  hisal "
                     + " ORDER BY grade ASC";
      
      String sql2 = "SELECT d.deptno, dname, empno, ename, sal , grade "
            + "FROM dept d JOIN emp e ON d.deptno = e.deptno "
            + "            JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal "
            + "WHERE  grade = ?           "
            + "ORDER BY d.deptno ASC";
      Connection conn = null;
      PreparedStatement pstmt = null, pstmt2 = null;
      ResultSet rs = null, rs2 = null;
      // SalgradeDTO 선언
      ArrayList<SalgradeDTO> list = null;
      SalgradeDTO dto = null;
      
      conn =  DBConn.getConnection();
      try {
         pstmt = conn.prepareStatement(sql);
         rs =  pstmt.executeQuery();
         
         if ( rs.next()) {
            list = new ArrayList<SalgradeDTO>();
            do {
               int grade  = rs.getInt("grade") ;
               int losal   = rs.getInt("losal");
               int hisal   = rs.getInt("hisal");
               int  cnt     = rs.getInt("cnt");
               dto = new SalgradeDTO(grade, losal, hisal, cnt);
               list.add(dto);  //
               // 출력
               System.out.printf("%2d등급\t(   %4d ~ %d ) - %d명 \n"
                         , dto.getGrade()
                         , dto.getLosal()
                         , dto.getHisal()
                         , dto.getCnt()     );
               
               // 시작
               pstmt2 = conn.prepareStatement(sql2);
               pstmt2.setInt(1, grade);
               rs2 =  pstmt2.executeQuery();
               if( rs2.next()) {
                  do {
                     // d.deptno, dname, empno, ename, sal , grade
                     int deptno =  rs2.getInt("deptno");
                     String dname = rs2.getString("dname");
                     int empno = rs2.getInt("empno");
                     String ename = rs2.getString("ename");
                     double sal = rs2.getDouble("sal");
                     //          20   RESEARCH   7369   SMITH   800
                     System.out.printf("\t\t%d\t%s\t%d\t%s\t%f\n", 
                           deptno, dname, empno, ename, sal);
                  } while (rs2.next());
               }else {
                  System.out.println("\t\t 사원이 존재하지 않습니다.");
               }
               // 끝
            } while (rs.next());
         } // if
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            pstmt.close();            rs.close();            
            pstmt2.close();            rs2.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      DBConn.close();
      
      // printSalgrade( list );
      
      System.out.println( " end ");
   } // main

   private static void printSalgrade(ArrayList<SalgradeDTO> list) {
      Iterator<SalgradeDTO> ir = list.iterator();
      while (ir.hasNext()) {
         SalgradeDTO dto = ir.next();
         System.out.printf("%2d등급\t(   %4d ~ %d ) - %d명 \n"
                               , dto.getGrade()
                               , dto.getLosal()
                               , dto.getHisal()
                               , dto.getCnt()     );
      } // while
      
   }

} // class

/*
[실행결과]
[1]등급       (   700~1200 ) - 2명
         SELECT 쿼리 실행
         A
         B
[2]등급   (   1201~1400 ) - 2명
         X
         Y 
3등급   (   1401~2000 ) - 2명 
4등급   (   2001~3000 ) - 4명 
5등급   (   3001~9999 ) - 1명    
 */
/*
[실행결과]
1등급   (     700~1200 ) - 2명
         20   RESEARCH   7369   SMITH   800
         30   SALES         7900   JAMES   950
2등급   (   1201~1400 ) - 2명
      30   SALES   7654   MARTIN   2650
      30   SALES   7521   WARD      1750   
3등급   (   1401~2000 ) - 2명
      30   SALES   7499   ALLEN      1900
      30   SALES   7844   TURNER   1500
4등급   (   2001~3000 ) - 4명
       10   ACCOUNTING   7782   CLARK   2450
      20   RESEARCH   7902   FORD   3000
      20   RESEARCH   7566   JONES   2975
      30   SALES   7698   BLAKE   2850
5등급   (   3001~9999 ) - 1명   
   10   ACCOUNTING   7839   KING   5000
 */