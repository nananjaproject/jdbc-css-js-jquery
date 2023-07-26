package days03.board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAOImpl implements BoardDAO {

	private Connection conn;
	// getter
	public Connection getConn() {
		return conn;
	}

	
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BoardDAOImpl() {
		super();
	}

	// 생성자를 통해서 BoardService 전달(주입) DI
	public BoardDAOImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public ArrayList<BoardDTO> select(int currentPage, int numberPerPage) throws SQLException {
		ArrayList<BoardDTO> list = null;
		BoardDTO dto = null;
		
		int seq;				// select 를 할 때 저장할 변수들 선언.
		String writer;
		String email;
		String title;
		Date writedate;
		int readed;
		
		//  BETWEEN ? AND ? 여기에 들어갈 값 계산하는 변수
		int begin = ( currentPage -1 ) * numberPerPage + 1;
		int end = begin + numberPerPage-1;
		
		
		String sql = "SELECT * "
				+ "FROM ( "
				+ "    SELECT ROWNUM no , t.* "
				+ "    FROM ( "
				+ "            SELECT seq, writer, email, title, readed, writedate   "
				+ "            FROM cstvsboard  "
				+ "            ORDER BY seq DESC "
				+ "        )t "
				+ " "
				+ "    )m "
				+ "WHERE m.no BETWEEN ? AND ?";
		
		
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, begin);
		this.pstmt.setInt(2, end);
		this.rs = this.pstmt.executeQuery(); 			// this. 을 붙이는 이유는 클래스변수를 선언했기 떄문? 멤버 불러오려고

		if (rs.next()) {
			list = new ArrayList<BoardDTO>();
			do {
				seq = rs.getInt("seq");
				writer = rs.getString("writer");
				email = rs.getString("email");
				title = rs.getString("title");
				readed = rs.getInt("readed");
				writedate = rs.getDate("writedate");
				
				dto = new BoardDTO(seq, writer, email, title, writedate, readed);
				list.add(dto);
				
			} while (rs.next());
		} // if
		
		this.pstmt.close();

		return list;
	}
	//단위테스트
	@Override
	public ArrayList<BoardDTO> select() throws SQLException {
		ArrayList<BoardDTO> list = null;
		BoardDTO dto = null;
		
		int seq;				// select 를 할 때 저장할 변수들 선언.
		String writer;
		String email;
		String title;
		Date writedate;
		int readed;

		String sql = "SELECT seq, writer, email, title, readed, writedate " 
						+ "FROM cstvsboard " 
						+ "ORDER BY seq DESC";
		this.pstmt = this.conn.prepareStatement(sql);
		this.rs = this.pstmt.executeQuery(); 			// this. 을 붙이는 이유는 클래스변수를 선언했기 떄문? 멤버 불러오려고

		if (rs.next()) {
			list = new ArrayList<BoardDTO>();
			do {
				seq = rs.getInt("seq");
				writer = rs.getString("writer");
				email = rs.getString("email");
				title = rs.getString("title");
				readed = rs.getInt("readed");
				writedate = rs.getDate("writedate");
				
				dto = new BoardDTO(seq, writer, email, title, writedate, readed);
				list.add(dto);
				
			} while (rs.next());
		} // if
		
		this.pstmt.close();

		return list;
	} // select

	@Override
	public int insert(BoardDTO dto) throws SQLException {

		int rowCount = 0;
	      String sql = "INSERT INTO cstvsboard (seq, writer, pwd, email, title, tag, content) "
	            + "VALUES (SEQ_CSTVSBOARD.NEXTVAL, ?, ?, ? , ? , ? , ? )";
	      this.pstmt = this.conn.prepareStatement(sql);
	      this.pstmt.setString(1,  dto.getWriter() );
	      this.pstmt.setString(2,  dto.getPwd() );
	      this.pstmt.setString(3,  dto.getEmail() );
	      this.pstmt.setString(4,  dto.getTitle() );
	      this.pstmt.setInt(5, dto.getTag());
	      this.pstmt.setString(6,  dto.getContent() );
	      
	      rowCount = this.pstmt.executeUpdate(); 
	      this.pstmt.close();
	      return rowCount;
	}

	@Override
	public BoardDTO view(int seq) throws SQLException {
		String sql = "SELECT seq,  writer, email, title, readed , writedate , content " + " FROM cstvsboard "
				+ " WHERE seq =  ?  ";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, seq); // WHERE seq = ?
		this.rs = this.pstmt.executeQuery();

		BoardDTO dto = null;
		if (this.rs.next()) {
			dto = new BoardDTO();
			dto.setSeq(seq);
			dto.setWriter(this.rs.getString("writer"));
			dto.setEmail(this.rs.getString("email"));
			dto.setTitle(this.rs.getString("title"));
			dto.setReaded(this.rs.getInt("readed"));
			dto.setWritedate(this.rs.getDate("writedate"));
			dto.setContent(this.rs.getString("content"));
		} //

		return dto;

	}

	@Override
	public void increaseReaded(int seq) throws SQLException {
		String sql = " UPDATE cstvsboard "
					+" SET readed = readed + 1"
					+"WHERE seq = ? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1,seq);
		int rowCount = this.pstmt.executeUpdate();
		this.pstmt.close();
		
	}

	@Override
	public int delete(int seq) throws SQLException {
		String sql = " DELETE FROM cstvsboard " 
						+ "WHERE seq = ? ";
		
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, seq);
		int rowCount = this.pstmt.executeUpdate();
		this.pstmt.close();
		return rowCount;
	}

	@Override
	public ArrayList<BoardDTO> search(int searchCondition, String searchWord) throws SQLException {
		ArrayList<BoardDTO> list = null;
		BoardDTO dto = null;
		
		int seq;				// select 를 할 때 저장할 변수들 선언.
		String writer;
		String email;
		String title;
		Date writedate;
		int readed;

		String sql = " SELECT seq, writer, email, title, readed, writedate " 
						+ " FROM cstvsboard " ;
		//	검색 조건, 검색어 WHERE 조건절만 추가...
		switch (searchCondition) {
		case 1: //제목
			sql += " WHERE REGEXP_LIKE(title, ?, 'i') ";		// i는 대소문자 구분하지 않고 검색하겠다는 뜻
			break;
		case 2: //내용
			sql += " WHERE REGEXP_LIKE(content, ?, 'i') ";
			break;
		case 3: //작성자
			sql += " WHERE REGEXP_LIKE(writer, ?, 'i') ";
			break;
		case 4: //제목 + 내용
			sql += " WHERE REGEXP_LIKE(title, ?, 'i')  OR REGEXP_LIKE(content, ?, 'i')";
			break;
		}
		
		sql+= "ORDER BY seq DESC";
		this.pstmt = this.conn.prepareStatement(sql);
		// 1,2,3, ?
		
		//4		 ?,?
		this.pstmt.setString(1, searchWord);
		 if( searchCondition == 4) this.pstmt.setString(2, searchWord); 
		 
		this.rs = this.pstmt.executeQuery(); 			// this. 을 붙이는 이유는 클래스변수를 선언했기 떄문? 멤버 불러오려고

		if (rs.next()) {
			list = new ArrayList<BoardDTO>();
			do {
				seq = rs.getInt("seq");
				writer = rs.getString("writer");
				email = rs.getString("email");
				title = rs.getString("title");
				readed = rs.getInt("readed");
				writedate = rs.getDate("writedate");
				
				dto = new BoardDTO(seq, writer, email, title, writedate, readed);
				list.add(dto);
				
			} while (rs.next());
		} // if
		
		this.pstmt.close();

		return list;
	}

	@Override
	public int update(BoardDTO dto) throws SQLException {
		String sql = "UPDATE cstvsboard "
						+ "SET title=?, email=?, content=? "
						+ "WHERE seq =? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, dto.getTitle());
		this.pstmt.setString(2, dto.getEmail());
		this.pstmt.setString(3, dto.getContent());
		this.pstmt.setInt(4, dto.getSeq());
		int rowCount = this.pstmt.executeUpdate();
		this.pstmt.close();
		return rowCount;
	}

	@Override
	public int getTotalPages(int numberPerPage) throws SQLException {
		String sql = "SELECT  COUNT(*) n,   CEIL( COUNT(*) / ? ) m " + " FROM  cstvsboard";
		int totalPages = 0;

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, numberPerPage);
		this.rs = this.pstmt.executeQuery();

		this.rs.next();
		totalPages = rs.getInt("m");

		this.rs.close();
		this.pstmt.close();

		return totalPages;
	}

	//페이징 처리 + 검색
		@Override
		public ArrayList<BoardDTO> search(
				int currentPage, int numberPerPage
				, int searchCondition, String searchWord)
						throws SQLException {

			ArrayList<BoardDTO> list = null;
			BoardDTO dto = null;


			int seq;     
			String writer;    
			String email;       
			String title;
			Date writedate;         
			int readed;       

			
			//BETWEEN ? AND ?
			int begin = (currentPage -1) * numberPerPage +1;
			int end = begin + numberPerPage-1;
			String sql = "SELECT * "
					+ "FROM ( "
					+ "    SELECT ROWNUM no, t.* "
					+ "    FROM ( "
					+ "            SELECT seq, writer, email, title, readed, writedate "
					+ "            FROM cstvsboard  "; 
			//검색 조건,검색어 WHERE 조건절만 추가.. 
			switch (searchCondition) {
			case 1: //제목
				sql += "WHERE REGEXP_LIKE(title, ?, 'i' ) "; // i의 뜻? 대소문자 구분하지 않고 검색하겠다
				break;
			case 2: //내용
				sql += "WHERE REGEXP_LIKE(content, ?, 'i' ) ";
				break;
			case 3: //작성자
				sql += "WHERE REGEXP_LIKE(writer, ?, 'i' ) ";
				break;
			case 4: //제목 + 내용
				sql += "WHERE REGEXP_LIKE(title, ?, 'i' ) OR REGEXP_LIKE(content, ?, 'i' )";
				break;
			}
			
			sql += "            ORDER BY seq DESC "
			+ "        ) t  "
			+ "    ) m "
			+ "WHERE m.no BETWEEN ? AND ? ";
			
			//
			this.pstmt = this.conn.prepareStatement(sql);
			
			this.pstmt.setString(1, searchWord);
			
			if(searchCondition ==4) {// ? ? ? (begin) ? (end)
				this.pstmt.setString(2, searchWord);
				this.pstmt.setInt(3, begin);
				this.pstmt.setInt(4, end);
			}else {
				this.pstmt.setInt(2, begin);
				this.pstmt.setInt(3, end);
			}

			this.rs = pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList<BoardDTO>();
				do {
					seq = rs.getInt("seq");
					writer = rs.getString("writer");
					email = rs.getString("email");
					title = rs.getString("title");
					readed = rs.getInt("readed");
					writedate = rs.getDate("writedate");

					dto = new BoardDTO(seq, writer, email, title, writedate, readed);
					list.add(dto);
				} while (rs.next());
			}//if

			this.pstmt.close();
			this.rs.close();

			return list;
		}

	@Override
	public int getTotalPages(int numberPerPage, int searchCondition, String searchWord) throws SQLException {
		String sql = "SELECT  COUNT(*) n,   CEIL( COUNT(*) / ? ) m " 
				+ " FROM  cstvsboard ";
		switch (searchCondition) {
		case 1: //제목
			sql += " WHERE REGEXP_LIKE(title, ?, 'i' ) "; // i의 뜻? 대소문자 구분하지 않고 검색하겠다
			break;
		case 2: //내용
			sql += " WHERE REGEXP_LIKE(content, ?, 'i' ) ";
			break;
		case 3: //작성자
			sql += " WHERE REGEXP_LIKE(writer, ?, 'i' ) ";
			break;
		case 4: //제목 + 내용
			sql += " WHERE REGEXP_LIKE(title, ?, 'i' ) OR REGEXP_LIKE(content, ?, 'i' )";
			break;
		}

		
		int totalPages = 0;

		this.pstmt =  this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, numberPerPage);
		this.pstmt.setString(2, searchWord);
		if(searchCondition == 4) {
			this.pstmt.setString(3, searchWord);
		}
		
		
		this.rs =  this.pstmt.executeQuery();

		this.rs.next();
		totalPages = rs.getInt("m");

		this.rs.close();
		this.pstmt.close();

		return totalPages;
	}
		
	

} // class
