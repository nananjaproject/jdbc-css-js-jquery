package days01;

import java.sql.Connection;
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
 *	@date 2023. 4. 10. - 오후 3:01:45
 * @subject	dept 테이블 수정(UPDATE)
 * @content
 * 					수정, 삭제하기 전에 검색 후...
 * 					지역명, 부서명 선택 후 검색
 * 					수정~
 */
public class Ex03_5 {

	   public static void main(String[] args) {
	      // 1. 검색   ArrayList<DeptDTO> 저장.
	      ArrayList<DeptDTO> list = searchDept();
	      if(list == null) {
	         System.out.println("검색 결과는 없다.");
	         return;
	      }

	      //검색 결과 출력
	      int 검색결과갯수 = list.size();
	      System.out.println("검색 결과 갯수:" + 검색결과갯수);
	      Iterator<DeptDTO> ir = list.iterator();
	      while( ir.hasNext()) {
	         DeptDTO dto = ir.next();
	         System.out.println(dto);
	      }
	      
	      //수정
	      //1. 수정할 부서번호 선택(입력)
	      Scanner scanner = new Scanner(System.in);
	      System.out.println("수정할 부서번호 입력?"); //50
	      int deptno = scanner.nextInt();

	      //2. 수정할 부서명, 지역명 입력
	      System.out.println("> 수정할 부서명, 지역명 입력");// X Y
	      String dname = scanner.next();
	      String loc = scanner.next();
	      
	            //Ex03.java main 복사 + 붙이기
	          // Ex03.java         dept 테이블 조회 (SELECT)
	          String className = "oracle.jdbc.driver.OracleDriver";
	          String url = "jdbc:oracle:thin:@localhost:1521:xe";
	            String user = "scott";
	            String password = "tiger";
	            Connection conn = null;
	            Statement stmt = null;
	            
	            String sql = String.format(
	                  "UPDATE dept"
	                  + " SET dname = '%s',loc ='%s'"
	                  + " WHERE deptno = %d"
	                     ,dname, loc, deptno);
	            
	            System.out.println(sql); //출력확인
	                  
	               
	          try {
	             // 1.
	             Class.forName(className);
	             // 2.
	             conn = DriverManager.getConnection(url,user,password);
	             // 3. CRUD 작업
	             stmt = conn.createStatement();
	            
	           // stmt.executeQuery(sql); //SELECT문 실행할 때
	           int rowCount = stmt.executeUpdate(sql); // INSERT, UPDATEm DELECT 문 실행할 때 사용
	            
	           if(rowCount == 1) {
	              System.out.println("부서 정보 수정 완료!!!");
	           }
	             
	          } catch (ClassNotFoundException e) {
	             e.printStackTrace();
	          } catch (SQLException e) {
	             e.printStackTrace();
	          }finally {
	             try {
	                // 4.
	                stmt.close();
	                conn.close();
	             } catch (SQLException e) {
	                e.printStackTrace();
	             }
	          }
	          
	          
	          
	      
	      
	   }   //main

	   private static ArrayList<DeptDTO> searchDept() {
	      ArrayList<DeptDTO> list = null;
	      
	      int searchCondition;   //검색조건      1(부서명), 2(지역명)
	      String searchWord;   //검색어
	      Scanner scanner = new Scanner(System.in);
	      
	      System.out.printf(">검색조건, 검색어 입력하세요 ? ");
	      searchCondition = scanner.nextInt();   //2
	      searchWord = scanner.next();            //EOU
	      
	      // Ex03.java 복사 + 붙이기
	     
	      Connection conn = DBConn.getConnection() ;
	      Statement stmt = null;
	      ResultSet rs = null;

	      String sql = "SELECT * " 
	                  + "FROM dept ";
	      if(searchCondition == 1) {
	         sql += "WHERE REGEXP_LIKE(dname, '"+ searchWord+"','i')";
	      }else {                     //2
	         sql += String.format(
	                              "WHERE REGEXP_LIKE(loc, '%s','i')"
	                              , searchWord
	                              );
	      }
	      sql += " ORDER BY DEPTNO ASC ";

	      try {
	

	         // 3. CRUD 작업
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(sql); 
	         
	         // rs.next();   다음 레코드(행)이 있니? false
	         // 다음 레코드로 이동을 시킨 후 있으면 true, 없으면 false를 반환하는 메서드
	         if(rs.next()) {
	            list = new ArrayList<DeptDTO>();
	            
	            int deptno;
	            String dname, loc;
	            DeptDTO dto;
	            
	            do {
	               deptno = rs.getInt("deptno");
	               dname = rs.getString("dname");
	               loc = rs.getString("loc");
	               
	               dto = new DeptDTO(deptno, dname, loc);
	               list.add(dto);         // Arraylist에 dto객체 넣기.
	            }while(rs.next());

	         }//if
	         
	    
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            // 4.
	            stmt.close();
	            DBConn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	               
	      return list;
	   }

	}   //class