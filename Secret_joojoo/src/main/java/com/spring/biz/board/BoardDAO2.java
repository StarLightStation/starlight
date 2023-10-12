package com.spring.biz.board;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

//	@Repository("boardDAO")
public class BoardDAO2 {

	//	@Autowired
	private JdbcTemplate jdbcTemplate;

	static final String SQL_INSERT
	= "INSERT INTO BOARD (BNUM,MID,MNAME,PNUM,BCONTENT,BSTAR) VALUES ((SELECT NVL(MAX(BNUM),0) + 1 FROM BOARD),?,?,?,?,?)";
	static final String SQL_SELECTALL_PRODUCT
	= "SELECT ROW_NUMBER() OVER (ORDER BY BNUM) AS RNUM, B.BNUM, B.MID, B.PNUM, B.BCONTENT, B.BSTAR, B.BDATE, B.MNAME, P.PNAME"
			+ " FROM BOARD B INNER JOIN PRODUCT P ON B.PNUM = P.PNUM"
			+ " WHERE P.PNUM = ?";
	//   보드 (리뷰) 테이블에 있는 모든 리뷰중에서, 해당 상품 (P.PNUM) 에 작성 되어 있는 리뷰를 SELECT 하기. (상품 이름이 필요해서 상품 테이블과 조인.)
	static final String SQL_SELECTALL_MYPAGE
	= "SELECT ROW_NUMBER() OVER (ORDER BY BNUM) AS RNUM, B.BNUM, B.MID, B.PNUM, B.BCONTENT, B.BSTAR, B.BDATE, B.MNAME, P.PNAME, P.PIMAGE"
			+ " FROM BOARD B INNER JOIN PRODUCT P ON B.PNUM = P.PNUM"
			+ " WHERE MID = ?";
	//   보드 (리뷰) 테이블에서 있는 모든 리뷰중에서, 내가 작성한 리뷰를 SELECT 하기. (상품 이름이 필요해서 상품 테이블과 조인.)
	static final String SQL_SELECTONE = "SELECT * FROM BOARD WHERE BNUM = ?";
	static final String SQL_UPDATE = "UPDATE BOARD SET BCONTENT = ?, BSTAR = ? WHERE BNUM = ?";
	static final String SQL_DELETE = "DELETE FROM BOARD WHERE BNUM = ?";

	public boolean insert(BoardVO bVO) {


		try {
			int rs = jdbcTemplate.update(SQL_INSERT, bVO.getmID(), bVO.getmName(), bVO.getpNum(), bVO.getbContent(), bVO.getbStar());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<BoardVO> selectAll(BoardVO bVO) {

		// Sk가 다른 것들 쿼리문에 들어갈 인자 값이 다르기 때문에 Sk마다 args를 다르게 선언
		try {
			if (bVO.getSk().equals("PRODUCT")) {
				Object[] args = {bVO.getpNum()};
				return jdbcTemplate.query(SQL_SELECTALL_PRODUCT, args, new BoardRowMapper_SELECTALL_PRODUCT());
			} else if (bVO.getSk().equals("MYPAGE")) {
				Object[] args = {bVO.getmID()};
				return jdbcTemplate.query(SQL_SELECTALL_MYPAGE, args, new BoardRowMapper_SELECTALL_MYPAGE());
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public BoardVO selectOne(BoardVO bVO) {

		try {
			Object[] args = {bVO.getbNum()};
			return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new BoardRowMapper_SELECTONE());
		} catch (DataAccessException e) { // 조회 결과가 없는 경우에 대한 처리
			e.printStackTrace();
			return null;
		}
	}

	public boolean update(BoardVO bVO) {

		try {
			int rs = jdbcTemplate.update(SQL_UPDATE, bVO.getbContent(), bVO.getbStar(), bVO.getbNum());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean delete(BoardVO bVO) {

		try {
			int rs = jdbcTemplate.update(SQL_DELETE, bVO.getbNum());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}    //   BoardDAO2


class BoardRowMapper_SELECTONE implements RowMapper<BoardVO> {

	public BoardVO mapRow(ResultSet rs, int rowNum) {
		BoardVO bdata = new BoardVO();
		try {
			bdata.setbNum(rs.getInt("BNUM"));
			bdata.setmID(rs.getString("MID"));
			bdata.setpNum(rs.getInt("PNUM"));
			bdata.setbContent(rs.getString("BCONTENT"));
			bdata.setbStar(rs.getDouble("BSTAR"));
			bdata.setbDate(rs.getDate("BDATE"));
			bdata.setmName(rs.getString("MNAME"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return bdata;
	}
}

class BoardRowMapper_SELECTALL_PRODUCT implements RowMapper<BoardVO> {

	public BoardVO mapRow(ResultSet rs, int rowNum) {
		BoardVO bdata = new BoardVO();
		try {
			bdata.setRnum(rs.getInt("RNUM"));
			bdata.setbNum(rs.getInt("BNUM"));
			bdata.setmID(rs.getString("MID"));
			bdata.setpNum(rs.getInt("PNUM"));
			bdata.setbContent(rs.getString("BCONTENT"));
			bdata.setbStar(rs.getDouble("BSTAR"));
			bdata.setbDate(rs.getDate("BDATE"));
			bdata.setmName(rs.getString("MNAME"));
			bdata.setpName(rs.getString("PNAME"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return bdata;
	}
}

class BoardRowMapper_SELECTALL_MYPAGE implements RowMapper<BoardVO> {

	public BoardVO mapRow(ResultSet rs, int rowNum) {
		BoardVO bdata = new BoardVO();
		try {
			bdata.setRnum(rs.getInt("RNUM"));
			bdata.setbNum(rs.getInt("BNUM"));
			bdata.setmID(rs.getString("MID"));
			bdata.setpNum(rs.getInt("PNUM"));
			bdata.setbContent(rs.getString("BCONTENT"));
			bdata.setbStar(rs.getDouble("BSTAR"));
			bdata.setbDate(rs.getDate("BDATE"));
			bdata.setmName(rs.getString("MNAME"));
			bdata.setpName(rs.getString("PNAME"));
			bdata.setpImage(rs.getString("PIMAGE"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return bdata;
	}
}

