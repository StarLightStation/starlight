package com.spring.biz.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//   static final String SQL_UPDATE = "UPDATE PRODUCT SET PCNT = PCNT - ? WHERE PNUM = ?";

@Repository("productDAO")
public class ProductDAO2 {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   static final String SQL_INSERT // 샘플 데이터로 insert() 하기. => 리스너 서블릿.
         = "INSERT INTO PRODUCT (PNUM,PNAME,PPRICE,PIMAGE,PCNT,PCATEGORY,PALCOHOL,PSWEET,PSOUR,PSPARKLE,PIMAGEDETAIL) VALUES ((SELECT NVL(MAX(PNUM),0) + 1 FROM PRODUCT),?,?,?,?,?,?,?,?,?,?)";
   // 전체 상품
   static final String SQL_SELECTALL_ALL = "SELECT PNUM, PNAME, PPRICE, PIMAGE, PCNT, PCATEGORY, PALCOHOL, PSWEET, PSOUR, PSPARKLE, PIMAGEDETAIL"
         + " FROM PRODUCT";

   // 필터 검색
   static final String SQL_SELECTALL_FILTER = "SELECT P.PNUM,P.PNAME,P.PPRICE,P.PIMAGE,P.PCNT,P.PCATEGORY,P.PALCOHOL,P.PSWEET,P.PSOUR,P.PSPARKLE,P.PIMAGEDETAIL,"
         + " ROUND(AVG(B.BSTAR),1) AS PSTARAVG, COUNT(B.BSTAR) AS PSTARCNT"
         + " FROM PRODUCT P LEFT JOIN BOARD B ON P.PNUM = B.PNUM WHERE 1 = 1";

   static final String SQL_SELECTONE = "SELECT P.PNUM,P.PNAME,P.PPRICE,P.PIMAGE,P.PCNT,P.PCATEGORY,P.PALCOHOL,P.PSWEET,P.PSOUR,P.PSPARKLE,P.PIMAGEDETAIL, ROUND(AVG(B.BSTAR),1) AS PSTARAVG, COUNT(B.BSTAR) AS PSTARCNT"
         + " FROM PRODUCT P LEFT JOIN BOARD B ON P.PNUM = B.PNUM WHERE P.PNUM = ? GROUP BY P.PNUM,P.PNAME,P.PPRICE,P.PIMAGE,P.PCNT,P.PCATEGORY,P.PALCOHOL,P.PSWEET,P.PSOUR,P.PSPARKLE,P.PIMAGEDETAIL";
   static final String SQL_UPDATE_CNT = "UPDATE PRODUCT SET PCNT = PCNT - ? WHERE PNUM = ?";
   static final String SQL_UPDATE_DETAIL = "UPDATE PRODUCT SET PNAME = ?, PPRICE = ?, PCNT = ?, PALCOHOL = ?, PCATEGORY = ?, PSOUR = ?, PSWEET = ?, PSPARKLE = ? WHERE PNUM = ?";
   static final String SQL_DELETE = "DELETE FROM PRODUCT WHERE PNUM = ?";

   public boolean insert(ProductVO pVO) {

      try {
         int rs = jdbcTemplate.update(SQL_INSERT, pVO.getpName(), pVO.getpPrice(), pVO.getpImage(), pVO.getpCnt(),
               pVO.getpCategory(), pVO.getpAlcohol(), pVO.getpSweet(), pVO.getpSour(), pVO.getpSparkle(),
               pVO.getpImagedetail());

         if (rs <= 0) {
            return false;
         }
      } catch (DataAccessException e) {
    	  e.printStackTrace();
         return false;
      }
      return true;
   }

