package com.spring.biz.subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//	static final String SQL_SELECTALL = "SELECT SUBNUM, SUBNAME, SUBPRICE FROM SUBSCRIPTION";
//	구독 종류 정보 보여주기.

//	static final String SQL_SELECTONE = "SELECT SUBNUM, SUBNAME, SUBPRICE FROM SUBSCRIPTION WHERE SUBNUM = ?";
//	해당 하는 구독 정보 보여주기.

@Repository("subscriptionDAO")
public class SubscriptionDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

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

	public List<SubscriptionVO> selectAll(SubscriptionVO sVO) {

		try {
			return jdbcTemplate.query(SQL_SELECTALL, new SubscriptionRowMapper());	
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public SubscriptionVO selectOne(SubscriptionVO sVO) {

		try {
			Object[] args = {sVO.getSubNum()};
			return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new SubscriptionRowMapper());
		}
		catch (DataAccessException e) { // 조회 결과가 없는 경우에 대한 처리
			e.printStackTrace();
			return null;
		}
	}

	private boolean update(SubscriptionVO sVO) {

		return false;
	}

	private boolean delete(SubscriptionVO sVO) {

		return false;
	}
}


class SubscriptionRowMapper implements RowMapper<SubscriptionVO>{

	@Override
	public SubscriptionVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		SubscriptionVO sdata = new SubscriptionVO();
		try {
			sdata.setSubNum(rs.getInt("SUBNUM"));
			sdata.setSubName(rs.getString("SUBNAME"));
			sdata.setSubPrice(rs.getInt("SUBPRICE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return sdata;
	}
}

