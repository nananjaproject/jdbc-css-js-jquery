package days03.board.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import com.util.DBConn;

import days03.board.BoardDAO;
import days03.board.BoardDAOImpl;
import days03.board.BoardDTO;

class BoardDAOImplTest {
	
	// 11:02 수업시작
	@Test
	void select_test() {
		Connection conn = DBConn.getConnection();
		BoardDAO dao = new BoardDAOImpl(conn);
		try {
			ArrayList<BoardDTO> list = dao.select();
			
			Iterator<BoardDTO> ir = list.iterator();
			
			while (ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.println(dto);
			}//while
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			DBConn.close();
		}
		
		
	}

}
