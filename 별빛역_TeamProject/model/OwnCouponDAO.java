package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OwnCouponDAO { // cnt집계함수 / 쿠폰 유효기간 ADDDATE(CURDATE INTERVAL 1 MONTH); -> 삭제도 만들어야 되네 ?? 유효기간 끝나면 

	static final String sql_insert_RECOMMAND="INSERT INTO OWNCOUPON (MIDFK, CNUMFK) VALUES(?,1)";
	static final String sql_insert_GRADE="INSERT INTO OWNCOUPON (MIDFK, CNUMFK) VALUES(?,?)";

	static final String sql_selectAll_COUPON="SELECT O.NUM, O.MIDFK, O.CNUMFK, O.FDATE, C.NAME, C.RATE, ROW_NUMBER() OVER (ORDER BY O.NUM) AS RNUM FROM OWNCOUPON O JOIN COUPON C ON O.CNUMFK = C.NUM WHERE O.MIDFK = ?";

	static final String sql_selectOne="SELECT NUM, MIDFK FROM OWNCOUPON WHERE MIDFK= ? AND CNUMFK = ?";

	static final String sql_delete_COUPON="DELETE FROM OWNCOUPON WHERE MIDFK =? AND CNUMFK =? LIMIT 1";
	//	어떤 회원이 추천인 쿠폰, 실버 쿠폰, 골드 쿠폰, 등등을 가질 수 있다.
	//	근데, 예를들어 추천인 쿠폰을 3개를 가지고 있다고 해보자.
	//	LIMIT 검색 해보거나 물어보기.
	//	ROW_NUMBER() => 조건에 맞는 행을 가져온다 . (동일한 행을 다 가져오는 느낌)
	//	OVER은 뭐야 ?
	//	AS RNUM => 그렇게 가져온 테이블의 별칭을 RNUM으로 해서 만들어줘.
	//	JOIN 함수를 사용하면, ON과 같이 사용 해야하는 듯?

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(OwnCouponVO oVO) { // (member.mid , coupon.num) 

		conn=JDBCUtil.connect();
		try {
			if(oVO.getTmpCondition().equals("추천인할인쿠폰증정")) {
				pstmt=conn.prepareStatement(sql_insert_RECOMMAND);
				pstmt.setString(1, oVO.getMidfk()); 
			}
			else if(oVO.getTmpCondition().equals("등급쿠폰증정")){
				pstmt=conn.prepareStatement(sql_insert_GRADE);
				pstmt.setString(1, oVO.getMidfk()); 
				pstmt.setInt(2, oVO.getTmpGrade() + 2);
				
//				if(oVO.getTmpGrade() == 0) {
//					pstmt.setInt(2, 2);
//				}
//				else if(oVO.getTmpGrade() == 1) {
//					pstmt.setInt(2, 3);
//				}
//				else if(oVO.getTmpGrade() == 2) {
//					pstmt.setInt(2, 4);
//				}
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}

	public ArrayList<OwnCouponVO> selectAll (OwnCouponVO oVO){
		// 내 쿠폰 출력 
		//		final String sql_selectAll_COUPON="SELECT O.NUM, O.MIDFK, O.CNUMFK, C.NAME, O.FDATE FROM OWNCOUPON O JOIN COUPON C ON O.CNUMFK = C.NUM WHERE O.MNUMFK = ?";
		ArrayList<OwnCouponVO> odatas=new ArrayList<OwnCouponVO>();

		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_selectAll_COUPON);
			pstmt.setString(1, oVO.getMidfk());
			rs=pstmt.executeQuery();

			while(rs.next()) {
				odatas.add(new OwnCouponVO(rs.getInt("NUM"), rs.getString("MIDFK"), rs.getInt("CNUMFK"), rs.getDate("FDATE"), rs.getString("NAME"), rs.getDouble("RATE"), rs.getInt("RNUM"))); // 출력은 fdate, name , rate 만
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		JDBCUtil.disconnect(rs, pstmt, conn);

		return odatas;
	}


	public OwnCouponVO selectOne(OwnCouponVO oVO) {
		//		OwnCouponVO odata=null;
		//
		//		conn=JDBCUtil.connect();
		//		try {
		//			pstmt=conn.prepareStatement(sql_selectOne);
		//			pstmt.setString(1, oVO.getMidfk());
		//			pstmt.setInt(2, oVO.getBnumfk());
		//
		//			rs=pstmt.executeQuery();
		//
		//			if(rs.next()) {	// 값이 있으면 중복된거
		//				odata=new OwnCouponVO(rs.getInt("NUM"), rs.getString("MIDFK"),rs.getInt("BNUMFK"));
		//			}
		//			else {	// 값이 없다면 중복 안 된거
		//				return null;
		//			}
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//			try {
		//				Thread.sleep(1000);
		//			} catch (InterruptedException e1) {
		//				e1.printStackTrace();
		//			}
		//		}
		//		JDBCUtil.disconnect(pstmt, conn);

		//		return odata;
		return null;

	}

	public boolean update(OwnCouponVO oVO) {
		return false;
	}
	//	static final String sql_delete_COUPON="DELETE OWNCOUPON WHERE MIDFK = ? AND CNUMFK = ? LIMIT 1";
	public boolean delete(OwnCouponVO oVO) {
		conn=JDBCUtil.connect();
		try {
			if(oVO.getTmpCondition().equals("쿠폰사용")) {	
				pstmt=conn.prepareStatement(sql_delete_COUPON);
				pstmt.setString(1, oVO.getMidfk());
				pstmt.setInt(2, oVO.getCnumfk());
			}
			int result=pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	} 
}
