package days04;

import java.util.Scanner;

/**
 * @author genie
 *	@date 2023. 4. 13. - 오전 9:03:39
 * @subject
 * @content
 */
public class Ex01 {

	public static void main(String[] args) {
		// [1] 페이지 : 1~10
		// [2] 페이지 : 11~20
		// [3] 페이지 : 21~30
		int currentPage;
		int numberPerPage;
		int begin;	//11
		int end;	//20
		Scanner scanner = new Scanner (System.in);
		System.out.println("> 현재페이지, 페이지당 갯수 입력 ? ");
		currentPage = scanner.nextInt();
		numberPerPage = scanner.nextInt();
		
		begin = ( currentPage -1 ) * numberPerPage + 1;
		end = begin + numberPerPage-1;
		
		System.out.printf( "> begin : %d, end : %d\n" , begin, end );
		// 보고자하는 페이지 : 3
		// 한 페이지에 출력할 게시글 수 : 10
		// 게시글 목록 출력.
		// BoardDAO의 select() 메서드 확인...	-> 수정

	}

}
