package com.spring.biz.subsinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;

//	static final String SQL_SELECTALL = "SELECT * FROM SUBSINFO SI INNER JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM";
//	static final String SQL_SELECTONE = "SELECT * FROM SUBSINFO SI INNER JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM WHERE SI.MID = ?";
//	static final String SQL_UPDATE = "UPDATE SUBSINFO SET SINFOPERIOD = SINFOPERIOD + INTERVAL 1 MONTH WHERE MID = ?";
//	이벤트 스케줄러를 사용해서, 구독 기간이 만료되면, 회원의 구독 여부를 0으로 자동 변경.
//	구독을 연장 기능은 현재 없어서,
//	새로운 구독을 하고 싶으면 구독 기간 만료 후에, 구독을 다시 신청.

//@Repository("subsinfoDAO")
public class SubsinfoDAO {

	static final String SQL_INSERT
	= "INSERT INTO SUBSINFO (SINFONUM,MID,SUBNUM) VALUES ((SELECT NVL(MAX(SINFONUM),0) + 1 FROM SUBSINFO),?,?)";
	static final String SQL_SELECTALL
	= "SELECT SI.SINFONUM, SI.MID, SI.SUBNUM, SI.SINFOPERIOD, S.SUBNAME, S.SUBPRICE"
			+ " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM";
	static final String SQL_SELECTONE
	= "SELECT SI.SINFONUM, SI.MID, SI.SUBNUM, SI.SINFOPERIOD, S.SUBNAME, S.SUBPRICE"
			+ " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM WHERE SI.MID = ?";
	static final String SQL_UPDATE = "";
	static final String SQL_DELETE = "";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(SubsinfoVO siVO) { //	구독 상세 정보.

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, siVO.getmID()); 
			pstmt.setInt(2, siVO.getSubNum());
			
			int result = pstmt.executeUpdate();
			
			if(result <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}

	public ArrayList<SubsinfoVO> selectAll(SubsinfoVO siVO) {	//	구독 상세 정보 전체 출력. (관리자 페이지)

		ArrayList<SubsinfoVO> sidatas = new ArrayList<SubsinfoVO>();

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL);
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				SubsinfoVO sidata = new SubsinfoVO();
				sidata.setsInfoNum(rs.getInt("SINFONUM"));
				sidata.setmID(rs.getString("MID"));
				sidata.setSubNum(rs.getInt("SUBNUM"));
				sidata.setsInfoPeriod(rs.getDate("SINFOPERIOD"));
				sidata.setSubName(rs.getString("SUBNAME"));
				sidata.setSubPrice(rs.getInt("SUBPRICE"));
				sidatas.add(sidata);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		JDBCUtil.disconnect(rs, pstmt, conn);
		return sidatas;
	}

	public SubsinfoVO selectOne(SubsinfoVO siVO) {	//	회원의 구독 상세 정보 출력.

		conn = JDBCUtil.connect();

		SubsinfoVO sidata = null;

		try {
			pstmt = conn.prepareStatement(SQL_SELECTONE);
			pstmt.setString(1, siVO.getmID());
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				sidata = new SubsinfoVO();
				sidata.setsInfoNum(rs.getInt("SINFONUM"));
				sidata.setmID(rs.getString("MID"));
				sidata.setSubNum(rs.getInt("SUBNUM"));
				sidata.setsInfoPeriod(rs.getDate("SINFOPERIOD"));
				sidata.setSubName(rs.getString("SUBNAME"));
				sidata.setSubPrice(rs.getInt("SUBPRICE"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		JDBCUtil.disconnect(rs, pstmt, conn);
		return sidata;
	}

	public boolean update(SubsinfoVO siVO) {

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, siVO.getmID());
			
			int result = pstmt.executeUpdate();
			
			if(result <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}

	private boolean delete(SubsinfoVO siVO) {

		return false;
	}

}
