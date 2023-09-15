package com.spring.biz.usecoupon;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.spring.biz.usecoupon.UsecouponVO;

@Service("usecouponDAO")
public class UsecouponDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 회원에게 추천인 쿠폰을 증정 ( 등급 쿠폰 지급은 트리거에서 해줌 )
	static final String SQL_INSERT
	= "INSERT INTO USECOUPON(UCNUM, MID, CNUM) VALUES ((SELECT NVL(MAX(UCNUM),0) + 1 FROM USECOUPON), ?, 1)";    

	// 마이페이지에서 보유한 쿠폰 모두 보기
	static final String SQL_SELECTALL_ALL
	= "SELECT ROW_NUMBER() OVER (ORDER BY UC.UCNUM) AS RNUM, C.CNAME, C.CDISCOUNT, UC.UCNUM, UC.UCFDATE, UC.UCABLE FROM USECOUPON UC JOIN COUPON C ON UC.CNUM = C.CNUM WHERE UC.MID = ?";    

	// 사용 가능한 쿠폰 보기
	static final String SQL_SELECTALL_ABLE
	= "SELECT ROW_NUMBER() OVER (ORDER BY UC.UCNUM) AS RNUM, C.CNAME, C.CDISCOUNT, UC.UCNUM, UC.UCFDATE, UC.UCABLE FROM USECOUPON UC JOIN COUPON C ON UC.CNUM = C.CNUM WHERE UC.MID = ? AND UCABLE = 1";    // 마이페이지에서 보유한 쿠폰 모두 보기

	static final String SQL_UPDATE
	= "UPDATE USECOUPON SET UCABLE = 0 WHERE UCNUM = ?";        // 쿠폰을 사용했다면 사용 가능 여부 0으로 만들어주기


	public boolean insert(UsecouponVO ucVO) {
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, ucVO.getmID());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<UsecouponVO> selectAll(UsecouponVO ucVO) {
		Object[] args = {ucVO.getmID()};

		try {
			if(ucVO.getSk().equals("ALL")) {
				return jdbcTemplate.query(SQL_SELECTALL_ALL, args, new UsecouponRowMapper_SELECTALL());
			}
			else if(ucVO.getSk().equals("ABLE")) {
				return jdbcTemplate.query(SQL_SELECTALL_ABLE, args, new UsecouponRowMapper_SELECTALL());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public UsecouponVO selectOne(UsecouponVO ucVO) {

		return null;
	}

	public boolean update(UsecouponVO ucVO) {
		System.out.println("log : UsecouponDAO2 : update()");

		try {
			int rs = jdbcTemplate.update(SQL_UPDATE, ucVO.getUcNum());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	private boolean delete(UsecouponVO ucVO) {

		return false;
	}    //   UsecouponDAO
}

class UsecouponRowMapper_SELECTALL implements RowMapper<UsecouponVO> {

	public UsecouponVO mapRow(ResultSet rs, int rowNum) {
		UsecouponVO ucdata = new UsecouponVO();
		try {
			ucdata.setRnum(rs.getInt("RNUM"));
			ucdata.setcName(rs.getString("CNAME"));
			ucdata.setcDiscount(rs.getDouble("CDISCOUNT"));
			ucdata.setUcNum(rs.getInt("UCNUM"));
			ucdata.setUcFdate(rs.getDate("UCFDATE"));
			ucdata.setUcAble(rs.getInt("UCABLE"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return ucdata;
	}
}

class UsecouponRowMapper_SELECTONE implements RowMapper<UsecouponVO> {

	public UsecouponVO mapRow(ResultSet rs, int rowNum) {
		UsecouponVO ucdata = new UsecouponVO();
		try {
			ucdata.setUcNum(rs.getInt("UCNUM"));
			ucdata.setmID(rs.getString("MID"));
			ucdata.setcNum(rs.getInt("CNUM"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return ucdata;
	}
}
