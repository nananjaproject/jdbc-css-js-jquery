package days03.board;

import java.sql.SQLException;
import java.util.ArrayList;

public class BoardService {

	private BoardDAO dao = null;

	public BoardService() {}
	public BoardService(BoardDAO dao) {
		this.dao = dao;
	}

	//1. 게시글 목록 조회하는 서비스 메서드
	public ArrayList<BoardDTO> selectService(){
		ArrayList<BoardDTO> list = null;
		// 1) 로그 기록
		// 2) list = dao.select()
		try {
			System.out.println("> 로그 기록 : 게시글 목록 조회...");
			list = this.dao.select();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// 3) _
		return list;
	}
	public int insertService(BoardDTO dto) {
		int rowCount = 0;
		System.out.println("> 로그 기록 : 게시글 새글쓰기");
		try {
			rowCount = this.dao.insert(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}

	public BoardDTO viewService(int seq) {
		BoardDTO dto = null;
		System.out.println("> 로그 기록 : 게시글 상세보기" + seq);
		try {
			//this.dao.getConn() ?
			((BoardDAOImpl)this.dao).getConn().setAutoCommit(false);
			// 해당 게시글의 조회수를 1증가키시는 작업
			this.dao.increaseReaded(seq);	//UPDATE
			dto = this.dao.view(seq);		//SELECT
			((BoardDAOImpl)this.dao).getConn().commit();	//트2
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				((BoardDAOImpl)this.dao).getConn().rollback();	//트3
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return dto;

	}
	public int deleteService(int seq) {
		int rowCount = 0;

		System.out.println("> 로그 기록 : 게시글 삭제하기.." + seq);
		try {
			rowCount = this.dao.delete(seq);		//SELECT
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount;
	}
	public ArrayList<BoardDTO> searchService(int searchCondition, String searchWord) {
		ArrayList<BoardDTO> list = null;

		try {
			System.out.println("> 로그 기록 : 게시글 검색.");
			list = this.dao.search(searchCondition, searchWord);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;
	}

	public int updateService(BoardDTO dto) {
		int rowCount = 0;

		try {
			System.out.println("> 로그 기록 : 게시글 수정.");
			rowCount = this.dao.update(dto);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return rowCount;
	}
	public ArrayList<BoardDTO> selectService(int currentPage, int numberPerPage) {
		ArrayList<BoardDTO> list = null;
		// 1) 로그 기록
		// 2) list = dao.select()
		try {
			System.out.println("> 로그 기록 : 게시글 목록 조회...");
			list = this.dao.select(currentPage, numberPerPage);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// 3) _
		return list;
	}

	// "\t\t[1] 2 3 4 5 6 7 8 9 10" 반환하는 메서드
	public String pageService(int currentPage, int numberPerPage, int numberOfPageBlock   ) {
		String pagingBlock ="\t\t";


		int totalPages;
		try {
			totalPages = this.dao.getTotalPages(numberPerPage);
			int start = (currentPage -1) /numberOfPageBlock * numberOfPageBlock +1 ;

			int end= start + numberOfPageBlock -1;
			end = end > totalPages ? totalPages:end;

			if(start != 1) pagingBlock +="<";

			for (int j= start; j<=end; j++) {
				pagingBlock += String.format(currentPage ==j?"[%d]":"%d",j);
			}
			if( end != totalPages )pagingBlock += ">";

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return pagingBlock;
	}
	
	public ArrayList<BoardDTO> searchService(
	         int currentPage, int numberPerPage
	         , int searchCondition,   String searchWord) {
	      
	      ArrayList<BoardDTO>  list = null;

	      try {
	         System.out.println("> 로그 기록  : 게시글 검색...");
	         list =  this.dao.search(currentPage,numberPerPage,  searchCondition, searchWord);
	      } catch (SQLException e) { 
	         e.printStackTrace();
	      }   
	      return list;
	      
	   }
	public String pageService(
			int currentPage
			, int numberPerPage
			, int numberOfPageBlock
			,int searchCondition,
			String searchWord) {
		
		String pagingBlock ="\t\t";


		int totalPages;
		try {
			totalPages = this.dao.getTotalPages(numberPerPage, searchCondition, searchWord);
			int start = (currentPage -1) /numberOfPageBlock * numberOfPageBlock +1 ;

			int end= start + numberOfPageBlock -1;
			end = end > totalPages ? totalPages:end;

			if(start != 1) pagingBlock +="<";

			for (int j= start; j<=end; j++) {
				pagingBlock += String.format(currentPage ==j?"[%d]":"%d",j);
			}
			if( end != totalPages )pagingBlock += ">";

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return pagingBlock;


	}
	
	
}
