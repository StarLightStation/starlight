package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//JDBC와 관련된 1,2 => connect()
//4 코드를 "모듈화" => disconnect()
//-> 별도의 클래스로 캡슐화하는 것이 더 유리함!

//-> 공통의 로직을 모듈화한 클래스
// == Util 클래스

// 장점
//  : 유지보수 용이
//  : 코드 재사용성이 증가
//  : 중복코드 최소화
//  : 오류의 파급효과 줄어듦
//  : 개발 시간,비용 단축
//  : 영업이익증가
public class JDBCUtil {

	static final String driverName_MySQL="com.mysql.cj.jdbc.Driver";
	static final String url_MySQL="jdbc:mysql://localhost/hwandb";
	static final String userName="root";
	static final String passwd="1234";

	public static Connection connect() {
		Connection conn=null;
		try {
			Class.forName(driverName_MySQL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn=DriverManager.getConnection(url_MySQL, userName, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void disconnect(PreparedStatement pstmt,Connection conn) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void disconnect(ResultSet rs,PreparedStatement pstmt,Connection conn) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}