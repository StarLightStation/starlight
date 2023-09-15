package com.spring.biz.orderdetail;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/*
static final String SQL_SELECTALL_ORDERDETAIL
= "SELECT ROW_NUMBER() OVER (ORDER BY OD.ONUM) AS RNUM, O.MID, OD.ONUM, P.PNAME, P.PPRICE, OD.PNUM, OD.ODCNT"
      + " FROM ORDERDETAIL OD JOIN PRODUCT P ON OD.PNUM = P.PNUM JOIN ORDER_ O ON O.ONUM = OD.ONUM"
      + " WHERE OD.ONUM = ?";
 */
//   특정한 주문건의 대한 주문 상세 보기.
//   중간 프로젝트 때 사용 XXX.

//   static final String SQL_DELETE = "DELECT FROM ORDERDETAIL WHERE ONUM = ?";
//   주문 테이블의 정보는 회원이 설령 탈퇴 하더라도, 주문 정보 자체를 지우지 않는것으로 설계.
//   따라서, oNum을 delete 할때, 주문 상세 테이블의 oNum이 일치하는 행 (데이터) 을 지우지 않아도 되서 delete 문은 제외.

@Repository("orderdetailDAO")
public class OrderdetailDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	static final String SQL_INSERT
	= "INSERT INTO ORDERDETAIL (ODNUM,PNUM,ONUM,ODCNT) VALUES ((SELECT NVL(MAX(ODNUM),0) + 1 FROM ORDERDETAIL),?,?,?)";

	// 주문에 대한 주문 상세
	static final String SQL_SELECTALL_ORDERDETAIL 
	= "SELECT ROW_NUMBER() OVER (ORDER BY OD.ONUM) AS RNUM, O.MID, OD.ONUM, P.PNAME, P.PPRICE, P.PIMAGE, OD.PNUM, OD.ODCNT, OD.ISWRITE, OD.ODNUM"
			+ " FROM ORDERDETAIL OD JOIN PRODUCT P ON OD.PNUM = P.PNUM JOIN ORDER_ O ON O.ONUM = OD.ONUM"
			+ " WHERE OD.ONUM = ?";

	// 마이페이지 에서 내가 작성 가능한 리뷰 보여주기.
	static final String SQL_SELECTALL_REVIEW        
	= "SELECT ROW_NUMBER() OVER (ORDER BY OD.ONUM) AS RNUM, O.MID, O.ODATE, OD.ONUM, P.PNAME, P.PPRICE, P.PIMAGE, OD.PNUM, OD.ODCNT, OD.ISWRITE, OD.ODNUM"
			+ " FROM ORDERDETAIL OD JOIN PRODUCT P ON OD.PNUM = P.PNUM JOIN ORDER_ O ON O.ONUM = OD.ONUM"
			+ " WHERE O.MID = ? AND OD.ISWRITE = '작성 전'";

	// 상품 이름 / 상품 판매 누적 개수 
	static final String SQL_SELECTALL_CNT
	= "SELECT P.PNAME, NVL(SUM(OD.ODCNT), 0) AS CNT, NVL(SUM(OD.ODCNT) * P.PPRICE, 0) AS TOTAL, RANK() OVER (ORDER BY NVL(SUM(OD.ODCNT), 0) DESC) AS CNT_RANK"
			+ " FROM PRODUCT P"
			+ " LEFT JOIN ORDERDETAIL OD ON OD.PNUM = P.PNUM"
			+ " GROUP BY P.PNAME, P.PPRICE";

	// 상품 판매 누적 액수 
	static final String SQL_SELECTALL_TOTAL
	= "SELECT P.PNAME, NVL(SUM(OD.ODCNT), 0) AS CNT, NVL(SUM(OD.ODCNT) * P.PPRICE, 0) AS TOTAL,  RANK() OVER (ORDER BY NVL(SUM(OD.ODCNT) * P.PPRICE, 0) DESC) AS TOTAL_RANK"
			+ " FROM PRODUCT P"
			+ " LEFT JOIN ORDERDETAIL OD ON OD.PNUM = P.PNUM"
			+ " GROUP BY P.PNAME, P.PPRICE";

	static final String SQL_SELECTONE = "";
	static final String SQL_UPDATE = "UPDATE ORDERDETAIL SET ISWRITE = '작성 완료' WHERE ODNUM = ?";
	static final String SQL_DELETE = "";


	public boolean insert(OrderdetailVO odVO) {
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, odVO.getpNum(), odVO.getoNum(), odVO.getOdCnt());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<OrderdetailVO> selectAll(OrderdetailVO odVO) {

		try {
			if (odVO.getSk().equals("ORDERDETAIL")) {
				Object[] args = {odVO.getoNum()};
				return jdbcTemplate.query(SQL_SELECTALL_ORDERDETAIL, args, new OrderdetailRowMapper_SELECTALL());
			}
			else if (odVO.getSk().equals("REVIEW")) {
				Object[] args = {odVO.getmID()};
				return jdbcTemplate.query(SQL_SELECTALL_REVIEW, args, new OrderdetailRowMapper_SELECTALL_REVIEW());
			}
			else if (odVO.getSk().equals("CNT")) {
				return jdbcTemplate.query(SQL_SELECTALL_CNT, new OrderdetailRowMapper_SELECTALL_CNT());
			}
			else if (odVO.getSk().equals("TOTAL")) {
				return jdbcTemplate.query(SQL_SELECTALL_TOTAL, new OrderdetailRowMapper_SELECTALL_TOTAL());
			}
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	private OrderdetailVO selectOne(OrderdetailVO odVO) {

		return null;
	}

	public boolean update(OrderdetailVO odVO) {
		try {
			int rs = jdbcTemplate.update(SQL_UPDATE, odVO.getOdNum());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean delete(OrderdetailVO odVO) {

		return false;
	}

}

class OrderdetailRowMapper_SELECTALL implements RowMapper<OrderdetailVO> {

	public OrderdetailVO mapRow(ResultSet rs, int rowNum) {
		OrderdetailVO oddata = new OrderdetailVO();

		try {
			oddata.setRnum(rs.getInt("RNUM"));
			oddata.setmID(rs.getString("MID"));
			oddata.setoNum(rs.getInt("ONUM"));
			oddata.setpName(rs.getString("PNAME"));
			oddata.setpPrice(rs.getInt("PPRICE"));
			oddata.setpImage(rs.getString("PIMAGE"));
			oddata.setpNum(rs.getInt("PNUM"));
			oddata.setOdCnt(rs.getInt("ODCNT"));
			oddata.setIsWrite(rs.getString("ISWRITE"));
			oddata.setOdNum(rs.getInt("ODNUM"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return oddata;
	}
}

class OrderdetailRowMapper_SELECTALL_REVIEW implements RowMapper<OrderdetailVO> {

	public OrderdetailVO mapRow(ResultSet rs, int rowNum) {
		OrderdetailVO oddata = new OrderdetailVO();

		try {
			oddata.setRnum(rs.getInt("RNUM"));
			oddata.setmID(rs.getString("MID"));
			oddata.setoDate(rs.getDate("ODATE"));
			oddata.setoNum(rs.getInt("ONUM"));
			oddata.setpName(rs.getString("PNAME"));
			oddata.setpPrice(rs.getInt("PPRICE"));
			oddata.setpImage(rs.getString("PIMAGE"));
			oddata.setpNum(rs.getInt("PNUM"));
			oddata.setOdCnt(rs.getInt("ODCNT"));
			oddata.setIsWrite(rs.getString("ISWRITE"));
			oddata.setOdNum(rs.getInt("ODNUM"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return oddata;
	}
}

class OrderdetailRowMapper_SELECTALL_CNT implements RowMapper<OrderdetailVO> {

	public OrderdetailVO mapRow(ResultSet rs, int rowNum) {
		OrderdetailVO oddata = new OrderdetailVO();

		try {
			oddata.setpName(rs.getString("PNAME"));
			oddata.setCnt(rs.getInt("CNT"));
			oddata.setTotal(rs.getInt("TOTAL"));
			oddata.setCnt_rank(rs.getInt("CNT_RANK"));

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return oddata;
	}
}

class OrderdetailRowMapper_SELECTALL_TOTAL implements RowMapper<OrderdetailVO> {
	
	public OrderdetailVO mapRow(ResultSet rs, int rowNum) {
		OrderdetailVO oddata = new OrderdetailVO();
		
		try {
			oddata.setpName(rs.getString("PNAME"));
			oddata.setCnt(rs.getInt("CNT"));
			oddata.setTotal(rs.getInt("TOTAL"));
			oddata.setTotal_rank(rs.getInt("TOTAL_RANK"));
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return oddata;
	}
}