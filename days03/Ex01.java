package days03;

import java.sql.Connection;

import com.util.DBConn;

import days03.board.BoardController;
import days03.board.BoardDAO;
import days03.board.BoardDAOImpl;
import days03.board.BoardService;
import days03.board.BoardService;

public class Ex01 {

	public static void main(String[] args) {
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		BoardService service = new BoardService(dao);
		BoardController controller = new BoardController(service);
		
		controller.start();
		/*
		 * 1. cstvsboard 테이블 생성
		 * 		seq_sctvsboard 시퀀스 생성
		 * 
		 * 2. 익명 프로시저를 사용해서 150개의 게시글 추가.
		 * 
		 * 		모델2방식(MVC 패턴)
		 * 		Model		로직 처리
		 * 		View			뷰
		 * 		Controller	컨트롤러
		 * 
		 * 3. BoardDTO 선언
		 * 		[M]
		 * 4. BoardDAO  인터페이스로 선언
		 * 		ㄴ BoardDAOImpl 클래스 선언
		 * 5. BoardService 선언
		 * 		홍길동 -> 로그인 -> [게시글 작성]
		 * 									1) 로그 기록
		 * 									2) INSERT
		 * 									3) 작성자 포인트 1증가 UPDATE
		 * 									등등
		 * 	[C + V]
		 * 6. BoardController 선언
		 * 7. Ex01.java - main(){ 시작 ~ 종료 }
		 * 
		 * Ctrl + F11 실행
		 * 		ㄴ Ex01.java - main(){ 시작~종료 }
		 * 		ㄴ BoardController - 메뉴 출력, 선택
		 * 				ㄴ BoardService(c. 선택된 메뉴의 서비스 메서드 호출
		 * 						ㄴ  BoardDAO(conn)nn)
		 * 								ㄴ Oracle 서버
		 * 
		 * 
		 * 
		 */

	}

}
/* MS SQL Server
create table cstVSBoard (
		  seq int identity (1, 1) not null primary key clustered,
		  writer varchar (20) not null ,
		  pwd varchar (20) not null ,
		  email varchar (100) null ,
		  title varchar (200) not null ,
		  writedate smalldatetime not null default (getdate()),
		  readed int not null default (0),
		  mode tinyint not null ,
		  content text null
		)
*/

/* Oracle
CREATE SEQUENCE seq_cstVSBoard

CREATE TABLE cstVSBoard 
(
		  seq NUMBER NOT NULL PRIMARY KEY,
		  writer VARCHAR2(20) NOT NULL ,
		  pwd VARCHAR2 (20) NOT NULL ,
		  email VARCHAR2 (100),
		  title VARCHAR2 (200) NOT NULL ,
		  writedate DATE  DEFAULT SYSDATE,
		  readed NUMBER DEFAULT 0,
		  mode NUMBER(1) NOT NULL ,
		  content NCLOB
)
*/

/*
 * 게시판 
 * 1. 테이블 , 시퀀스 
 * 2. BoardDTO 
 * 3. BoardDAO 
 * 		BoardDAOImpl 
 * 				ㄴArrayList<BoardDTO>
 * 		select() { 
 * // 
 * // 
 * 		return list;
 *  	}
 *   }
 */


/*
 * [ 검색하기 + 페이징 처리]
 * 1. BC
 * 
 * 
 * 
 * 
 * 
 */











