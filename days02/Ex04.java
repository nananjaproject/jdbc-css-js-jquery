package days02;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import com.util.DBConn;

public class Ex04 {

	public static void main(String[] args) {
		// url, user, password 연결 문자열
		String dir = System.getProperty("user.dir");
		Properties p = new Properties();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<JobsDTO> list = null;

		String fileName = dir + "/src/com/util/ConnectionString.properties";
		try (FileReader reader = new FileReader(fileName)) {
			p.load(reader); // load() key, value Entry
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			String url = p.getProperty("url");
			conn = DBConn.getConnection(url, user, password); // 1, 2
			// System.out.println( conn );
			String sql = "SELECT REPLACE(job_id, 'RE', '[RE]' )  job_id "
					+ "    ,  REGEXP_REPLACE(job_title, '(RE|re|Re|rE)', '[\\1]')  job_title " + "FROM jobs "
					+ "WHERE REGEXP_LIKE( job_id, 'RE', 'i')  " + "      OR REGEXP_LIKE( job_title, 'RE', 'i')";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				list = new ArrayList<JobsDTO>();

				do {
					String job_id = rs.getString(1); // job_id
					String job_title = rs.getString(2); // job_title

					list.add(new JobsDTO(job_id, job_title, 0, 0));
				} while (rs.next());

			} // if

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConn.close(); // 4
		}

		// 출력….
		Iterator<JobsDTO> ir = list.iterator();
		while (ir.hasNext()) {
			JobsDTO dto = ir.next();
			System.out.println(dto);
		}

	}

}

/*
 * 2. HR의 jobs 테이블에서 job_id 컬럼 또는 job_title 컬럼에 'RE' 또는 're' 또는 'Re' 또는 'rE' (
 * 즉, 대소문자 구분 없이 ) 문자열을 포함하는 레코드를 검색해서 아래와 같이 출력하세요.
 * 
 * JOB_ID JOB_TITLE
 * -------------------------------------------------------------- AD_P[RE]S
 * P[re]sident AD_VP Administration Vice P[re]sident SA_[RE]P Sales
 * [Re]p[re]sentative MK_[RE]P Marketing [Re]p[re]sentative HR_[RE]P Human
 * [Re]sources [Re]p[re]sentative PR_[RE]P Public [Re]lations [Re]p[re]sentative
 * 
 * 6개 행이 선택되었습니다.
 * 
 */