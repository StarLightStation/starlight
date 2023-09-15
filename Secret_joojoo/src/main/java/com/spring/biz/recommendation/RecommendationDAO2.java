package com.spring.biz.recommendation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("recommendationDAO")
public class RecommendationDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	static final String SQL_INSERT
	= "INSERT INTO RECOMMENDATION (RCNUM, BNUM, RCMID) VALUES ((SELECT NVL(MAX(RCNUM),0) + 1 FROM RECOMMENDATION),?,?)";
	//	static final String SQL_SELECTALL
	//	= "SELECT ROW_NUMBER() OVER (ORDER BY RCNUM) AS RNUM, B.BCONTENT, B.MID, D.DMID, D.DDATE"
	//			+ " FROM DECLARATION D JOIN BOARD B ON D.BNUM = B.BNUM";
	//	//  신고 당한 보드(리뷰) 전체 목록 보여주기 - 리뷰 내용과 리뷰 작성자가 필요해서 BOARD와 JOIN
	//	static final String SQL_SELECTONE = "SELECT B.MID, B.BCONTENT, B.BSTAR, B.BDATE, B.BNAME, D.DNUM, D.BNUM, D.DMID, D.DDATE, P.PNAME, P.PIMAGE"
	//			+ " FROM DECLARATION D JOIN BOARD B ON D.BNUM = B.BNUM JOIN PRODUCT P ON B.PNUM = P.PNUM WHERE D.DNUM = ?";
	//	// 신고 닿아 보드(리뷰) 상세 보기 - BOARD와 PRODUCT과 JOIN
	//	static final String SQL_UPDATE = "";
	static final String SQL_DELETE = "DELETE FROM RECOMMENDATION WHERE RCNUM = ?"; // pk 하나만 지워야되니까, 추천한 글을 다시 추천취소할때

	public boolean insert(RecommendationVO rcVO) {

		System.out.println("log : RecommendationDAO2 : insert()");

		try {
			int rs = jdbcTemplate.update(SQL_INSERT, rcVO.getbNum(), rcVO.getRcId());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			System.out.println("log : RecommendationDAO2 : insert() Fail...");
			return false;
		}
		return true;
	}

	public List<RecommendationVO> selectAll(RecommendationVO rcVO) {

		System.out.println("log : RecommendationDAO2 : selectAll()");
		return null;
	}

	public RecommendationVO selectOne(RecommendationVO rcVO) {

		System.out.println("log : RecommendationDAO2 : selectOne()");
		return null;
	}

	public boolean update(RecommendationVO rcVO) {

		return false;
	}


	public boolean delete(RecommendationVO rcVO) {

		System.out.println("log : RecommendationDAO2 : delete()");
		try {
			int rs = jdbcTemplate.update(SQL_DELETE, rcVO.getRcNum());

			if (rs <= 0) {
				return false;
			}
		} catch (DataAccessException e) {
			System.out.println("log : RecommendationDAO2 : delete() fail...");
			return false;
		}
		return true;
	}
}    //   RecommendationDAO



