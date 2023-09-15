package com.spring.biz.member;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//   static final String SQL_SELECTONE_FINDPW
//   = "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND FROM MEMBER WHERE MID = ?";
//   비밀번호 찾기는 중간 프로젝트 이후 추가할 예정.
//   static final String SQL_UPDATE_CHANGENAME = "UPDATE MEMBER SET MNAME = ? WHERE MID = ?";
//   PW, NAME SQL문 합치기로 해서 주석 처리.

@Repository("memberDAO")
public class MemberDAO2 {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 회원 가입
	static final String SQL_INSERT = "INSERT INTO MEMBER (MID,MPW,MNAME,MPHONE,SIGNUPKIND) VALUES (?,?,?,?,?)";

	// 관리자가 아닌 모든 회원 출력
	static final String SQL_SELECTALL = "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND,MGRADE FROM MEMBER WHERE ISADMIN = 0";

	// 로그인 할 때
	static final String SQL_SELECTONE_SIGNIN
	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND,MGRADE FROM MEMBER WHERE MID = ? AND MPW = ?";

	// 특정 회원의 정보
	static final String SQL_SELECTONE_INFO
	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND,MGRADE FROM MEMBER WHERE MID = ?";

	// 회원의 아이디 찾기 - 전화번호로 아이디 찾기 가능
	static final String SQL_SELECTONE_FINDID
	= "SELECT MID,MPW,MNAME,SUBSCRIPTION,ISADMIN,MPHONE,SIGNUPKIND,MGRADE FROM MEMBER WHERE MPHONE = ?";

	// 총 회원 수
	static final String SQL_SELECTONE_COUNT
	= "SELECT COUNT(MID) AS CNT FROM MEMBER";

	// 회원 정보 변경
	static final String SQL_UPDATE_CHANGE = "UPDATE MEMBER SET MPW = ?, MNAME = ?, MPHONE = ? WHERE MID = ?";

	// 회원의 구독 여부 
	static final String SQL_UPDATE_CHANGESUBS = "UPDATE MEMBER SET SUBSCRIPTION = 1 WHERE MID = ?";

	// 회원 탈퇴
	static final String SQL_DELETE = "DELETE FROM MEMBER WHERE MID = ?";


	public boolean insert(MemberVO mVO) {
		try {
			int rs = jdbcTemplate.update(SQL_INSERT, mVO.getmID(), mVO.getmPW(), mVO.getmName(), mVO.getmPhone(), mVO.getSignUpKind());

			if (rs <= 0) {
				return false;
			}
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<MemberVO> selectAll(MemberVO mVO) {   //   회원 목록 전체 출력. (관리자 페이지)

		try {
			return jdbcTemplate.query(SQL_SELECTALL ,new MemberRowMapper());
		}
		catch(DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public MemberVO selectOne(MemberVO mVO) {   //   1) 로그인 2) 회원 정보 3) 회원 가입 4) 아이디 찾기

		try {
			if (mVO.getSk().equals("SIGNIN")) {
				Object[] args = {mVO.getmID(), mVO.getmPW()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE_SIGNIN, args, new MemberRowMapper());
			}

			else if (mVO.getSk().equals("INFO")) {
				Object[] args = {mVO.getmID()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE_INFO, args, new MemberRowMapper());
			} 

			else if (mVO.getSk().equals("FINDID")) {
				Object[] args = {mVO.getmPhone()};
				return jdbcTemplate.queryForObject(SQL_SELECTONE_FINDID, args, new MemberRowMapper());
			}

			else if (mVO.getSk().equals("COUNT")) {
				return jdbcTemplate.queryForObject(SQL_SELECTONE_COUNT, new MemberRowMapper_SELECTONE_COUNT());
			}
		} 
		catch (DataAccessException e) { // 조회 결과가 없는 경우에 대한 처리
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean update(MemberVO mVO) {   //   회원 정보 변경. (비밀번호 변경, 닉네임 변경, 구독 정보 변경.)
		int rs = 0;

		try {
			if (mVO.getSk().equals("CHANGE")) {
				rs = jdbcTemplate.update(SQL_UPDATE_CHANGE, mVO.getTmpMpw(), mVO.getTmpMname(), mVO.getTmpMphone(), mVO.getmID());
			} else if (mVO.getSk().equals("CHANGESUBS")) {
				rs = jdbcTemplate.update(SQL_UPDATE_CHANGESUBS, mVO.getmID());
			}

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean delete(MemberVO mVO) {   //   회원 탈퇴.

		try {
			int rs=jdbcTemplate.update(SQL_DELETE, mVO.getmID());

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
}

class MemberRowMapper implements RowMapper<MemberVO> {

	public MemberVO mapRow(ResultSet rs, int rowNum) {

		MemberVO mdata = new MemberVO();
		try {
			mdata.setmID(rs.getString("MID"));
			mdata.setmPW(rs.getString("MPW"));
			mdata.setmName(rs.getString("MNAME"));
			mdata.setSubscription(rs.getInt("SUBSCRIPTION"));
			mdata.setIsAdmin(rs.getInt("ISADMIN"));
			mdata.setmPhone(rs.getString("MPHONE"));
			mdata.setSignUpKind(rs.getString("SIGNUPKIND"));
			mdata.setmGrade(rs.getString("MGRADE"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return mdata;
	}
}

class MemberRowMapper_SELECTONE_COUNT implements RowMapper<MemberVO> {

	public MemberVO mapRow(ResultSet rs, int rowNum) {
		MemberVO mdata = new MemberVO();
		try {
			mdata.setCnt(rs.getInt("CNT"));
		}
		catch (Exception e) {
			e.printStackTrace(); 
			return null;
		}

		return mdata;
	}
}
//   MemberDAO