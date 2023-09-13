package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {
	
	static final String sql_selectAll="SELECT NUM, CATEGORY FROM CATEGORY";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(CategoryVO cgVO) { 
		
		return true;
	}
	
	public ArrayList<CategoryVO> selectAll(CategoryVO cgVO) {

		ArrayList<CategoryVO> cdatas=new ArrayList<CategoryVO>();

		conn=JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(sql_selectAll);
			rs=pstmt.executeQuery();

			while(rs.next()) {
				cdatas.add(new CategoryVO(rs.getInt("NUM"), rs.getString("CATEGORY"))); // 출력은 fdate, name , rate 만
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		JDBCUtil.disconnect(rs, pstmt, conn);

		return cdatas;
	}

	public CategoryVO selectOne(CategoryVO cgVO) {

		return null;
	}

	public boolean update(CategoryVO cgVO) {

		return true;
	}

	public boolean delete(CategoryVO cgVO) {

		return false;
	}

}
