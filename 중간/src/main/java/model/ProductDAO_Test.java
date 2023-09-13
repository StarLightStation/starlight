package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO_Test {

	static final String SQL_INSERT
	= "INSERT INTO PRODUCT (PNAME,PPRICE,PIMAGE,PCNT,PCATEGORY,PALCOHOL,PSWEET,PSOUR,PSPARKLE) VALUES (?,?,?,?,?,?,?,?,?)";
	static final String SQL_SELECTALL
	= "SELECT * FROM PRODUCT"
			+ " WHERE PCATEGORY = ?"
			+ " AND PALCOHOL BETWEEN ? AND ?"
			+ " AND PSWEET = ?"	
			+ " AND PSOUR = ?"
			+ " AND PSPARKLE = ?"
			;
	static final String SQL_SELECTONE = "";
	static final String SQL_UPDATE = "";
	static final String SQL_DELETE = "";

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public boolean insert(ProductVO pVO) { 

		String csvFile
		= "C:/Users/dhfg0/Desktop/국비 수업 복습/최종팀 프로젝트/7월 27일 중간 프로젝트/모델 파트/Model 코드/Sample_Product_원본.csv";

		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));

			String line;

			try {
				while((line = br.readLine()) != null) {

					String[] fields = line.split(",");

					String PNAME = fields[0];
					int PPRICE = Integer.parseInt(fields[1]);
					String PIMAGE = fields[2];
					int PCNT = Integer.parseInt(fields[3]);
					String PCATEGORY = fields[4];
					double PALCOHOL = Double.parseDouble(fields[5]);
					String PSWEET = (fields.length > 6) ? fields[6] : "";
					String PSOUR = (fields.length > 7) ? fields[7] : "";
					String PSPARKLE = (fields.length > 8) ? fields[8] : "";

					conn = JDBCUtil.connect();

					try {
						pstmt = conn.prepareStatement(SQL_INSERT);
						pstmt.setObject(1, PNAME); 
						pstmt.setObject(2, PPRICE); 
						pstmt.setObject(3, PIMAGE);
						pstmt.setObject(4, PCNT);
						pstmt.setObject(5, PCATEGORY);
						pstmt.setObject(6, PALCOHOL);
						pstmt.setObject(7, PSWEET);
						pstmt.setObject(8, PSOUR);
						pstmt.setObject(9, PSPARKLE);

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
				}	//	end while
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
		
	}	//	insert()

	public ArrayList<ProductVO> selectAll(ProductVO pVO) {

		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();

		conn = JDBCUtil.connect();

		try {
			pstmt = conn.prepareStatement(SQL_SELECTALL);

			//	setObject() 로 해도 되는듯.

			pstmt.setString(1, pVO.getpCategory());
			pstmt.setDouble(2, pVO.getpAlcohol());	
			pstmt.setString(3, pVO.getpSweet());
			pstmt.setString(4, pVO.getpSour());
			pstmt.setString(5, pVO.getpSparkle());

			rs = pstmt.executeQuery();

			System.out.println("log : ProductDAO_Test : " + pVO + "\n");

			while(rs.next()) {
				ProductVO pdata = new ProductVO();
				pdata.setpCategory(rs.getString("PCATEGORY"));
				pdata.setpAlcohol(rs.getDouble("PALCOHOL"));
				pdata.setpSweet(rs.getString("PSWEET"));
				pdata.setpSour(rs.getString("PSOUR"));
				pdata.setpSparkle(rs.getString("PSPARKLE"));
				pdatas.add(pdata);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
		finally {
			JDBCUtil.disconnect(rs, pstmt, conn);
		}

		for(ProductVO data : pdatas) {
			System.out.println("log : ProductDAO_Test : " + data + "\n");
		}

		/*
		for(int i = 0; i < pdatas.size(); i++) {
			System.out.println(pdatas.get(i) + "\n");
		}
		 */

		return pdatas;
	}

	public ProductVO selectOne(ProductVO pVO) {

		return null;
	}

	public boolean update(ProductVO pVO) {

		return true;
	}

	public boolean delete(ProductVO pVO) {

		return false;
	}

}