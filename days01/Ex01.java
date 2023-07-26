package days01;


/**
 * @author genie
 *	@date 2023. 4. 10. - 오전 11:18:16
 * @subject alt+shift+J
 * @content
 */
public class Ex01 {

	public static void main(String[] args) {
		System.out.println("hello world");
		/*
		 * 1. JDBC ( [J]ava [D]ata[B]ase[C]onnectivity )	(시험)
		 * 	- 자바 표준 [인터페이스] : DBMS(오라클) 연결 + 작업(CRUD)
		 * 
		 * 2. 오라클(DBMS) 연동
		 * 	-오라클사에서 JDBC를 구현한 드라이버(Driver)를 다운로드 : Oracle Driver
		 * 		(JDBC 드라이버 종류 : [OracleDriver], MySQL Driver, MS SQL Driver)
		 * 3. JDBC Driver 종류
		 * 		1) Type1 - ODBC Driver 브릿지 사용 연동.
		 * 		2) Type2 - C, C++ 언어로 만든 라이브러리(Native Library)를 사용해서 DB 연동.
		 * 		3) Type3 - 미들웨어 서버
		 * 		4) *** Type4 *** - Thin 드라이버, 순수 자바로 만들어짐. 가장 많이 사용된다.
		 * 4. DBMS와 연결 방법  ( 시험 ) ***
		 * 			1) JDBC Driver 로딩 - Class.forName() 메서드
		 * 			2) Connection 객체를 얻어와야 된다.
		 * 				- DriverManager 클래스의 getConnection() 메서드를 얻어온다.
		 * 			3) 필요한 작업 - CRUD
		 * 				명령을 처리하는 객체 
		 * 				- 1) Statement 객체
		 * 					2) [Prepared] + Statement 객체 : 성능 빠르다.
		 * 						미리 준비된
		 * 					3) [Callable] + Statement 객체 : 저장 프로시저를 사용하는 명령 객체
		 * 
		 * 			4) 연결 종료 - Connection 객체 닫기(close) 
		 * 		
		 * 
		 */

	}//main

}//class



