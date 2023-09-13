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

public class SampleData {

	public static ArrayList<ProductVO> sample() {
		
		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();

		String csvFile
		= "C:\\Users\\dhfg0\\Desktop\\이클립스 2023_06\\workspace\\SECERT_JOOJOO\\src\\main\\webapp\\csv\\Sample_Product.csv";

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
					String PSWEET = (fields.length > 6 && !(fields[6].isEmpty())) ? fields[6] : null;
	                String PSOUR = (fields.length > 7 && !(fields[7].isEmpty())) ? fields[7] : null;
	                String PSPARKLE = (fields.length > 8 && !(fields[8].isEmpty())) ? fields[8] : null;
					String PIMAGEDETAIL = fields[9];
					//	배열의 길이가 조건보다 크면, fields[인덱스] 가 대입되고, 크지 않으면, null.
					
					ProductVO pVO = new ProductVO();
					pVO.setpName(PNAME);
					pVO.setpPrice(PPRICE);
					pVO.setpImage(PIMAGE);
					pVO.setpCnt(PCNT);
					pVO.setpCategory(PCATEGORY);
					pVO.setpAlcohol(PALCOHOL);
					pVO.setpSweet(PSWEET);
					pVO.setpSour(PSOUR);
					pVO.setpSparkle(PSPARKLE);
					pVO.setpImagedetail(PIMAGEDETAIL);
					pdatas.add(pVO);
					
				}	//	end while
			}
			catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return pdatas;
		
	}	//	sample()
	

}	//	SampleData
