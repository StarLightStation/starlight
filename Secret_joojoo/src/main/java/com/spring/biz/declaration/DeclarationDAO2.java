package com.spring.biz.declaration;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("declarationDAO")
public class DeclarationDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	static final String SQL_INSERT
	= "INSERT INTO DECLARATION (DNUM, BNUM, MID) VALUES ((SELECT NVL(MAX(DNUM),0) + 1 FROM DECLARATION),?,?)";		// 오라클은 AUTO INCREMENT가 안되기 때문에 쿼리문으로 PK를 1씩 늘려주는 서브쿼리를 만듬

	//  신고 당한 보드(리뷰) 전체 목록 보여주기 - 리뷰 내용과 리뷰 작성자가 필요해서 BOARD와 JOIN
	static final String SQL_SELECTALL_ALL
	= "SELECT ROW_NUMBER() OVER (ORDER BY DNUM) AS RNUM, B.BCONTENT, B.MID, D.DNUM, D.MID AS DMID, D.DDATE"
			+ " FROM DECLARATION D JOIN BOARD B ON D.BNUM = B.BNUM";
	// 어떤 회원이 신고한 모든 리뷰들 보기 - 신고 버튼 비활성화를 위해 필요
	static final String SQL_SELECTALL_MEMBER_DECLARATION
	= "SELECT B.BNUM"
			+ " FROM DECLARATION D JOIN BOARD B ON D.BNUM = B.BNUM"
			+ " WHERE D.MID = ? AND B.PNUM = ?";

	// 신고 당한 보드(리뷰) 상세 보기 - BOARD와 PRODUCT과 JOIN
	static final String SQL_SELECTONE = "SELECT B.MID, B.BCONTENT, B.BSTAR, B.BDATE, B.MNAME, D.DNUM, D.BNUM, D.MID AS DMID , D.DDATE, P.PNAME, P.PIMAGE"
			+ " FROM DECLARATION D JOIN BOARD B ON D.BNUM = B.BNUM JOIN PRODUCT P ON B.PNUM = P.PNUM WHERE D.DNUM = ?";
	static final String SQL_UPDATE = "";
	static final String SQL_DELETE = "DELETE FROM DECLARATION WHERE DNUM = ?";

	public boolean insert(DeclarationVO dVO) {

		try {
			int rs = jdbcTemplate.update(SQL_INSERT, dVO.getbNum(), dVO.getmID());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<DeclarationVO> selectAll(DeclarationVO dVO) {

		try {
			if(dVO.getSk().equals("ALL")) {
				return jdbcTemplate.query(SQL_SELECTALL_ALL, new DeclarationRowMapper_SELECTALL());
			}
			else if(dVO.getSk().equals("MEMBER_DECLARATION")) {
				Object[] args = {dVO.getmID(), dVO.getpNum()};
				return jdbcTemplate.query(SQL_SELECTALL_MEMBER_DECLARATION, args, new DeclarationRowMapper_SELECTALL_MEMBER_DECLARATION());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public DeclarationVO selectOne(DeclarationVO dVO) {

		try {
			Object[] args = {dVO.getdNum()};
			return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new DeclarationRowMapper_SELECTONE());
		} catch (DataAccessException e) { // 조회 결과가 없는 경우에 대한 처리
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(DeclarationVO dVO) {

		return false;
	}


	public boolean delete(DeclarationVO dVO) {

		try {
			int rs = jdbcTemplate.update(SQL_DELETE, dVO.getdNum());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}    //   DeclarationDAO

class DeclarationRowMapper_SELECTALL implements RowMapper<DeclarationVO> {

	public DeclarationVO mapRow(ResultSet rs, int rowNum) {
		DeclarationVO ddata = new DeclarationVO();
		try {
			ddata.setRnum(rs.getInt("RNUM"));
			ddata.setbContent(rs.getString("BCONTENT"));
			ddata.setmID(rs.getString("MID"));
			ddata.setdNum(rs.getInt("DNUM"));
			ddata.setDmID(rs.getString("DMID"));
			ddata.setdDate(rs.getDate("DDATE"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return ddata;
	}
}

class DeclarationRowMapper_SELECTALL_MEMBER_DECLARATION implements RowMapper<DeclarationVO> {

	public DeclarationVO mapRow(ResultSet rs, int rowNum) {
		DeclarationVO ddata = new DeclarationVO();
		try {
			ddata.setbNum(rs.getInt("BNUM"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return ddata;
	}
}

//SELECT B.MID, B.BCONTENT, B.BSTAR, B.BDATE, B.BNAME, D.DNUM, D.BNUM, D.DMID, D.DDATE, P.PNAME, P.PIMAGE
class DeclarationRowMapper_SELECTONE implements RowMapper<DeclarationVO> {

	public DeclarationVO mapRow(ResultSet rs, int rowNum) {
		DeclarationVO ddata = new DeclarationVO();
		try {
			ddata.setbContent(rs.getString("BCONTENT"));
			ddata.setmID(rs.getString("MID"));
			ddata.setbStar(rs.getDouble("BSTAR"));
			ddata.setbDate(rs.getDate("BDATE"));
			ddata.setbName(rs.getString("MNAME"));
			ddata.setdNum(rs.getInt("DNUM"));
			ddata.setbNum(rs.getInt("BNUM"));
			ddata.setDmID(rs.getString("DMID"));
			ddata.setdDate(rs.getDate("DDATE"));
			ddata.setpName(rs.getString("PNAME"));
			ddata.setpImage(rs.getString("PIMAGE"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return ddata;
	}
}