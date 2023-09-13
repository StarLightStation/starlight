package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//	WHERE 1=1 을 사용하는 이유 :
//	1=1 은 True를 의미 한다. 즉, 항상 참이므로,
//	기본적으로 조건이 없는 상태로 쿼리를 시작 할 수 있다.
//	즉, 조건문을 유연하게 추가 하기 위해서 작성했다.
//	조건문을 유연하게 추가 한다는 말은, 코드의 유지보수를 쉽게 할 수 있음을 의미.

//	[ 참고 ]
//	동적쿼리에서 특정 상황 마다 WHERE 절을 다르게 작성 해야 할 때 편리.
//	동적쿼리 ?
//	조건에 따라서, 결과값이 달라지는 쿼리를 의미.
//	파라미터를 어떻게 받냐에 따라, 조건값이 달라 진다.
//	조건값이 달라진다 == SELECT 하는 결과값이 달라진다.

//	static final String SQL_SELECTALL = "SELECT * FROM PRODUCT WHERE 1 = 1";
//	static final String SQL_SELECTONE = "SELECT * FROM PRODUCT WHERE PNUM = ?";
//	static final String SQL_UPDATE_STAR = "UPDATE PRODUCT SET PSTARSUM = PSTARSUM + ?, PSTARCNT = PSTARCNT + 1 WHERE PNUM = ?";
//	static final String SQL_UPDATE_ONLYSTAR = "UPDATE PRODUCT SET PSTARSUM = PSTARSUM + ? WHERE PNUM = ?";

//	static final String SQL_UPDATE = "UPDATE PRODUCT SET PCNT = PCNT - ? WHERE PNUM = ?";

public class ProductDAO {

