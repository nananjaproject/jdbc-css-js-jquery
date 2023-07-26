package days03.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.util.DBConn;

// 메뉴- 선택 -> BoardService -> BoardDAO -> DB 처리

public class BoardController {

	private Scanner scanner = null;
	private int selectedNumber; // 선택된 메뉴 저장 변수
	private BoardService service = null;

	public BoardController() {
		this.scanner = new Scanner(System.in);
	}

	public BoardController(BoardService service) {
		this();
		this.service = service;
	}

	public void start() {
		while (true) {
			메뉴출력();
			메뉴선택();
			메뉴처리();
			목록보기();

		}
	}

	private void 메뉴처리() {
		
		switch (this.selectedNumber) {
		case 1: // 새글
			새글쓰기();

			break;
		case 2: // 목록
			목록보기();

			break;
		case 3: // 보기
			상세보기();
			break;
		case 4: // 수정
			수정하기();

			break;
		case 5: // 삭제
			//삭제하기(); -> ??? service.deleteService(??)-> ??? dao.delete(???)
			삭제하기();
			break;
		case 6: // 검색
			검색하기();

			break;
		case 7: // 종료
			exit();
			break;

		}

	}

	private void 수정하기() {
		System.out.print( "> 수정할 게시글 번호(seq)를 입력? ");
		int seq = this.scanner.nextInt();
		
		BoardDTO dto = this.service.viewService(seq);
		// 해당 게시글 정보 출력(View 담당)
		System.out.println("\tㄱ. 글번호 : " + seq );
		System.out.println("\tㄴ. 작성자 : " + dto.getWriter() );
		System.out.println("\tㄷ. 조회수 : " + dto.getReaded() );
		System.out.println("\tㄹ. 글제목 : " + dto.getTitle() );
		System.out.println("\tㅁ. 글내용 : " + dto.getContent() );
		System.out.println("\tㅂ. 작성일 : " + dto.getWritedate() );
	
		// 수정할 게시글 정보를 입력...: 제목, 내용, 이메일, 
		System.out.print("> 수정할 제목 : ");
		String title = this.scanner.next();
		System.out.print("> 수정할 내용 : ");
		String content = this.scanner.next();
		System.out.print("> 수정할 이메일 : ");
		String email = this.scanner.next();
		
		dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setEmail(email);
		dto.setSeq(seq);
		
		int rowCount = this.service.updateService( dto );
		
		if( rowCount == 1) {
			System.out.println("> 수정완료 ~");
		}
		
		일시정지();
		
	}

	private void 검색하기() {
		System.out.print("> 검색 현재 페이지(currentPage) 입력 ? ");
		this.currentPage = this.scanner.nextInt();

		
		// 1.제목 2.내용 3. 작성자 4. 제목+내용
		int searchCondition;
		String searchWord;
		
		System.out.print("> 1.제목 2.내용 3. 작성자 4. 제목+내용  검색 조건 입력 ? ");
		searchCondition = this.scanner.nextInt();
		System.out.print(">검색어 입력? ");
		searchWord = this.scanner.next();
		
		ArrayList<BoardDTO> list = this.service.searchService(
				this.currentPage, this.numberPerPage,
				searchCondition,searchWord);
		
		// 뷰(View)  -출력 담당
		System.out.println("\t\t\t  게시판");
	    System.out.println("--------------------------------------------------------------");
	    System.out.printf("%s\t%-40s\t%s\t%-10s\t%s\n", 
	            "글번호","글제목","글쓴이","작성일","조회수");
	    System.out.println("--------------------------------------------------------------");
	      
	    System.out.println("--------------------------------------------------------------");
	      
		if(list == null ) {
			System.out.println("\t\t 게시글 존재 X");
		} else {
			Iterator<BoardDTO> ir = list.iterator();
			while(ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.printf("%d\t%-30s  %s\t%-10s\t%d\n",
		                  dto.getSeq(), 
		                  dto.getTitle(),
		                  dto.getWriter(),
		                  dto.getWritedate(),
		                  dto.getReaded());   
			}//while
		}//if
		System.out.println("-------------------------------------------------------------");
		//System.out.println("\t\t[1] 2 3 4 5 6 7 8 9 10");
		String pagingBlock = this.service.pageService(
						this.currentPage
						, this.numberPerPage
						, this.numberOfPageBlock
						,searchCondition
						,searchWord						
				);
		System.out.println(pagingBlock);
		
		System.out.println("-------------------------------------------------------------");
		일시정지();
		
	}

