package com.spring.biz.orderdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;

/*
static final String SQL_SELECTALL_ORDERDETAIL
= "SELECT ROW_NUMBER() OVER (ORDER BY OD.ONUM) AS RNUM, O.MID, OD.ONUM, P.PNAME, P.PPRICE, OD.PNUM, OD.ODCNT"
		+ " FROM ORDERDETAIL OD JOIN PRODUCT P ON OD.PNUM = P.PNUM JOIN ORDER_ O ON O.ONUM = OD.ONUM"
		+ " WHERE OD.ONUM = ?";
 */
//	특정한 주문건의 대한 주문 상세 보기.
//	중간 프로젝트 때 사용 XXX.

//	static final String SQL_DELETE = "DELECT FROM ORDERDETAIL WHERE ONUM = ?";
//	주문 테이블의 정보는 회원이 설령 탈퇴 하더라도, 주문 정보 자체를 지우지 않는것으로 설계.
//	따라서, oNum을 delete 할때, 주문 상세 테이블의 oNum이 일치하는 행 (데이터) 을 지우지 않아도 되서 delete 문은 제외.

//@Repository("orderdetailDAO")
public class OrderdetailDAO {

	static final String SQL_INSERT
	= "INSERT INTO ORDERDETAIL (ODNUM,PNUM,ONUM,ODCNT) VALUES ((SELECT NVL(MAX(ODNUM),0) + 1 FROM ORDERDETAIL),?,?,?)";
	static final String SQL_SELECTALL_ORDERDETAIL = "";
	static final String SQL_SELECTALL_REVIEW
	= "SELECT ROW_NUMBER() OVER (ORDER BY OD.ONUM) AS RNUM, O.MID, OD.ONUM, P.PNAME, P.PPRICE, OD.PNUM, OD.ODCNT, OD.ISWRITE, OD.ODNUM"
			+ " FROM ORDERDETAIL OD JOIN PRODUCT P ON OD.PNUM = P.PNUM JOIN ORDER_ O ON O.ONUM = OD.ONUM"
			+ " WHERE O.MID = ? AND OD.ISWRITE = '작성 전'";
	//	마이페이지 에서 내가 작성 가능한 리뷰 보여주기.
	static final String SQL_SELECTONE = "";
	static final String SQL_UPDATE = "UPDATE ORDERDETAIL SET ISWRITE = '작성 완료' WHERE ODNUM = ?";
	static final String SQL_DELETE = "";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(OrderdetailVO odVO) { 

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, odVO.getpNum()); 
			pstmt.setInt(2, odVO.getoNum());
			pstmt.setInt(3, odVO.getOdCnt());
			pstmt.executeUpdate();
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

	public ArrayList<OrderdetailVO> selectAll(OrderdetailVO odVO) {

		ArrayList<OrderdetailVO> oddatas = new ArrayList<OrderdetailVO>();

		conn = JDBCUtil.connect();

		try {
			if(odVO.getSk().equals("ORDERDETAIL")) {
				pstmt = conn.prepareStatement(SQL_SELECTALL_ORDERDETAIL);
				pstmt.setString(1, odVO.getmID());
				pstmt.setInt(2, odVO.getoNum());
			}
			else if(odVO.getSk().equals("REVIEW")) {
				pstmt = conn.prepareStatement(SQL_SELECTALL_REVIEW);
				pstmt.setString(1, odVO.getmID());
			}
			rs = pstmt.executeQuery();

			while(rs.next()) {
				OrderdetailVO oddata = new OrderdetailVO();
				oddata.setRnum(rs.getInt("RNUM"));
				oddata.setmID(rs.getString("MID"));
				oddata.setoNum(rs.getInt("ONUM"));
				oddata.setpName(rs.getString("PNAME"));
				oddata.setpPrice(rs.getInt("PPRICE"));
				oddata.setOdCnt(rs.getInt("ODCNT"));
				oddata.setpNum(rs.getInt("PNUM"));
				oddata.setIsWrite(rs.getString("ISWRITE"));
				oddata.setOdNum(rs.getInt("ODNUM"));
				oddatas.add(oddata);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}      
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return oddatas;
	}

	private OrderdetailVO selectOne(OrderdetailVO odVO) {

		return null;
	}

	public boolean update(OrderdetailVO odVO) {
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setInt(1, odVO.getOdNum());

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
				return false;
			}
		}   
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	private boolean delete(OrderdetailVO odVO) {

		return false;
	}

}
