package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDAO { // Book 이랑 Member 합친거

	static final String sql_insert="INSERT INTO CART (MIDFK, BNUMFK, CNT) VALUES(?,?,?)";

	static final String sql_selectAll="SELECT C.NUM, C.MIDFK, C.BNUMFK, C.CNT, B.TITLE, B.PRICE, ROW_NUMBER() OVER (ORDER BY C.NUM) AS RNUM FROM CART C JOIN BOOK B ON C.BNUMFK = B.NUM WHERE C.MIDFK = ?";
	//	ROW_NUMBER() => 조건에 맞는 행을 가져온다 . (동일한 행을 다 가져오는 느낌)
	//	OVER은 뭐야 ?
	//	AS RNUM => 그렇게 가져온 테이블의 별칭을 RNUM으로 해서 만들어줘.
	//	JOIN 함수를 사용하면, ON과 같이 사용 해야하는 듯?
	//	FROM CART C JOIN BOOK B ON C.BNUMFK = B.NUM
	//	=>	CART 테이블로 부터, 근데 별칭이 C이고, JOIN 할거야. BOOK이랑 BOOK의 별칭은 B야
	//	B를 ON 할거야. 카트 테이블에 있는 북 테이블 PK를 의미하는 FK는 북 테이블의 PK와 같아.
	//	정리 : JOIN 했을 때, FK로 사용하고 있는 부분을 가져온 원래 테이블의 PK와 같다는 것을 명시해 줘야 하는듯.
	//	WHERE C.MIDFK = ?
	//	조건이 카트의 사용자 아이디 이다. 근데 이 아이디는 FK 이다. Member 테이블의 아이디 PK를 의미하는듯.
	
	static final String sql_selectOne="SELECT NUM, MIDFK ,BNUMFK FROM CART WHERE MIDFK= ? AND BNUMFK = ?";

	static final String sql_update = "UPDATE CART SET CNT = CNT + ? WHERE NUM = ?";
	
	static final String sql_delete_ONE="DELETE FROM CART WHERE MIDFK = ? AND BNUMFK = ?";
	static final String sql_delete_ALL="DELETE FROM CART WHERE MIDFK = ?";
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(CartVO caVO) { 

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_insert);
			pstmt.setString(1, caVO.getMidfk()); 
			pstmt.setInt(2, caVO.getBnumfk());
			pstmt.setInt(3, caVO.getCnt());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}

	public ArrayList<CartVO> selectAll(CartVO caVO) {
		
		// 내 장바구니 출력 
		//		final String sql_selectAll_CART="SELECT C.NUM, C.MIDFK, C.BNUMFK, B.TITLE, B.PRICE, C.CNT FROM CART C JOIN BOOK B ON // C.CNUMFK //  = B.NUM WHERE C.MNUMFK = ?";
		ArrayList<CartVO> cdatas=new ArrayList<CartVO>();

		//		int num, String midfk, int bnumfk, String title, int price, int cnt

		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_selectAll);
			pstmt.setString(1, caVO.getMidfk());	//	로그인한 회원의 아이디의 정보를 카트의 midFK가 가지고 있는듯.
			rs=pstmt.executeQuery();

			while(rs.next()) {
				CartVO cdata = new CartVO(rs.getInt("NUM"), rs.getString("MIDFK"), rs.getInt("BNUMFK"),rs.getInt("CNT"));
				cdata.setTmpTitle(rs.getString("TITLE"));
				cdata.setTmpPrice(rs.getInt("PRICE"));
				cdata.setTmpRowNum(rs.getInt("RNUM"));
				cdatas.add(cdata);	// 출력은 title, price, cnt만
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		JDBCUtil.disconnect(rs, pstmt, conn);

		return cdatas;
	}

	public CartVO selectOne(CartVO caVO) {		// 컨트롤러에서 selectOne으로 mid랑 bnum이랑 같은게 있는지 찾기 ! 있으면 cart.update 없으면 cart.insert  if(cDAT.selectOne == null) insert /else update
		
		CartVO cdata=null;

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_selectOne);
			pstmt.setString(1, caVO.getMidfk());
			pstmt.setInt(2, caVO.getBnumfk());

			rs=pstmt.executeQuery();

			if(rs.next()) {	// 값이 있으면 중복된거
				cdata=new CartVO(rs.getInt("NUM"), rs.getString("MIDFK"),rs.getInt("BNUMFK"));
				
			} 	
			else {	// 값이 없다면 중복 안 된거
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		JDBCUtil.disconnect(rs, pstmt, conn);

		return cdata;
	}

	public boolean update(CartVO caVO) {
		
//		static final String sql_update = "UPDATE CART SET CNT = CNT + ? WHERE NUM = ?";
		
		conn = JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(sql_update);	
			pstmt.setInt(1, caVO.getCnt());
			pstmt.setInt(2, caVO.getNum());
			

			int result = pstmt.executeUpdate();
			if(result <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		JDBCUtil.disconnect(rs, pstmt, conn);
		return true;
	}

	public boolean delete(CartVO caVO) {
		
		conn=JDBCUtil.connect();
		try {
			if(caVO.getSk().equals("한개삭제")) {
	            pstmt=conn.prepareStatement(sql_delete_ONE);
	            pstmt.setString(1, caVO.getMidfk());
	            pstmt.setInt(2, caVO.getBnumfk());
	         }
			else if(caVO.getSk().equals("전체삭제")) {
				pstmt=conn.prepareStatement(sql_delete_ALL);
				pstmt.setString(1, caVO.getMidfk());			
			}

			int result=pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		JDBCUtil.disconnect(pstmt, conn);
		return true;
	}

}
