package com.spring.biz.orderset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.order.OrderVO;
import com.spring.biz.orderdetail.OrderdetailVO;

@Repository("orderSetDAO")
public class OrderSetDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	static final String SQL_SELECTALL_MYORDER
	= "SELECT ROW_NUMBER() OVER (ORDER BY ONUM) AS RNUM, ONUM, MID, ODATE, OPRICE, OSTATE, OADDRESS FROM ORDER_ WHERE MID = ?";
	//	
	static final String SQL_SELECTALL_ORDERDETAIL
	= "SELECT ROW_NUMBER() OVER (ORDER BY OD.ONUM) AS RNUM, OD.ODNUM, P.PNUM, OD.ONUM, OD.ODCNT, O.MID, P.PNAME, P.PPRICE"
			+ " FROM ORDERDETAIL OD JOIN PRODUCT P ON OD.PNUM = P.PNUM JOIN ORDER_ O ON O.ONUM = OD.ONUM WHERE OD.ONUM = ?";
	//	ORDERDETAIL 테이블에서, WHERE절 조건에 맞는, oNum 열의 행을 가져오기. 

	public ArrayList<OrderSet> selectAll(OrderVO oVO) {

		conn = JDBCUtil.connect();

		ArrayList<OrderSet> datas = new ArrayList<OrderSet>();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL_MYORDER);
			pstmt.setString(1, oVO.getmID());

			rs = pstmt.executeQuery();

			while(rs.next()) {
				OrderSet os = new OrderSet();

				OrderVO data = new OrderVO();
				data.setoNum(rs.getInt("ONUM"));
				data.setmID(rs.getString("MID"));
				data.setoDate(rs.getDate("ODATE"));
				data.setoPrice(rs.getInt("OPRICE"));
				data.setoState(rs.getString("OSTATE"));
				data.setoAddress(rs.getString("OADDRESS"));
				data.setRnum(rs.getInt("RNUM"));
				os.setOrder(data);

				//	System.out.println("log : OrderSetDAO : OrderVO data : " + data + "\n");

				pstmt = conn.prepareStatement(SQL_SELECTALL_ORDERDETAIL);
				pstmt.setInt(1, data.getoNum());

				ResultSet rs2 = pstmt.executeQuery();

				ArrayList<OrderdetailVO> oddatas = new ArrayList<OrderdetailVO>();

				while(rs2.next()) {
					OrderdetailVO odVO = new OrderdetailVO();
					odVO.setOdNum(rs2.getInt("ODNUM"));
					odVO.setpNum(rs2.getInt("PNUM"));
					odVO.setoNum(rs2.getInt("ONUM"));
					odVO.setOdCnt(rs2.getInt("ODCNT"));
					odVO.setRnum(rs2.getInt("RNUM"));
					odVO.setmID(rs2.getString("MID"));
					odVO.setpName(rs2.getString("PNAME"));
					odVO.setpPrice(rs2.getInt("PPRICE"));

					oddatas.add(odVO);
				}

				//	System.out.println("log : OrderSetDAO : OrderdetailVO odVO : " + oddatas + "\n");

				os.setOddatas(oddatas);

				datas.add(os);

				//	System.out.println("log : OrderSetDAO : ArrayList<OrderSet> datas : " + datas + "\n");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return datas;
	}

}



