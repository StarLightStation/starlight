package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class BookDAO {
	
	static final String sql_INSERT="INSERT INTO BOOK (TITLE, PRICE,CNT ,CATEGORY) VALUES(?,?,?,?)";
	
	static final String sql_selectAll_NAME="SELECT B.NUM, B.TITLE, B.PRICE,B.CNT,B.CATEGORY, C.CATEGORY FROM BOOK B JOIN CATEGORY C ON B.CATEGORY = C.NUM WHERE TITLE LIKE CONCAT('%', ?, '%')";	//	=>	%?%
	static final String sql_selectAll_PRICE="SELECT B.NUM, B.TITLE, B.PRICE,B.CNT,B.CATEGORY, C.CATEGORY FROM BOOK B JOIN CATEGORY C ON B.CATEGORY = C.NUM WHERE ?<=B.PRICE AND B.PRICE<=?";
	static final String sql_selectAll_MINPRICE="SELECT B.NUM, B.TITLE, B.PRICE,B.CNT,B.CATEGORY, C.CATEGORY FROM BOOK B JOIN CATEGORY C ON B.CATEGORY = C.NUM WHERE B.PRICE IN (SELECT MIN(PRICE) FROM BOOK)" ;
	static final String sql_selectAll_MAXPRICE="SELECT B.NUM, B.TITLE, B.PRICE,B.CNT,B.CATEGORY, C.CATEGORY FROM BOOK B JOIN CATEGORY C ON B.CATEGORY = C.NUM WHERE B.PRICE IN (SELECT MAX(PRICE) FROM BOOK)" ;
	
//	static final String sql_selectOne="SELECT NUM,TITLE,PRICE,CNT,CATEGORY FROM BOOK WHERE NUM=?";

	static final String sql_selectOne="SELECT B.NUM, B.TITLE, B.PRICE,B.CNT, B.CATEGORY,C.CATEGORY FROM BOOK B JOIN CATEGORY C ON B.CATEGORY = C.NUM WHERE B.NUM=?";
	
	static final String sql_update_CNTPLUS = "UPDATE BOOK SET CNT = CNT + ? WHERE NUM =?";	//	책 재고 더하기.
	static final String sql_update_CNTMINUS = "UPDATE BOOK SET CNT = CNT - ? WHERE NUM =?";	//	책 재고 빼기.
	
	
	Random rand = new Random();
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(BookVO bVO) { 
		
		conn=JDBCUtil.connect();
		try {
			// 크롤링한 데이터 삽입
			pstmt=conn.prepareStatement(sql_INSERT);
			pstmt.setString(1, bVO.getTitle());
			pstmt.setInt(2, bVO.getPrice());
			pstmt.setInt(3, bVO.getCnt());
			pstmt.setInt(4, bVO.getCategory());
			//			-------------------------------------
			// 관리자모드에서 책 추가하기
			// C에서 넘겨받은 정보를 add함
			//-----------------------------------
			int result=pstmt.executeUpdate();
			// Query -> SELECT
			// Update -> INSERT,UPDATE,DELETE

			if(result<=0) {
				// 적용된 row가 없음!
				return false;
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public ArrayList<BookVO> selectAll(BookVO bVO){
		
		// selectAll
		//		// 책 전체 목록 출력 + 이름 검색
		//		 만약 searchCondition이 "이름"이라면
		//		 조건에 맞는 값 C에 전달
		//		// 최저가 
		//		 만약 searchCondition이 "최고가"라면
		//		 조건에 맞는 값 C에 전달
		//		// 최고가
		//		 만약 searchCondition이 "최저가"라면
		//		 조건에 맞는 값 C에 전달
		//		// 필터 검색
		//		 만약 searchCondition이 "필터검색"이라면
		//		 조건에 맞는 값 C에 전달
		//		final String sql_selectAll_NAME="SELECT NUM,TITLE,PRICE,CNT,CATEGORY FROM BOOK WHERE TITLE LIKE CONCAT('%', ?, '%')";
		//		final String sql_selectAll_PRICE="SELECT NUM,TITLE,PRICE,CNT,CATEGORY FROM BOOK WHERE ?<=PRICE AND PRICE<=?";
		//		final String sql_selectAll_MINPRICE="SELECT NUM,TITLE,PRICE,CNT,CATEGORY FROM BOOK WHERE PRICE IN (SELECT MIN(PRICE) FROM BOOK)" ;
		//		final String sql_selectAll_MAXPRICE="SELECT NUM,TITLE,PRICE,CNT,CATEGORY FROM BOOK WHERE PRICE IN (SELECT MAX(PRICE) FROM BOOK)" ;

		ArrayList<BookVO> bdatas=new ArrayList<BookVO>();

		conn=JDBCUtil.connect();
		try {
			if(bVO.getSk().equals("이름검색")) {
				pstmt = conn.prepareStatement(sql_selectAll_NAME);
				pstmt.setString(1, bVO.getChangeTitle());
			}
			else if(bVO.getSk().equals("필터검색")) {
				pstmt = conn.prepareStatement(sql_selectAll_PRICE);
				pstmt.setInt(1, bVO.getMinprice());
				pstmt.setInt(2, bVO.getMaxprice());	
			}
			else if(bVO.getSk().equals("최저가")) {
				pstmt = conn.prepareStatement(sql_selectAll_MINPRICE);
			}
			else if(bVO.getSk().equals("최고가")) {
				pstmt = conn.prepareStatement(sql_selectAll_MAXPRICE);
			}
			rs=pstmt.executeQuery();

			while(rs.next()) {
				BookVO bdata = new BookVO(rs.getInt("NUM"),rs.getString("TITLE"),rs.getInt("PRICE"),rs.getInt("CNT"),rs.getInt("B.CATEGORY"));
				bdata.setTmpCategory(rs.getString("C.CATEGORY"));
				bdatas.add(bdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		JDBCUtil.disconnect(rs, pstmt, conn);

		return bdatas;
	}

	public BookVO selectOne(BookVO bVO) {
		
		// selectOne 
		//		final String sql_selectOne="SELECT NUM,TITLE,PRICE,CNT,CATEGORY FROM BOOK WHERE NUM=?";

		BookVO bdata=null;

		conn=JDBCUtil.connect();
		try {
			//		// 책 랜덤 추천(해당 카테고리에 있는 책 랜덤으로 하나 추천) - 카테고리 입력값 줘 !
			//		  C에서 받아온 랜덤값으로 반환
			if(bVO.getSk().equals("랜덤추천")) {
				pstmt = conn.prepareStatement(sql_selectOne);
				pstmt.setInt(1, (rand.nextInt(20) + 1) + (20 * (bVO.getCategory() - 1))); // 카테고리별 20개 끊기
			}
			//		// 책 한권 출력 
			//		  책 번호 받아서 그 번호에 해당하는 책 한권 출력
			else if(bVO.getSk().equals("한권출력")) {
				pstmt = conn.prepareStatement(sql_selectOne);
				pstmt.setInt(1, bVO.getNum());	
			}
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				bdata=new BookVO(rs.getInt("NUM"),rs.getString("TITLE"),rs.getInt("PRICE"),rs.getInt("CNT"), rs.getInt("B.CATEGORY"));
				bdata.setTmpCategory(rs.getString("C.CATEGORY"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		JDBCUtil.disconnect(rs, pstmt, conn);

		return bdata;
	}

	public boolean update(BookVO bVO) {
		
		// update
		// 재고 변경(관리자모드)
		//		 C 에서 "재고빼기" / 개수까지 받아오기
		//		 setcnt (- 받아온 개수)
		//		 C 에서 "재고추가" 
		//		 setcnt (+ 받아온 개수)
		
		// 책 구매시 재고 감소
		//		책 구매시 구매한 개수 C 에서 받아오기 
		//		setcnt( cnt - 구매한 개수)
		

		
		conn = JDBCUtil.connect();
		try {
			if(bVO.getSk().equals("재고추가")) { 
				pstmt=conn.prepareStatement(sql_update_CNTPLUS);	
				pstmt.setInt(1, bVO.getTmpCnt());
				pstmt.setInt(2, bVO.getNum());
			}
			else if(bVO.getSk().equals("재고빼기")) { // 재고 빼기 / 구매하기
				pstmt=conn.prepareStatement(sql_update_CNTMINUS);	
				pstmt.setInt(1, bVO.getTmpCnt());
				pstmt.setInt(2, bVO.getNum());
			}
			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(BookVO bVO) {
		return false;
	}
	
}