   public List<ProductVO> selectAll(ProductVO pVO) {

      Object[] args;

      try {
         if (pVO.getSk().equals("FILTER")) {
            ArrayList<Object> params = new ArrayList<Object>();
            // Object 타입은 자바에서 최상위 클래스라서, 어떤 타입의 객체라도 다 가져올 수 있다.

            StringBuilder queryBuilder = new StringBuilder(SQL_SELECTALL_FILTER);
            // StringBuilder는 문자열을 동적으로 조작하기 위한 클래스.
            // queryBuilder 참조변수로 append() 메서드를 사용해서, 객체에 문자열을 연속적으로 추가.

            // double 타입은 null로 비교가 불가 해서, Double 래퍼 클래스를 사용.
            Double pAlcohol = pVO.getpAlcohol();

            if (pVO.getpAlcohol() == 0.0) {
               pAlcohol = null;
            }
            // View 에서, 조건 선택 안했을 때, 디폴트값으로 0.0이 들어오는 경우 null로 처리 하기.
            // 도수 필터에서 두개의 값을 View 에서 받아야 하는데, 두 값 중에서 큰 값 하나만 받기.

            // System.out.println("log1");

            if (pVO.getpSweet() == null || !(pVO.getpSweet().equals("약")) && !(pVO.getpSweet().equals("중"))
                  && !(pVO.getpSweet().equals("강"))) {
               pVO.setpSweet(null);
            }
            if (pVO.getpSour() == null || !(pVO.getpSour().equals("약")) && !(pVO.getpSour().equals("중"))
                  && !(pVO.getpSour().equals("강"))) {
               pVO.setpSour(null);
            }
            if (pVO.getpSparkle() == null || !(pVO.getpSparkle().equals("약")) && !(pVO.getpSparkle().equals("중"))
                  && !(pVO.getpSparkle().equals("강"))) {
               pVO.setpSparkle(null);
            }

            // 이름 조건
            if (pVO.getpName() != null) {
               queryBuilder.append(" AND PNAME LIKE '%'|| ? || '%'");
               params.add(pVO.getpName());
            }
            // 카테고리 조건
            if (pVO.getpCategory() != null) {
               queryBuilder.append(" AND PCATEGORY = ?");
               params.add(pVO.getpCategory());
            }
            // 알콜 조건
            if (pAlcohol != null) {
               queryBuilder.append(" AND PALCOHOL BETWEEN ? AND ?");
               params.add(pAlcohol - 10.0); // 만약, 0.0도 ~ 10.0도 필터 였으면, 0.0도를 get 하면 pAlcohol이 null 이 되니까,
               params.add(pAlcohol); // 10.0도를 get 해서, params.add(0.0) 하고, params.add(10.0) 이 되도록 작성.
            }
            // 당도 조건
            if (pVO.getpSweet() != null) {
               queryBuilder.append(" AND PSWEET = ?"); // "SELECT * FROM PRODUCT WHERE 1=1" 쿼리문에,
               params.add(pVO.getpSweet());
            }
            // 신맛 조건
            if (pVO.getpSour() != null) {
               queryBuilder.append(" AND PSOUR = ?"); // if문의 조건이 맞으면,
               params.add(pVO.getpSour());
            }
            // 탄산 조건
            if (pVO.getpSparkle() != null) {
               queryBuilder.append(" AND PSPARKLE = ?"); // AND ~~ SQL 문을 append().
               params.add(pVO.getpSparkle());
            }

            queryBuilder.append(
                  " GROUP BY P.PNUM,P.PNAME,P.PPRICE,P.PIMAGE,P.PCNT,P.PCATEGORY,P.PALCOHOL,P.PSWEET,P.PSOUR,P.PSPARKLE,P.PIMAGEDETAIL");
            // 전체 상품을 보여주게 하기 위해서, GROUP BY P.PNUM 로 묶어주는 개념 이다.
            // AND ~~ 로 append()가 모두 끝나고, GROUP BY P.PNUM 을 append() 해주기.

            System.out.println("log : " + queryBuilder.toString());

            args = new Object[params.size()]; // args의 크기

            for (int i = 0; i < params.size(); i++) { // args 크기만큼 반복
               args[i] = params.get(i); // args[i] 에 arraylist params에 담아둔 값들 넣어주기
            }

            return jdbcTemplate.query(queryBuilder.toString(), args,
                  new ProductRowMapper_SELECTONE_SELECTALL_FILTER());
         } else if (pVO.getSk().equals("ALL")) {
            return jdbcTemplate.query(SQL_SELECTALL_ALL, new ProductRowMapper_SELECTALL_ALL());
         }
      } catch (DataAccessException e) {
    	  e.printStackTrace();
         return null;
      }
      return null;
   } // selectAll()

