package com.spring.biz.subsinfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//   static final String SQL_SELECTALL = "SELECT * FROM SUBSINFO SI INNER JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM";
//   static final String SQL_SELECTONE = "SELECT * FROM SUBSINFO SI INNER JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM WHERE SI.MID = ?";
//   static final String SQL_UPDATE = "UPDATE SUBSINFO SET SINFOPERIOD = SINFOPERIOD + INTERVAL 1 MONTH WHERE MID = ?";
//   이벤트 스케줄러를 사용해서, 구독 기간이 만료되면, 회원의 구독 여부를 0으로 자동 변경.
//   구독을 연장 기능은 현재 없어서,
//   새로운 구독을 하고 싶으면 구독 기간 만료 후에, 구독을 다시 신청.

@Repository("subsinfoDAO")
public class SubsinfoDAO2 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String SQL_INSERT
            = "INSERT INTO SUBSINFO (SINFONUM,MID,SUBNUM) VALUES ((SELECT NVL(MAX(SINFONUM),0) + 1 FROM SUBSINFO),?,?)";
    // 관리자 페이지에서 회원의 구독 정보를 확인 ( 회원이 구독했었던 모든 정보(구독 만료된 것도 보여주기) )
    static final String SQL_SELECTALL
            = "SELECT SI.SINFONUM, SI.MID, SI.SUBNUM, SI.SINFOPERIOD, S.SUBNAME, S.SUBPRICE"
            + " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM WHERE SI.MID = ?";
    // 회원이 자신의 구독 정보 확인 ( 현재 구독한 상품만 보여줌 )
    static final String SQL_SELECTONE
            = "SELECT SI.SINFONUM, SI.MID, SI.SUBNUM, SI.SINFOPERIOD, S.SUBNAME, S.SUBPRICE"
            + " FROM SUBSINFO SI JOIN SUBSCRIPTION S ON SI.SUBNUM = S.SUBNUM WHERE SI.MID = ? AND SI.SINFOABLE = 1";

    static final String SQL_UPDATE = "";
    static final String SQL_DELETE = "";


    public boolean insert(SubsinfoVO siVO) { //   구독 상세 정보.

        try {
            int rs = jdbcTemplate.update(SQL_INSERT, siVO.getmID(), siVO.getSubNum());

            if (rs <= 0) {
                return false;
            }
        } catch (DataAccessException e) {
        	e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<SubsinfoVO> selectAll(SubsinfoVO siVO) {   //   구독 상세 정보 전체 출력. (관리자 페이지)
        try {
            Object[] args = {siVO.getmID()};

            return jdbcTemplate.query(SQL_SELECTALL, args, new SubsinfoRowMapper());
        } catch (DataAccessException e) {
        	e.printStackTrace();
            return null;
        }
    }


    public SubsinfoVO selectOne(SubsinfoVO siVO) {   //   회원의 구독 상세 정보 출력.

        try {
            Object[] args = {siVO.getmID()};

            return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new SubsinfoRowMapper());
        } catch (DataAccessException e) {
        	e.printStackTrace();
            return null;
        }
    }

    private boolean update(SubsinfoVO siVO) {

        return false;
    }

    private boolean delete(SubsinfoVO siVO) {

        return false;
    }

}

class SubsinfoRowMapper implements RowMapper<SubsinfoVO> {

    public SubsinfoVO mapRow(ResultSet rs, int rowNum) {
        SubsinfoVO sidata = new SubsinfoVO();
        try {
            sidata.setsInfoNum(rs.getInt("SINFONUM"));
            sidata.setmID(rs.getString("MID"));
            sidata.setSubNum(rs.getInt("SUBNUM"));
            sidata.setsInfoPeriod(rs.getDate("SINFOPERIOD"));
            sidata.setSubName(rs.getString("SUBNAME"));
            sidata.setSubPrice(rs.getInt("SUBPRICE"));

        } catch (SQLException e) {
            // 여기에 예외 처리 로직
            e.printStackTrace();
        }
        return sidata;
    }

}