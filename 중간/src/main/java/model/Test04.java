package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test04 {

	public static void main(String[] args) {

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
					
					System.out.println("PNAME : " + PNAME);
					System.out.println("PPRICE : " + PPRICE);
					System.out.println("PIMAGE : " + PIMAGE);
					System.out.println("PCNT : " + PCNT);
					System.out.println("PCATEGORY : " + PCATEGORY);
					System.out.println("PALCOHOL : " + PALCOHOL);
					System.out.println("PSWEET : " + PSWEET);
					System.out.println("PSOUR : " + PSOUR);
					System.out.println("PSPARKLE : " + PSPARKLE);
					
					/*
					if(fields.length > 6 ) {
						String PSWEET = fields[6];
						System.out.println("PSWEET : " + PSWEET);
					}
					if(fields.length > 7 ) {
						String PSOUR = fields[7];
						System.out.println("PSOUR : " + PSOUR);
					}
					if(fields.length > 8) {
						String PSPARKLE = fields[8];
						System.out.println("PSPARKLE : " + PSPARKLE);
					}
					*/
					
					System.out.println("========== 줄바꿈 ==========");

				}	//	end while
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
