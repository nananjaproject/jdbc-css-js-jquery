package days03.board;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BoardDAO {
	
	// 1. 추상메서드 - 게시글 목록 조회
	ArrayList<BoardDTO> select() throws SQLException;
	// 1. 추상메서드 - 게시글 목록 조회 +  페이징 처리 구현
		ArrayList<BoardDTO> select(int currentPage, int numberPerPage ) throws SQLException;
	
	// 2. 추상메서드 - 게시글 새글 쓰기
	int insert(BoardDTO dto) throws SQLException;
	
	// 3. 상세보기
	BoardDTO view(int seq) throws SQLException ;
	
	// 4. 조회수 증가
	void increaseReaded(int seq) throws SQLException;
	
	// 5. 삭제하기
	int delete(int seq) throws SQLException;
	
	// 6. 검색
	ArrayList<BoardDTO> search(int searchCondition, String searchWord) throws SQLException;
	
	// 6-2 검색하기 + 페이징 처리
	ArrayList<BoardDTO> search(int currentPage, int numberPerPage, 
			int searchCondition, String searchWord) throws SQLException ;
	
	// 7. 수정하기 추상메서드
	int update(BoardDTO dto) throws SQLException;
	
	// 8. 총페이지수를 반환하는 메서드
	int getTotalPages(int numberPerPage) throws SQLException ;
	
	// 8-2 검색 + 총페이지수를 반환하는 메서드
	int getTotalPages(int numberPerPage, int searchCondition, String searchWord) throws SQLException;
	
	


	
}