	private void 삭제하기() {
		System.out.print( "> 삭제할 게시글 번호(seq)를 입력? ");
		int seq = this.scanner.nextInt();
		int rowCount = this.service.deleteService(seq);
		
		if( rowCount == 1) {
			System.out.println("> 삭제 완료~~~~");
			
		}
		일시정지();
	}

	private void 상세보기() {
		System.out.print(">게시글 번호(seq)를 입력?");
		int seq = this.scanner.nextInt();
		
		BoardDTO dto = this.service.viewService(seq);
		// 해당 게시글 정보 출력(View 담당)
		System.out.println("\tㄱ. 글번호 : " + seq );
		System.out.println("\tㄴ. 작성자 : " + dto.getWriter() );
		System.out.println("\tㄷ. 조회수 : " + dto.getReaded() );
		System.out.println("\tㄹ. 글제목 : " + dto.getTitle() );
		System.out.println("\tㅁ. 글내용 : " + dto.getContent() );
		System.out.println("\tㅂ. 작성일 : " + dto.getWritedate() );
	
		System.out.println("\t\n [수정] [삭제] [목록(home)]");

		일시정지();
	}

	private void 새글쓰기() {
		System.out.println("> writer, pwd, email, title, tag, content 입력 ?");
		// "홍길동,1234,hong@naver.com,첫번째_게시글,1,첫번째_게시글내용"
		String [] datas = this.scanner.next().split(",");
		String writer = datas[0];
		String pwd = datas[1];
		String email = datas[2];
		String title = datas[3];
		int tag = Integer.parseInt(datas[4]);
		String content = datas[5];
		
		BoardDTO dto = new BoardDTO			// DTO객체에서 가져온다?
				(0, writer, pwd, email, title, null, 0, tag, content);
		
		int rowCount = this.service.insertService(dto);
		
		if(rowCount == 1) {
			System.out.println(">새글 쓰기 완료 ~~~");
		}
		
		일시정지();
	}
	
	private int currentPage =1;
	private int numberPerPage = 10;
	private int numberOfPageBlock = 10;

	private void 목록보기() {
		
		// 1. 현재 페이지 입력
		// 2. 한 페이지 출력할 게시글 수 입력
		System.out.println("> 현재 페이지 (currentPage) 입력 ? ");
		this.currentPage = this.scanner.nextInt();
		
		ArrayList<BoardDTO> list = this.service.selectService(this.currentPage, this.numberPerPage);
		
		// 뷰(View)  -출력 담당
		System.out.println("\t\t\t  게시판");
	    System.out.println("--------------------------------------------------------------");
	    System.out.printf("%s\t%-40s\t%s\t%-10s\t%s\n", 
	            "글번호","글제목","글쓴이","작성일","조회수");
	    System.out.println("--------------------------------------------------------------");
	      
	    System.out.println("--------------------------------------------------------------");
	      
		if(list == null ) {
			System.out.println("\t\t 게시글 존재 X");
		} else {
			Iterator<BoardDTO> ir = list.iterator();
			while(ir.hasNext()) {
				BoardDTO dto = ir.next();
				System.out.printf("%d\t%-30s  %s\t%-10s\t%d\n",
		                  dto.getSeq(), 
		                  dto.getTitle(),
		                  dto.getWriter(),
		                  dto.getWritedate(),
		                  dto.getReaded());   
			}//while
		}//if
		System.out.println("-------------------------------------------------------------");
		// System.out.println("\t\t[1] 2 3 4 5 6 7 8 9 10");
		String pagingBlock = this.service.pageService(this.currentPage, this.numberPerPage, this.numberOfPageBlock);
		System.out.println(pagingBlock);
		
		System.out.println("-------------------------------------------------------------");
		일시정지();
	}

	private void 일시정지() {
		System.out.println(" \t\t 계속하려면 엔터치세요.");
		try {
			System.in.read();
			System.in.skip(System.in.available()); // 13, 10
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exit() {
		DBConn.close();
		System.out.println("\t\t\t  프로그램 종료!!!");
		System.exit(-1);
	}

	private void 메뉴선택() {
		System.out.print(">메뉴 선택하세요 ?");
		this.selectedNumber = this.scanner.nextInt();

	}

	private void 메뉴출력() {

		String[] menus = { "새글", "목록", "보기", "수정", "삭제", "검색", "종료" };
		System.out.println("[메뉴]");
		for (int i = 0; i < menus.length; i++) {
			System.out.printf("%d, %s\t", i + 1, menus[i]);
		}
		System.out.println();

	}

} // class
