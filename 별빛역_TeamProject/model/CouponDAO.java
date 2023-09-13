package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CouponDAO {


	static final String sql_selectOne="SELECT NUM, NAME, RATE FROM COUPON WHERE NUM = ?";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(CouponVO cVO) { 
		return false;
	}

	public ArrayList<CouponVO> selectAll(CouponVO cVO){
		return null;
	}

	public CouponVO selectOne(CouponVO cVO) {

		CouponVO odata=null;

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOne);
			pstmt.setInt(1, cVO.getNum());

			rs=pstmt.executeQuery();

			if(rs.next()) {	// 값이 있으면 중복된거
				odata=new CouponVO(rs.getInt("NUM"), rs.getString("NAME"),rs.getDouble("RATE"));
			}
			else {	// 값이 없다면 중복 안 된거
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		JDBCUtil.disconnect(pstmt, conn);

		return odata;

	}

	public boolean update(CouponVO cVO) {
		// 쿠폰
		cVO.setTmpprice(cVO.getTmpprice() * cVO.getRate()); // 책의 가격을 C에서 tmpprice에 저장해서 보내주면 할인율 * 책가격);
		return true;
		//		ctrl
		//		if(cDAO.update(cVO)) {
		//			mVO.settmpCondition("쿠폰사용");
		//			mDAO.update(mVO);
		//		}
	}

	public boolean delete(CouponVO cVO) {
		return false;
	}
}
