package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SampleData_Test {

	/*
	static final String SQL_INSERT
	= "INSERT INTO PRODUCT_TEST (PNAME,PPRICE,PIMAGE,PCNT,PCATEGORY,PALCOHOL,PSWEET,PSOUR,PSPARKLE,PIMAGEDETAIL) VALUES (?,?,?,?,?,?,?,?,?,?)";
	*/
	
	static final String SQL_INSERT = "";
	
	static Connection conn;
	static PreparedStatement pstmt;
	static ResultSet rs;

	public static void main(String[] args) {

		String csvFile
		= "C:\\Users\\dhfg0\\Desktop\\이클립스 2023_06\\workspace\\SECERT_JOOJOO\\src\\main\\webapp\\csv\\Sample_Product.csv";

		try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));

			String line;

			try {
				while((line = br.readLine()) != null) {

					String[] fields = line.split(",");
					
					for(String data : fields) {
						System.out.println("log : SampleData_Test : main() : " + data);
					}

					String PNAME = fields[0];
					int PPRICE = Integer.parseInt(fields[1]);
					String PIMAGE = fields[2];
					int PCNT = Integer.parseInt(fields[3]);
					String PCATEGORY = fields[4];
					double PALCOHOL = Double.parseDouble(fields[5]);
					String PSWEET = (fields.length > 6 && !(fields[6].isEmpty())) ? fields[6] : null;
	                String PSOUR = (fields.length > 7 && !(fields[7].isEmpty())) ? fields[7] : null;
	                String PSPARKLE = (fields.length > 8 && !(fields[8].isEmpty())) ? fields[8] : null;
					String PIMAGEDETAIL = fields[9];
					
					//	(조건식) ? 참일 때 값 : 거짓일 때 값
					
					System.out.println("log : !(fields[6].isEmpty())) : " + !(fields[6].isEmpty()));
					System.out.println("log : !(fields[7].isEmpty())) : " + !(fields[7].isEmpty()));
					System.out.println("log : !(fields[8].isEmpty())) : " + !(fields[8].isEmpty()));
					

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
						pstmt.setObject(10, PIMAGEDETAIL);

						int result = pstmt.executeUpdate();

						if(result <= 0) {
							//	return false;
						}
					}
					catch (SQLException e) {
						e.printStackTrace();
						try {
							Thread.sleep(1000);
						}
						catch (InterruptedException e1) {
							e1.printStackTrace();
							//	return false;
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

	}	//	main()

}	//	SampleData_Insert
