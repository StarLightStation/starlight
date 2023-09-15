package com.spring.biz.coupon;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("couponDAO")
public class CouponDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// 쿠폰의 이름/ 할인율 볼 때
	static final String SQL_SELECTONE = "SELECT CNUM, CNAME, CDISCOUT FROM COUPON WHERE CNUM = ?";

	private boolean insert(CouponVO cVO) {

		return false;
	}

	private List<CouponVO> selectAll(CouponVO cVO) {

		return null;
	}

	public CouponVO selectOne(CouponVO cVO) {

		try {
			Object[] args = {cVO.getcNum()};
			return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new CouponRowMapper());
		} 
		catch (DataAccessException e) { // 조회 결과가 없는 경우에 대한 처리
			e.printStackTrace();
			return null;
		}
	}

	private boolean update(CouponVO cVO) {

		return false;
	}


	private boolean delete(CouponVO cVO) {

		return false;
	}	//	CouponDAO
}

class CouponRowMapper implements RowMapper<CouponVO> {

	public CouponVO mapRow(ResultSet rs, int rowNum) {
		CouponVO cdata = new CouponVO();
		try {
			cdata.setcNum(rs.getInt("CNUM"));
			cdata.setcName(rs.getString("CNAME"));
			cdata.setcDiscount(rs.getDouble("CDISCOUNT"));
		} 
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return cdata;
	}
}



