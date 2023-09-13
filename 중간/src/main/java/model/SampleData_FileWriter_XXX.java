package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SampleData_FileWriter_XXX {

	public static void main(String[] args) {
		
		final String filePath
		= "C:/Users/dhfg0/Desktop/국비 수업 복습/최종팀 프로젝트/7월 27일 중간 프로젝트/모델 파트/Model 코드/Sample_Product.csv";
		
		File file = null;
		BufferedWriter bw = null;
		String NEWLINE = System.lineSeparator();	//	줄바꿈 (\n) 메서드.
		
		try {
			file = new File(filePath);
			bw = new BufferedWriter(new FileWriter(file));
			
			bw.write("");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		

	}	//	main()

}	//	SampleData
