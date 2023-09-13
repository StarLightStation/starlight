package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//	static final String SQL_SELECTALL = "SELECT SUBNUM, SUBNAME, SUBPRICE FROM SUBSCRIPTION";
//	구독 종류 정보 보여주기.

//	static final String SQL_SELECTONE = "SELECT SUBNUM, SUBNAME, SUBPRICE FROM SUBSCRIPTION WHERE SUBNUM = ?";
//	해당 하는 구독 정보 보여주기.

public class SubscriptionDAO {
	
	static final String SQL_INSERT = "";
	static final String SQL_SELECTALL = "SELECT SUBNUM, SUBNAME, SUBPRICE FROM SUBSCRIPTION";
	static final String SQL_SELECTONE = "SELECT SUBNUM, SUBNAME, SUBPRICE FROM SUBSCRIPTION WHERE SUBNUM = ?";
	static final String SQL_UPDATE = "";
	static final String SQL_DELETE = "";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	private boolean insert(SubscriptionVO sVO) { 
		
		return false;
	}
	
	public ArrayList<SubscriptionVO> selectAll(SubscriptionVO sVO) {
		
		ArrayList<SubscriptionVO> sdatas = new ArrayList<SubscriptionVO>();

		
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				SubscriptionVO sdata = new SubscriptionVO();
				sdata.setSubNum(rs.getInt("SUBNUM"));
				sdata.setSubName(rs.getString("SUBNAME"));
				sdata.setSubPrice(rs.getInt("SUBPRICE"));
				sdatas.add(sdata);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return sdatas;
	}

	public SubscriptionVO selectOne(SubscriptionVO sVO) {
		
		conn = JDBCUtil.connect();

		SubscriptionVO sdata = null;

		try {
			pstmt = conn.prepareStatement(SQL_SELECTONE);
			pstmt.setInt(1, sVO.getSubNum());
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				sdata = new SubscriptionVO();
				sdata.setSubNum(rs.getInt("SUBNUM"));
				sdata.setSubName(rs.getString("SUBNAME"));
				sdata.setSubPrice(rs.getInt("SUBPRICE"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(rs, pstmt, conn);
		return sdata;
	}

	private boolean update(SubscriptionVO sVO) {

		return false;
	}

	private boolean delete(SubscriptionVO sVO) {

		return false;
	}

}