   public ProductVO selectOne(ProductVO pVO) {

      try {
         Object[] args = { pVO.getpNum() };
         return jdbcTemplate.queryForObject(SQL_SELECTONE, args, new ProductRowMapper_SELECTONE_SELECTALL_FILTER());

      } catch (DataAccessException e) { // 조회 결과가 없는 경우에 대한 처리
    	  e.printStackTrace();
         return null;
      }
   }

   public boolean update(ProductVO pVO) {

      try {
         int rs = 0;
         if (pVO.getSk().equals("UPDATEDETAIL")) {
            System.out.println("실행");
            rs = jdbcTemplate.update(SQL_UPDATE_DETAIL, pVO.getpName(), pVO.getpPrice(), pVO.getpCnt(),
                  pVO.getpAlcohol(), pVO.getpCategory(), pVO.getpSour(), pVO.getpSweet(), pVO.getpSparkle(),
                  pVO.getpNum());

         } else if(pVO.getSk().equals("CNT")){
            rs = jdbcTemplate.update(SQL_UPDATE_CNT, pVO.getTmpCnt(), pVO.getpNum());
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

   public boolean delete(ProductVO pVO) { // 회원 탈퇴.

      try {
         int rs = jdbcTemplate.update(SQL_DELETE, pVO.getpNum());

         if (rs <= 0) {
            return false;
         }
      } catch (DataAccessException e) {
    	  e.printStackTrace();
         return false;
      }
      return true;
   }

} // ProductDAO

class ProductRowMapper_SELECTONE_SELECTALL_FILTER implements RowMapper<ProductVO> {

   @Override
   public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
      ProductVO pdata = new ProductVO();
      try {
         pdata.setpNum(rs.getInt("PNUM"));
         pdata.setpName(rs.getString("PNAME"));
         pdata.setpPrice(rs.getInt("PPRICE"));
         pdata.setpImage(rs.getString("PIMAGE"));
         pdata.setpCnt(rs.getInt("PCNT"));
         pdata.setpCategory(rs.getString("PCATEGORY"));
         pdata.setpAlcohol(rs.getDouble("PALCOHOL"));
         pdata.setpSweet(rs.getString("PSWEET"));
         pdata.setpSour(rs.getString("PSOUR"));
         pdata.setpSparkle(rs.getString("PSPARKLE"));
         pdata.setpImagedetail(rs.getString("PIMAGEDETAIL"));
         pdata.setpStarAvg(rs.getDouble("PSTARAVG"));
         pdata.setpStarCnt(rs.getInt("PSTARCNT"));
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }

      return pdata;
   }
}

class ProductRowMapper_SELECTALL_ALL implements RowMapper<ProductVO> {

   @Override
   public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
      ProductVO pdata = new ProductVO();
      try {
         pdata.setpNum(rs.getInt("PNUM"));
         pdata.setpName(rs.getString("PNAME"));
         pdata.setpPrice(rs.getInt("PPRICE"));
         pdata.setpImage(rs.getString("PIMAGE"));
         pdata.setpCnt(rs.getInt("PCNT"));
         pdata.setpCategory(rs.getString("PCATEGORY"));
         pdata.setpAlcohol(rs.getDouble("PALCOHOL"));
         pdata.setpSweet(rs.getString("PSWEET"));
         pdata.setpSour(rs.getString("PSOUR"));
         pdata.setpSparkle(rs.getString("PSPARKLE"));
         pdata.setpImagedetail(rs.getString("PIMAGEDETAIL"));
      } catch (Exception e) {
         e.printStackTrace();
         return null;
      }

      return pdata;
   }

}