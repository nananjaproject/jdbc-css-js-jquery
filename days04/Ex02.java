package days04;

public class Ex02 {

	public static void main(String[] args) {
		// [1] 2 3 4 5 6 7 8 9 10 >
		int currentPage = 2;
		int numberPerPage = 10;
		int numberOfPageBlock = 10;
		
		// 1 [2] 3 4 5 6 7 8 9 10 >
		// 1) 총 레코드 수? 151 	SELECT COUNT(*)FROM cstvsboard;
		// 2) 총 페이지 수? 16		SELECT COUNT(*), CEIL(COUNT(*)/10)FROM cstvsboard;
		int totalPages = 19;
		for( int i = 1; i <= 16; i++ ) {
			System.out.printf("%d 페이지\n", i);
			
			int start = (currentPage -1) /numberOfPageBlock * numberOfPageBlock +1 ;
	        int end= start + numberOfPageBlock -1;
	        end = end > totalPages ? totalPages:end;
	        
	        if(start != 1) System.out.print("<");
	         
			for (int j= start; j<=end; j++) {
				System.out.printf(i==j?"[%d]":"%d",j);
			}
			if( end != totalPages ) System.out.print(">");
			
			System.out.println();
		}
		/*
		 * 1 페이지	[1] 2 3 4 5 6 7 8 9 10 > 
			2 페이지
			3 페이지
			4 페이지
			5 페이지
			6 페이지
			7 페이지
			8 페이지
			9 페이지
			10 페이지		1 2 3 4 5 6 7 8 9 [10] >
			11 페이지		< [11] 12 13 14 15 16
			12 페이지		< 11 [12] 13 14 15 16
			13 페이지
			14 페이지
			15 페이지
			16 페이지		< 11 12 13 14 15 [16]
		 * 
		 */

	}

}
