package com.spring.biz.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;

//@Repository("orderDAO")
public class OrderDAO {

	static final String SQL_INSERT
	= "INSERT INTO ORDER_ (ONUM,MID,OPRICE,OSTATE,OADDRESS) VALUES ((SELECT NVL(MAX(ONUM),0) + 1 FROM ORDER_),?,?,?,?)";
	static final String SQL_SELECTALL_MYORDER
	= "SELECT ROW_NUMBER() OVER (ORDER BY ONUM) AS RNUM, ONUM,MID,ODATE,OPRICE,OSTATE,OADDRESS FROM ORDER_ WHERE MID = ?";
	//	내 주문 목록 보기.
	static final String SQL_SELECTALL_ADMIN = "SELECT MID,ODATE,OPRICE,OSTATE,OADDRESS FROM ORDER_";
	static final String SQL_SELECTONE
	= "SELECT MID,ODATE,OPRICE,OSTATE,OADDRESS FROM ORDER_ WHERE ONUM = (SELECT MAX(ONUM) FROM ORDER_)";	// AND MID = ?";
	//	ORDER 테이블에서 제일 최신 (마지막) 에 저장된 주문 내역을 가져오기.
	//	(ORDER 테이블에서 ONUM 칼럼에 행 번호가 제일 큰 수를 ORDER 테이블로 부터 SELECT 하는 구문.)
	static final String SQL_UPDATE = "UPDATE ORDER_ SET OSTATE = '결제완료' WHERE ONUM = ?";
	static final String SQL_DELETE = "";
	//	사용자가 주문 취소를 하면, 주문 정보 자체가 DELETE 되는 설계가 아니다.
	//	주문 취소를 하면, "주문 취소된 주문 입니다." 라고 보이게 할 예정 이다.

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(OrderVO oVO) {	//	주문 정보.

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1,oVO.getmID()); 
			pstmt.setInt(2,oVO.getoPrice()); 
			pstmt.setString(3,oVO.getoState());
			pstmt.setString(4,oVO.getoAddress());

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

	public ArrayList<OrderVO> selectAll(OrderVO oVO) {	//	주문 목록 전체 출력. (관리자 페이지)

		ArrayList<OrderVO> odatas = new ArrayList<OrderVO>();

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL_MYORDER);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				OrderVO odata = new OrderVO();
				odata.setRnum(rs.getInt("RNUM"));
				odata.setoNum(rs.getInt("ONUM"));
				odata.setmID(rs.getString("MID"));
				odata.setoDate(rs.getDate("ODATE"));
				odata.setoPrice(rs.getInt("OPRICE"));
				odata.setoState(rs.getString("OSTATE"));
				odata.setoAddress(rs.getString("OADDRESS"));
				odatas.add(odata);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return odatas;
	}

	public OrderVO selectOne(OrderVO oVO) {	//	회원의 구독 상세 정보 출력.

		conn = JDBCUtil.connect();

		OrderVO odata = null;

		try {
			pstmt = conn.prepareStatement(SQL_SELECTONE);
			//	pstmt.setString(1,oVO.getmID());	//	회원이 동시다발적으로 주문을 넣게 되면, 최신 oNum의 값이 엉뚱한 회원에게 갈 수 있기 때문에 조건 추가.

			rs = pstmt.executeQuery();

			if(rs.next()) {
				odata = new OrderVO();
				odata.setmID(rs.getString("MID"));
				odata.setoDate(rs.getDate("ODATE"));
				odata.setoPrice(rs.getInt("OPRICE"));
				odata.setoState(rs.getString("OSTATE"));
				odata.setoAddress(rs.getString("OADDRESS"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return null;
			}
		}   
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return odata;

	}

	public boolean update(OrderVO oVO) {

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setInt(1, oVO.getoNum());

			int rs = pstmt.executeUpdate();

			if(rs <= 0) {
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

	private boolean delete(OrderVO oVO) {

		/*
		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, oVO.getoNum());

			int rs = pstmt.executeUpdate();

			if(rs <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		 */

		return false;
	}

}
