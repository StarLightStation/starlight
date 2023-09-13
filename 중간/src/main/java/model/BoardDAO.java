package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {

	static final String SQL_INSERT = "INSERT INTO BOARD (MID,MNAME,PNUM,BCONTENT,BSTAR) VALUES (?,?,?,?,?)";
	static final String SQL_SELECTALL_PRODUCT
	= "SELECT ROW_NUMBER() OVER (ORDER BY BNUM) AS RNUM, B.BNUM, B.MID, B.PNUM, B.BCONTENT, B.BSTAR, B.BDATE, B.MNAME, P.PNAME"
			+ " FROM BOARD B INNER JOIN PRODUCT P ON B.PNUM = P.PNUM"
			+ " WHERE P.PNUM = ?";
	//	보드 (리뷰) 테이블에 있는 모든 리뷰중에서, 해당 상품 (P.PNUM) 에 작성 되어 있는 리뷰를 SELECT 하기. (상품 이름이 필요해서 상품 테이블과 조인.)
	static final String SQL_SELECTALL_MYPAGE
	= "SELECT ROW_NUMBER() OVER (ORDER BY BNUM) AS RNUM, B.BNUM, B.MID, B.PNUM, B.BCONTENT, B.BSTAR, B.BDATE, B.MNAME, P.PNAME"
			+ " FROM BOARD B INNER JOIN PRODUCT P ON B.PNUM = P.PNUM"
			+ " WHERE MID = ?";
	//	보드 (리뷰) 테이블에서 있는 모든 리뷰중에서, 내가 작성한 리뷰를 SELECT 하기. (상품 이름이 필요해서 상품 테이블과 조인.)
	static final String SQL_SELECTONE = "SELECT * FROM BOARD WHERE BNUM = ?";
	static final String SQL_UPDATE = "UPDATE BOARD SET BCONTENT = ?, BSTAR = ? WHERE BNUM = ?";
	static final String SQL_DELETE = "DELETE FROM BOARD WHERE BNUM = ?";

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public boolean insert(BoardVO bVO) {

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, bVO.getmID());
			pstmt.setString(2, bVO.getmName());
			pstmt.setInt(3, bVO.getpNum());
			pstmt.setString(4, bVO.getbContent());
			pstmt.setDouble(5, bVO.getbStar());

			int result = pstmt.executeUpdate();

			if(result <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public ArrayList<BoardVO> selectAll(BoardVO bVO) {

		ArrayList<BoardVO> bdatas = new ArrayList<BoardVO>();

		conn = JDBCUtil.connect();

		try {
			if(bVO.getSk().equals("PRODUCT")) {
				pstmt = conn.prepareStatement(SQL_SELECTALL_PRODUCT);
				pstmt.setInt(1, bVO.getpNum());
			}
			else if(bVO.getSk().equals("MYPAGE")) {
				pstmt = conn.prepareStatement(SQL_SELECTALL_MYPAGE);
				pstmt.setString(1, bVO.getmID());
			}

			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardVO bdata = new BoardVO();
				bdata.setRnum(rs.getInt("RNUM"));
				bdata.setbNum(rs.getInt("BNUM"));
				bdata.setmID(rs.getString("MID"));
				bdata.setpNum(rs.getInt("PNUM"));
				bdata.setbContent(rs.getString("BCONTENT"));
				bdata.setbStar(rs.getDouble("BSTAR"));
				bdata.setbDate(rs.getDate("BDATE"));
				bdata.setmName(rs.getString("MNAME"));
				bdata.setpName(rs.getString("PNAME"));
				bdatas.add(bdata);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return null;
			}
		}	
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return bdatas;
	}

	public BoardVO selectOne(BoardVO bVO) {

		conn = JDBCUtil.connect();

		BoardVO bdata = null;

		try {
			pstmt = conn.prepareStatement(SQL_SELECTONE);
			pstmt.setInt(1, bVO.getbNum());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				bdata = new BoardVO();
				bdata.setbNum(rs.getInt("BNUM"));
				bdata.setmID(rs.getString("MID"));
				bdata.setpNum(rs.getInt("PNUM"));
				bdata.setbContent(rs.getString("BCONTENT"));
				bdata.setbStar(rs.getDouble("BSTAR"));
				bdata.setbDate(rs.getDate("BDATE"));
				bdata.setmName(rs.getString("MNAME"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return null;
			}
		}	
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return bdata;
	}

	public boolean update(BoardVO bVO) {

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, bVO.getbContent());
			pstmt.setDouble(2, bVO.getbStar());
			pstmt.setInt(3, bVO.getbNum());

			int result = pstmt.executeUpdate();

			if(result <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return false;
			}
		}	
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

	public boolean delete(BoardVO bVO) {

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, bVO.getbNum());

			int result = pstmt.executeUpdate();

			if(result <= 0) {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
				return false;
			}
		}	
		finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}

}	//	BoardDAO
