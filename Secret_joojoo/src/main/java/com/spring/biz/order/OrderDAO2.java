package com.spring.biz.order;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("orderDAO")
public class OrderDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	static final String SQL_INSERT 
	= "INSERT INTO ORDER_ (ONUM,MID,OPRICE,OSTATE,OADDRESS) VALUES ((SELECT NVL(MAX(ONUM),0) + 1 FROM ORDER_),?,?,?,?)";

	//   내 주문 목록 보기.
	static final String SQL_SELECTALL_MYORDER
	= "SELECT ROW_NUMBER() OVER (ORDER BY ONUM) AS RNUM, ONUM,MID,ODATE,OPRICE,OSTATE,OADDRESS FROM ORDER_ WHERE MID = ?";

	// 관리자가 전체 주문 목록 보기
	static final String SQL_SELECTALL_ADMIN = "SELECT ONUM,MID,ODATE,OPRICE,OSTATE,OADDRESS FROM ORDER_";   

	// 회원이 결제한  총 금액 O
	static final String SQL_SELECTALL_MEMBER_TOTAL 
	= "SELECT COALESCE(ORDER_.MID, SUBSINFO.MID) AS MID, COALESCE(ORDER_.PRODUCT_PRICE, 0) AS PRODUCT_PRICE, COALESCE(SUBSINFO.SUBS_PRICE, 0) SUBS_PRICE, COALESCE(ORDER_.PRODUCT_PRICE, 0) + COALESCE(SUBSINFO.SUBS_PRICE, 0) AS TOTAL_PRICE"
			+ " FROM ("
			+ " SELECT MID,  SUM(OPRICE) AS PRODUCT_PRICE"
			+ " FROM ORDER_"
			+ " GROUP BY MID"
			+ ") ORDER_"
			+ " FULL OUTER JOIN (SELECT SI.MID, SUM(S.SUBPRICE) AS SUBS_PRICE"
			+ " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " GROUP BY SI.MID"
			+ ") SUBSINFO ON ORDER_.MID = SUBSINFO.MID"
			+ " ORDER BY TOTAL_PRICE DESC, MID";      

	// 회원의 상세 누적 금액 ( 연도 , 분기 , 총 결제 금액) 
	static final String SQL_SELECTALL_MEMBER_SALE 
	= "SELECT COALESCE(ORDER_.MID, SUBSINFO.MID) AS MID, COALESCE(ORDER_.YEAR, SUBSINFO.YEAR) AS YEAR, COALESCE(ORDER_.QUARTER, SUBSINFO.QUARTER) AS QUARTER,COALESCE(ORDER_.PRODUCT_PRICE, 0) AS PRODUCT_PRICE, COALESCE(SUBSINFO.SUBS_PRICE, 0) SUBS_PRICE, COALESCE(ORDER_.PRODUCT_PRICE, 0) + COALESCE(SUBSINFO.SUBS_PRICE, 0) AS TOTAL_PRICE"
			+ " FROM (SELECT EXTRACT(YEAR FROM ODATE) AS YEAR, TO_NUMBER(TO_CHAR(ODATE, 'Q')) AS QUARTER, SUM(OPRICE) AS PRODUCT_PRICE, MID"
			+ " FROM ORDER_"
			+ " GROUP BY EXTRACT(YEAR FROM ODATE), TO_NUMBER(TO_CHAR(ODATE, 'Q')), MID"
			+ ") ORDER_"
			+ " FULL OUTER JOIN (SELECT EXTRACT(YEAR FROM SI.SINFOPERIOD) AS YEAR, TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q')) AS QUARTER, SUM(S.SUBPRICE) AS SUBS_PRICE, SI.MID"
			+ " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " GROUP BY EXTRACT(YEAR FROM SI.SINFOPERIOD), TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q')), SI.MID"
			+ " ) SUBSINFO ON ORDER_.YEAR = SUBSINFO.YEAR AND ORDER_.QUARTER = SUBSINFO.QUARTER AND ORDER_.MID = SUBSINFO.MID"
			+ " WHERE COALESCE(ORDER_.MID, SUBSINFO.MID) = ?"
			+ " ORDER BY YEAR DESC, QUARTER DESC";

	// 모든 연도 분기별 총 매출
	static final String SQL_SELECTALL_YEAR_QUARTER_SALE_ALL
	= "WITH Quarters AS (SELECT"
			+ " EXTRACT(YEAR FROM SYSDATE) AS YEAR,"
			+ " LEVEL AS QUARTER"
			+ " FROM DUAL"
			+ " CONNECT BY LEVEL <= 4"
			+ " UNION ALL"
			+ " SELECT"
			+ " EXTRACT(YEAR FROM SYSDATE) - 1 AS YEAR,"
			+ " LEVEL AS QUARTER"
			+ " FROM DUAL"
			+ " CONNECT BY LEVEL <= 4"
			+ " ) SELECT COALESCE(Q.YEAR, ORDER_.YEAR) AS YEAR,"
			+ " COALESCE(Q.QUARTER, ORDER_.QUARTER) AS QUARTER,"
			+ " COALESCE(ORDER_.PRODUCT_PRICE, 0) + COALESCE(SUBSINFO.SUBS_PRICE, 0) AS TOTAL_PRICE"
			+ " FROM Quarters Q"
			+ " LEFT JOIN ("
			+ " SELECT EXTRACT(YEAR FROM ODATE) AS YEAR,"
			+ " TO_NUMBER(TO_CHAR(ODATE, 'Q')) AS QUARTER,"
			+ " SUM(OPRICE) AS PRODUCT_PRICE"
			+ " FROM ORDER_"
			+ " GROUP BY EXTRACT(YEAR FROM ODATE), TO_NUMBER(TO_CHAR(ODATE, 'Q'))"
			+ " ) ORDER_ ON Q.YEAR = ORDER_.YEAR AND Q.QUARTER = ORDER_.QUARTER"
			+ " FULL OUTER JOIN ("
			+ " SELECT EXTRACT(YEAR FROM SI.SINFOPERIOD) AS YEAR,"
			+ " TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q')) AS QUARTER,"
			+ " SUM(S.SUBPRICE) AS SUBS_PRICE"
			+ " FROM SUBSINFO SI"
			+ " JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " GROUP BY EXTRACT(YEAR FROM SI.SINFOPERIOD), TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q'))"
			+ " ) SUBSINFO ON Q.YEAR = SUBSINFO.YEAR AND Q.QUARTER = SUBSINFO.QUARTER"
			+ " ORDER BY YEAR DESC, QUARTER DESC";

	//      = "SELECT COALESCE(ORDER_.YEAR, SUBSINFO.YEAR) AS YEAR, COALESCE(ORDER_.QUARTER, SUBSINFO.QUARTER) AS QUARTER,  COALESCE(ORDER_.PRODUCT_PRICE, 0) + COALESCE(SUBSINFO.SUBS_PRICE, 0) AS TOTAL_PRICE"
	//         + " FROM (SELECT EXTRACT(YEAR FROM ODATE) AS YEAR, TO_NUMBER(TO_CHAR(ODATE, 'Q')) AS QUARTER, SUM(OPRICE) AS PRODUCT_PRICE"
	//         + " FROM ORDER_"
	//         + " GROUP BY EXTRACT(YEAR FROM ODATE), TO_NUMBER(TO_CHAR(ODATE, 'Q'))"
	//         + ") ORDER_"
	//         + " FULL OUTER JOIN (SELECT EXTRACT(YEAR FROM SI.SINFOPERIOD) AS YEAR, TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q')) AS QUARTER, SUM(S.SUBPRICE) AS SUBS_PRICE"
	//         + " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
	//         + " GROUP BY EXTRACT(YEAR FROM SI.SINFOPERIOD), TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q'))"
	//         + " ) SUBSINFO ON ORDER_.YEAR = SUBSINFO.YEAR AND ORDER_.QUARTER = SUBSINFO.QUARTER"
	//         + " ORDER BY YEAR DESC, QUARTER DESC";


	// 그 해당년도에 따른 분기별 금액 O
	static final String SQL_SELECTALL_YEAR_QUARTER_SALE 
	="WITH years AS (SELECT DISTINCT EXTRACT(YEAR FROM ODATE) AS YEAR FROM ORDER_"
			+ " UNION"
			+ " SELECT DISTINCT EXTRACT(YEAR FROM SINFOPERIOD) AS YEAR FROM SUBSINFO)"
			+ " SELECT years.YEAR, quarters.QUARTER, COALESCE(ORDER_.PRODUCT_PRICE, 0) AS PRODUCT_PRICE, COALESCE(SUBSINFO.SUBS_PRICE, 0) AS SUBS_PRICE, COALESCE(ORDER_.PRODUCT_PRICE, 0) + COALESCE(SUBSINFO.SUBS_PRICE, 0) AS TOTAL_PRICE"
			+ " FROM years"
			+ " CROSS JOIN ("
			+ " SELECT 1 AS QUARTER FROM DUAL"
			+ " UNION"
			+ " SELECT 2 AS QUARTER FROM DUAL"
			+ " UNION"
			+ " SELECT 3 AS QUARTER FROM DUAL"
			+ " UNION"
			+ " SELECT 4 AS QUARTER FROM DUAL"
			+ " ) quarters"
			+ " LEFT JOIN ("
			+ " SELECT EXTRACT(YEAR FROM ODATE) AS YEAR, TO_NUMBER(TO_CHAR(ODATE, 'Q')) AS QUARTER, SUM(OPRICE) AS PRODUCT_PRICE"
			+ " FROM ORDER_"
			+ " GROUP BY EXTRACT(YEAR FROM ODATE), TO_NUMBER(TO_CHAR(ODATE, 'Q'))"
			+ " ) ORDER_ ON years.YEAR = ORDER_.YEAR AND quarters.QUARTER = ORDER_.QUARTER"
			+ " FULL OUTER JOIN ("
			+ " SELECT EXTRACT(YEAR FROM SI.SINFOPERIOD) AS YEAR, TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q')) AS QUARTER, SUM(S.SUBPRICE) AS SUBS_PRICE"
			+ " FROM SUBSINFO SI"
			+ " JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " GROUP BY EXTRACT(YEAR FROM SI.SINFOPERIOD), TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q'))"
			+ ") SUBSINFO ON years.YEAR = SUBSINFO.YEAR AND quarters.QUARTER = SUBSINFO.QUARTER"
			+ " WHERE years.YEAR = ?"
			+ " ORDER BY years.YEAR DESC, quarters.QUARTER"; 

	//      = "SELECT COALESCE(ORDER_.YEAR, SUBSINFO.YEAR) AS YEAR,   COALESCE(ORDER_.QUARTER, SUBSINFO.QUARTER) AS QUARTER, COALESCE(ORDER_.PRODUCT_PRICE, 0) AS PRODUCT_PRICE, COALESCE(SUBSINFO.SUBS_PRICE, 0) SUBS_PRICE, COALESCE(ORDER_.PRODUCT_PRICE, 0) + COALESCE(SUBSINFO.SUBS_PRICE, 0) AS TOTAL_PRICE"
	//         + " FROM (SELECT EXTRACT(YEAR FROM ODATE) AS YEAR, TO_NUMBER(TO_CHAR(ODATE, 'Q')) AS QUARTER, SUM(OPRICE) AS PRODUCT_PRICE"
	//         + " FROM ORDER_"
	//         + " GROUP BY EXTRACT(YEAR FROM ODATE), TO_NUMBER(TO_CHAR(ODATE, 'Q'))"
	//         + ") ORDER_"
	//         + " FULL OUTER JOIN (SELECT EXTRACT(YEAR FROM SI.SINFOPERIOD) AS YEAR, TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q')) AS QUARTER, SUM(S.SUBPRICE) AS SUBS_PRICE"
	//         + " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
	//         + " GROUP BY EXTRACT(YEAR FROM SI.SINFOPERIOD), TO_NUMBER(TO_CHAR(SI.SINFOPERIOD, 'Q'))"
	//         + " ) SUBSINFO ON ORDER_.YEAR = SUBSINFO.YEAR AND ORDER_.QUARTER = SUBSINFO.QUARTER"
	//         + " WHERE COALESCE(ORDER_.YEAR, SUBSINFO.YEAR) = ?"
	//         + " ORDER BY YEAR DESC, QUARTER";

	// 년도만 나온 매출  O
	static final String SQL_SELECTALL_YEAR_SALE
	= "SELECT COALESCE(ORDER_.YEAR, SUBSINFO.YEAR) AS YEAR, COALESCE(ORDER_.PRODUCT_PRICE, 0) AS PRODUCT_PRICE, COALESCE(SUBSINFO.SUBS_PRICE, 0) SUBS_PRICE, COALESCE((ORDER_.PRODUCT_PRICE), 0) + COALESCE((SUBSINFO.SUBS_PRICE), 0) AS TOTAL_PRICE"
			+ " FROM( SELECT EXTRACT(YEAR FROM ODATE) YEAR, SUM(OPRICE) AS PRODUCT_PRICE"
			+ " FROM ORDER_"
			+ " GROUP BY EXTRACT(YEAR FROM ODATE)"
			+ ") ORDER_"
			+ " FULL OUTER JOIN (SELECT EXTRACT(YEAR FROM SI.SINFOPERIOD) AS YEAR, SUM(S.SUBPRICE) AS SUBS_PRICE"
			+ " FROM SUBSCRIPTION S JOIN SUBSINFO SI ON S.SUBNUM = SI.SUBNUM"
			+ " GROUP BY EXTRACT(YEAR FROM SI.SINFOPERIOD)"
			+ ") SUBSINFO ON ORDER_.YEAR = SUBSINFO.YEAR"
			+ " ORDER BY YEAR DESC"; 

	// 최근 1주일 매출 (7일 이내의 데이터)
	static final String SQL_SELECTALL_WEEK_SALE
	= "WITH DATE_RANGE AS ("
			+ " SELECT TRUNC(SYSDATE - LEVEL + 1) AS SALE_DATE"
			+ " FROM dual"
			+ " CONNECT BY LEVEL <= 7"
			+ ")"
			+ " SELECT DR.SALE_DATE AS ODATE, NVL(SUM(DS.DAILY_PRICE), 0) AS TOTAL_PRICE"
			+ " FROM DATE_RANGE DR"
			+ " LEFT JOIN ("
			+ " SELECT TRUNC(ODATE) AS DATE_COLUMN, SUM(OPRICE) AS DAILY_PRICE"
			+ " FROM ORDER_"
			+ " WHERE TRUNC(ODATE) BETWEEN TRUNC(SYSDATE - 7) AND TRUNC(SYSDATE)"
			+ " GROUP BY TRUNC(ODATE)"
			+ " UNION ALL"
			+ " SELECT TRUNC(SI.SINFOPERIOD) AS DATE_COLUMN, SUM(S.SUBPRICE) AS DAILY_PRICE"
			+ " FROM SUBSINFO SI"
			+ " JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " WHERE TRUNC(SI.SINFOPERIOD) BETWEEN TRUNC(SYSDATE - 7) AND TRUNC(SYSDATE)"
			+ " GROUP BY TRUNC(SI.SINFOPERIOD)"
			+ " ) DS"
			+ " ON DR.SALE_DATE = DS.DATE_COLUMN"
			+ " GROUP BY DR.SALE_DATE"
			+ " ORDER BY DR.SALE_DATE";



	// 총 매출  O
	static final String SQL_SELECTONE_TOTAL
	= "SELECT (SELECT COALESCE(SUM(OPRICE),0) FROM ORDER_) AS PRODUCT_PRICE, (SELECT COALESCE(SUM(SUBPRICE),0) FROM SUBSCRIPTION) AS SUBS_PRICE, (SELECT SUM(OPRICE) FROM ORDER_) + (SELECT SUM(SUBPRICE) FROM SUBSCRIPTION) AS TOTAL_PRICE" 
			+ " FROM DUAL";

	// 오늘 상품 매출
	static final String SQL_SELECTONE_TODAY_PRODUCTPRICE
	= "SELECT NVL(SUM(OPRICE),0) AS TOTAL_PRICE"
			+ " FROM ORDER_"
			+ " WHERE TRUNC(ODATE) = TRUNC(SYSDATE)";

	// 오늘 구독 매출
	static final String SQL_SELECTONE_TODAY_SUBSPRICE
	= "SELECT NVL(SUM(S.SUBPRICE),0) AS TOTAL_PRICE"
			+ " FROM SUBSINFO SI"
			+ " JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " WHERE TRUNC(SI.SINFOPERIOD) = TRUNC(SYSDATE)";

	// 오늘 매출
	static final String SQL_SELECTONE_TODAY_TOTAL
	= "SELECT NVL(SUM(PRICE),0) AS TOTAL_PRICE"
			+ " FROM ("
			+ " SELECT SUM(OPRICE) AS PRICE"
			+ " FROM ORDER_"
			+ " WHERE TRUNC(ODATE) = TRUNC(SYSDATE)"
			+ " UNION ALL"
			+ " SELECT SUM(S.SUBPRICE) AS PRICE"
			+ " FROM SUBSINFO SI"
			+ " JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " WHERE TRUNC(SI.SINFOPERIOD) = TRUNC(SYSDATE)"
			+ " )";

	// 어제 매출
	static final String SQL_SELECTONE_YESTERDAY_TOTAL
	= "SELECT NVL(SUM(PRICE),0) AS TOTAL_PRICE"
			+ " FROM ("
			+ " SELECT SUM(OPRICE) AS PRICE"
			+ " FROM ORDER_"
			+ " WHERE TRUNC(ODATE) = TRUNC(SYSDATE-1)"
			+ " UNION ALL"
			+ " SELECT SUM(S.SUBPRICE) AS PRICE"
			+ " FROM SUBSINFO SI"
			+ " JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM"
			+ " WHERE TRUNC(SI.SINFOPERIOD) = TRUNC(SYSDATE-1)"
			+ " )";


	//   ORDER 테이블에서 제일 최신 (마지막) 에 저장된 주문 내역을 가져오기.
	//   (ORDER 테이블에서 ONUM 칼럼에 행 번호가 제일 큰 수를 ORDER 테이블로 부터 SELECT 하는 구문.)
	static final String SQL_SELECTONE_MAXONUM
	= "SELECT MID,ODATE,OPRICE,OSTATE,OADDRESS,ONUM FROM ORDER_ WHERE ONUM = (SELECT MAX(ONUM) FROM ORDER_)";   // AND MID = ?";

	static final String SQL_UPDATE = "UPDATE ORDER_ SET OSTATE = '결제완료' WHERE ONUM = ?";

	static final String SQL_DELETE = "";
	//   사용자가 주문 취소를 하면, 주문 정보 자체가 DELETE 되는 설계가 아니다.
	//   주문 취소를 하면, "주문 취소된 주문 입니다." 라고 보이게 할 예정 이다.


	public boolean insert(OrderVO oVO) {   //   주문 정보.

		try {
			int rs=jdbcTemplate.update(SQL_INSERT, oVO.getmID(), oVO.getoPrice(), oVO.getoState(), oVO.getoAddress());

			if(rs<=0) {
				return false;
			}
		} 
		catch (DataAccessException e) { 
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<OrderVO> selectAll(OrderVO oVO) {   //   주문 목록 전체 출력. (관리자 페이지)

		try {
			if (oVO.getSk().equals("MYORDER")) {
				Object[] args={oVO.getmID()};
				return jdbcTemplate.query(SQL_SELECTALL_MYORDER, args, new OrderRowMapper_SELECTALL_MYORDER());
			}
			else if(oVO.getSk().equals("ADMIN")) {
				return jdbcTemplate.query(SQL_SELECTALL_ADMIN, new OrderRowMapper_SELECTONE_SELECTALL_ADMIN());
			}

			else if(oVO.getSk().equals("MEMBER_TOTAL")) {
				return jdbcTemplate.query(SQL_SELECTALL_MEMBER_TOTAL, new OrderRowMapper_SELECTALL_MEMBER_TOTAL());            
			}
			else if(oVO.getSk().equals("MEMBER_SALE")) {
				Object[] args= {oVO.getmID()};
				return jdbcTemplate.query(SQL_SELECTALL_MEMBER_SALE, args, new OrderRowMapper_SELECTALL_MEMBER_SALE());            
			}
			else if(oVO.getSk().equals("YEAR_QUARTER_SALE")) {
				Object[] args={oVO.getYear()};
				return jdbcTemplate.query(SQL_SELECTALL_YEAR_QUARTER_SALE, args, new OrderRowMapper_SELECTALL_YEAR_QUARTER_SALE());   
			}
			else if(oVO.getSk().equals("YEAR_QUARTER_SALE_ALL")) {
				return jdbcTemplate.query(SQL_SELECTALL_YEAR_QUARTER_SALE_ALL, new OrderRowMapper_SELECTALL_YEAR_QUARTER_SALE_ALL());   
			}
			else if(oVO.getSk().equals("YEAR_SALE")) {
				return jdbcTemplate.query(SQL_SELECTALL_YEAR_SALE, new OrderRowMapper_SELECTALL_YEAR_SALE());   
			}
			else if(oVO.getSk().equals("WEEK_SALE")) {
				return jdbcTemplate.query(SQL_SELECTALL_WEEK_SALE, new OrderRowMapper_SELECTALL_WEEK_SALE());   
			}
		} 
		catch (DataAccessException e) { 
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public OrderVO selectOne(OrderVO oVO) {  

		try {
			if(oVO.getSk().equals("TOTAL")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_TOTAL, new OrderRowMapper_SELECTONE_TOTAL());
			}
			else if(oVO.getSk().equals("MAXONUM")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_MAXONUM, new OrderRowMapper_SELECTONE_SELECTALL_ADMIN());
			}
			else if(oVO.getSk().equals("TODAY_PRODUCTPRICE")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_TODAY_PRODUCTPRICE, new OrderRowMapper_SELECTONE_TOTAL2());
			}
			else if(oVO.getSk().equals("TODAY_SUBSPRICE")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_TODAY_SUBSPRICE, new OrderRowMapper_SELECTONE_TOTAL2());
			}
			else if(oVO.getSk().equals("TODAY_TOTAL")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_TODAY_TOTAL, new OrderRowMapper_SELECTONE_TOTAL2());
			}
			else if(oVO.getSk().equals("YESTERDAY_TOTAL")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_YESTERDAY_TOTAL, new OrderRowMapper_SELECTONE_TOTAL2());
			}
		}
		catch (DataAccessException e) { // 조회 결과가 없는 경우에 대한 처리
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean update(OrderVO oVO) {

		try {
			int rs=jdbcTemplate.update(SQL_UPDATE, oVO.getoNum());

			if(rs <= 0) {
				return false;
			}
		} 
		catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private boolean delete(OrderVO oVO) {

		return false;
	}
}


class OrderRowMapper_SELECTALL_MYORDER implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setRnum(rs.getInt("RNUM"));
			odata.setoNum(rs.getInt("ONUM"));
			odata.setmID(rs.getString("MID"));
			odata.setoPrice(rs.getInt("OPRICE"));
			odata.setoState(rs.getString("OSTATE"));
			odata.setoAddress(rs.getString("OADDRESS"));
			odata.setoDate(rs.getDate("ODATE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}

class OrderRowMapper_SELECTONE_SELECTALL_ADMIN implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setoNum(rs.getInt("ONUM"));
			odata.setmID(rs.getString("MID"));
			odata.setoPrice(rs.getInt("OPRICE"));
			odata.setoState(rs.getString("OSTATE"));
			odata.setoAddress(rs.getString("OADDRESS"));
			odata.setoDate(rs.getDate("ODATE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}

class OrderRowMapper_SELECTALL_MEMBER_TOTAL implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {


		OrderVO odata = new OrderVO();
		try {
			odata.setmID(rs.getString("MID"));
			odata.setProduct_price(rs.getInt("PRODUCT_PRICE"));
			odata.setSubs_price(rs.getInt("SUBS_PRICE"));
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}


class OrderRowMapper_SELECTALL_MEMBER_SALE implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {


		OrderVO odata = new OrderVO();
		try {
			odata.setmID(rs.getString("MID"));
			odata.setYear(rs.getInt("YEAR"));
			odata.setQuarter(rs.getInt("QUARTER"));
			odata.setProduct_price(rs.getInt("PRODUCT_PRICE"));
			odata.setSubs_price(rs.getInt("SUBS_PRICE"));
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}

class OrderRowMapper_SELECTALL_YEAR_QUARTER_SALE implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setYear(rs.getInt("YEAR"));
			odata.setQuarter(rs.getInt("QUARTER"));
			odata.setProduct_price(rs.getInt("PRODUCT_PRICE"));
			odata.setSubs_price(rs.getInt("SUBS_PRICE"));
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}

class OrderRowMapper_SELECTALL_YEAR_QUARTER_SALE_ALL implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setYear(rs.getInt("YEAR"));
			odata.setQuarter(rs.getInt("QUARTER"));
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}


class OrderRowMapper_SELECTALL_YEAR_SALE implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setYear(rs.getInt("YEAR"));
			odata.setProduct_price(rs.getInt("PRODUCT_PRICE"));
			odata.setSubs_price(rs.getInt("SUBS_PRICE"));
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}

class OrderRowMapper_SELECTALL_WEEK_SALE implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setoDate(rs.getDate("ODATE"));
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}


class OrderRowMapper_SELECTONE_TOTAL implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setProduct_price(rs.getInt("PRODUCT_PRICE"));
			odata.setSubs_price(rs.getInt("SUBS_PRICE"));
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}

class OrderRowMapper_SELECTONE_TOTAL2 implements RowMapper<OrderVO> {

	public OrderVO mapRow(ResultSet rs, int rowNum) {
		OrderVO odata = new OrderVO();
		try {
			odata.setTotal_price(rs.getInt("TOTAL_PRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return odata;
	}
}