	static final String SQL_INSERT	//	샘플 데이터로 insert() 하기. => 리스너 서블릿.
	= "INSERT INTO PRODUCT (PNAME,PPRICE,PIMAGE,PCNT,PCATEGORY,PALCOHOL,PSWEET,PSOUR,PSPARKLE,PIMAGEDETAIL) VALUES (?,?,?,?,?,?,?,?,?,?)";
	static final String SQL_SELECTALL
	= "SELECT P.*, ROUND(AVG(B.BSTAR),1) AS PSTARAVG, COUNT(B.BSTAR) AS PSTARCNT"
			+ " FROM PRODUCT P LEFT JOIN BOARD B ON P.PNUM = B.PNUM WHERE 1 = 1";  
	static final String SQL_SELECTONE
	= "SELECT P.*, ROUND(AVG(B.BSTAR),1) AS PSTARAVG, COUNT(B.BSTAR) AS PSTARCNT"
			+ " FROM PRODUCT P LEFT JOIN BOARD B ON P.PNUM = B.PNUM WHERE P.PNUM = ?";
	static final String SQL_UPDATE = "";
	static final String SQL_DELETE = "";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(ProductVO pVO) {

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setObject(1, pVO.getpName()); 
			pstmt.setObject(2, pVO.getpPrice()); 
			pstmt.setObject(3, pVO.getpImage());
			pstmt.setObject(4, pVO.getpCnt());
			pstmt.setObject(5, pVO.getpCategory());
			pstmt.setObject(6, pVO.getpAlcohol());
			pstmt.setObject(7, pVO.getpSweet());
			pstmt.setObject(8, pVO.getpSour());
			pstmt.setObject(9, pVO.getpSparkle());
			pstmt.setObject(10, pVO.getpImagedetail());

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

	public ArrayList<ProductVO> selectAll(ProductVO pVO) {

		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();

		conn = JDBCUtil.connect();

		try {
			ArrayList<Object> params = new ArrayList<Object>();	
			//	Object 타입은 자바에서 최상위 클래스라서, 어떤 타입의 객체라도 다 가져올 수 있다.

			StringBuilder queryBuilder = new StringBuilder(SQL_SELECTALL);	
			//	StringBuilder는 문자열을 동적으로 조작하기 위한 클래스.
			//	queryBuilder 참조변수로 append() 메서드를 사용해서, 객체에 문자열을 연속적으로 추가.

			//	double 타입은 null로 비교가 불가 해서, Double 래퍼 클래스를 사용.
			Double pAlcohol = pVO.getpAlcohol();

			//	System.out.println("log : " + pVO.getpName());
			//	System.out.println("log : " + pVO.getpCategory());
			//	System.out.println("log : " + pVO.getpAlcohol());
			//	System.out.println("log : " + pVO.getpSweet());
			//	System.out.println("log : " + pVO.getpSour());
			//	System.out.println("log : " + pVO.getpSparkle());

			if(pVO.getpAlcohol() == 0.0) {	
				pAlcohol = null;
			}
			//	View 에서, 조건 선택 안했을 때, 디폴트값으로 0.0이 들어오는 경우 null로 처리 하기.
			//	도수 필터에서 두개의 값을 View 에서 받아야 하는데, 두 값 중에서 큰 값 하나만 받기.

			//	System.out.println("log1");


			if(pVO.getpSweet() == null || !(pVO.getpSweet().equals("약")) && !(pVO.getpSweet().equals("중")) && !(pVO.getpSweet().equals("강"))) {
				pVO.setpSweet(null);
			}
			if(pVO.getpSour() == null || !(pVO.getpSour().equals("약")) && !(pVO.getpSour().equals("중")) && !(pVO.getpSour().equals("강"))) {
				pVO.setpSour(null);
			}
			if(pVO.getpSparkle() == null || !(pVO.getpSparkle().equals("약")) && !(pVO.getpSparkle().equals("중")) && !(pVO.getpSparkle().equals("강"))) {
				pVO.setpSparkle(null);
			}


			//	이름 조건
			if(pVO.getpName() != null) {
				queryBuilder.append(" AND PNAME LIKE CONCAT ('%', ?, '%')");	
				params.add(pVO.getpName());	
				//	System.out.println(pVO.getpName().getClass());
				//	System.out.println(pVO.getpName());
			}
			// 	카테고리 조건
			if(pVO.getpCategory() != null) {
				queryBuilder.append(" AND PCATEGORY = ?");	
				params.add(pVO.getpCategory());
				//	System.out.println(pVO.getpCategory().getClass());
				//	System.out.println(pVO.getpCategory());
			}
			// 	알콜 조건
			if(pAlcohol != null) {
				queryBuilder.append(" AND PALCOHOL BETWEEN ? AND ?");	
				params.add(pAlcohol - 10.0);	//	만약, 0.0도 ~ 10.0도 필터 였으면, 0.0도를 get 하면 pAlcohol이 null 이 되니까,
				params.add(pAlcohol);			//	10.0도를 get 해서, params.add(0.0) 하고, params.add(10.0) 이 되도록 작성. 
			}
			// 	당도 조건
			if(pVO.getpSweet() != null) {
				queryBuilder.append(" AND PSWEET = ?");	//	"SELECT * FROM PRODUCT WHERE 1=1" 쿼리문에,
				params.add(pVO.getpSweet());
				//	System.out.println(pVO.getpSweet().getClass());
				//	System.out.println(pVO.getpSweet());
			}
			// 	신맛 조건
			if(pVO.getpSour() != null) {
				queryBuilder.append(" AND PSOUR = ?");	//	if문의 조건이 맞으면,
				params.add(pVO.getpSour());
				//	System.out.println(pVO.getpSour().getClass());
				//	System.out.println(pVO.getpSour());
			}
			// 	탄산 조건
			if(pVO.getpSparkle() != null) {
				queryBuilder.append(" AND PSPARKLE = ?");	//	AND ~~ SQL 문을 append().
				params.add(pVO.getpSparkle());
				//	System.out.println(pVO.getpSparkle().getClass());
				//	System.out.println(pVO.getpSparkle());
			}

			queryBuilder.append(" GROUP BY P.PNUM");
			//	전체 상품을 보여주게 하기 위해서, GROUP BY P.PNUM 로 묶어주는 개념 이다.
			//	AND ~~ 로 append()가 모두 끝나고, GROUP BY P.PNUM 을 append() 해주기.

			System.out.println("log : " + queryBuilder.toString());

			// 	PreparedStatement 생성
			pstmt = conn.prepareStatement(queryBuilder.toString());	//	.toString() 메서드를 붙혀서 객체를 문자열화.

			//	파라미터 설정
			for (int i = 0; i < params.size(); i++) {	//	다같은 Object 타입이라서 for문 돌림.
				pstmt.setObject(i + 1, params.get(i));
				//	setObject() 메서드는 모든 데이터 타입의 값을 설정 가능.

				//	System.out.println("log : ProductDAO : " + params.get(i) + "\n");
			}

			rs = pstmt.executeQuery();

			// 	결과 처리
			while(rs.next()) {
				ProductVO pdata = new ProductVO();
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
				pdatas.add(pdata);
			}

			//	for(ProductVO data : pdatas) {
			//	System.out.println("log : ProductDAO : " + data + "\n");
			//	}

			//	System.out.println("log2");
		}

		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}
		return pdatas;

	}	//	selectAll()

	public ProductVO selectOne(ProductVO pVO) {

		conn = JDBCUtil.connect();

		ProductVO pdata = null;

		try {
			pstmt = conn.prepareStatement(SQL_SELECTONE);
			pstmt.setInt(1, pVO.getpNum());

			rs = pstmt.executeQuery();

			if(rs.next()) {
				pdata = new ProductVO();
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
		return pdata;
	}

	private boolean update(ProductVO pVO) {

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_UPDATE);
			pstmt.setInt(1, pVO.getpCnt());
			pstmt.setInt(2, pVO.getpNum());

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

	private boolean delete(ProductVO pVO) {

		return false;
	}

}	//	ProductDAO

