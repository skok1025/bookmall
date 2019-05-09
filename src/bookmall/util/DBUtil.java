package bookmall.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	private static final String IP_ADDR = "192.168.1.42";
	/**
	 * DB 접속 메소드
	 * 
	 * @return connection DB연결객체
	 */
	public static Connection getConnection() {
		Connection conn = null;

		String url = "jdbc:mariadb://"+IP_ADDR+":3307/bookmall";
		String id = "bookmall";
		String pw = "bookmall";

		try {
			
			// 클래스 로딩	
			Class.forName("org.mariadb.jdbc.Driver");

			conn = DriverManager.getConnection(url, id, pw);

//			System.out.println("Database connected");
			return conn;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DBUtil.getConnection() : " + e.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;

	}

	/**
	 * 인자값을 받아 DB 접속하는 메소드
	 * 
	 * @param server 서버IP
	 * @param db 사용할 데이터베이스
	 * @param id     아이디
	 * @param pw     비밀번호
	 * @return connection DB연결객체
	 */
	public static Connection getConnection(String server,String db, String id, String pw) {

		Connection conn = null;

		String url = "jdbc:mariadb://"+server+":3307/"+db;

		try {

			Class.forName("org.mariadb.jdbc.Driver");

			conn = DriverManager.getConnection(url, id, pw);

			return conn;

		} catch (Exception e) {
			System.out.println("DBUtil.getConnection() : " + e.toString());
		}

		return null;

	}

}