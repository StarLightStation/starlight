package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SampleDataCrawling_XXX {
	
	public static ArrayList<ProductVO> sample() {
		
		final String url = "https://hwanii96.tistory.com/282";
		
		Connection connection = Jsoup.connect(url);

		Document document = null;
		
		try {
			document = connection.get();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements elements1 = document.select("tr > td:nth-child(1)");
		Elements elements2 = document.select("tr > td:nth-child(2)");
		Elements elements3 = document.select("tr > td:nth-child(3)");
		Elements elements4 = document.select("tr > td:nth-child(4)");
		Elements elements5 = document.select("tr > td:nth-child(5)");
		Elements elements6 = document.select("tr > td:nth-child(6)"); 
		Elements elements7 = document.select("tr > td:nth-child(7)");
		Elements elements8 = document.select("tr > td:nth-child(8)");
		Elements elements9 = document.select("tr > td:nth-child(9)");
		
		Iterator<Element> iterator1 = elements1.iterator();
		Iterator<Element> iterator2 = elements2.iterator();
		Iterator<Element> iterator3 = elements3.iterator();
		Iterator<Element> iterator4 = elements4.iterator();
		Iterator<Element> iterator5 = elements5.iterator();
		Iterator<Element> iterator6 = elements6.iterator();
		Iterator<Element> iterator7 = elements7.iterator();
		Iterator<Element> iterator8 = elements8.iterator();
		Iterator<Element> iterator9 = elements9.iterator();
		
		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();
		
		while(iterator1.hasNext() && iterator2.hasNext() && iterator3.hasNext()
				&& iterator4.hasNext() && iterator5.hasNext() && iterator6.hasNext()
				&& iterator7.hasNext() && iterator8.hasNext() && iterator9.hasNext()) {
			
		String pName = iterator1.next().text();
		String pPrice = iterator2.next().text();
		String pImage = iterator3.next().text();
		String pCnt = iterator4.next().text();
		String pCategory = iterator5.next().text();
		String pAlcohol = iterator6.next().text();
		String pSweet = iterator7.next().text();
		String pSour = iterator8.next().text();
		String pSparkle = iterator9.next().text();

		int price = Integer.parseInt(pPrice);
		int cnt = Integer.parseInt(pCnt);
		double alcohol = Double.parseDouble(pAlcohol);
		
		System.out.println("log : pName : " + pName);
		System.out.println("log : pPrice : " + pPrice);
		System.out.println("log : pImage : " + pImage);
		System.out.println("log : pCnt : " + pCnt);
		System.out.println("log : pCategory : " + pCategory);
		System.out.println("log : pAlcohol : " + pAlcohol);
		System.out.println("log : pSweet : " + pSweet);
		System.out.println("log : pSour : " + pSour);
		System.out.println("log : pSparkle : " + pSparkle);
		
		ProductVO pVO = new ProductVO();
		pVO.setpName(pName);
		pVO.setpPrice(price);	
		pVO.setpImage(pImage);
		pVO.setpCnt(cnt);
		pVO.setpCategory(pCategory);
		pVO.setpAlcohol(alcohol);	
		pVO.setpSweet(pSweet);
		pVO.setpSour(pSour);
		pVO.setpSparkle(pSparkle);
		pdatas.add(pVO);
		
		}
		return pdatas;
		
	}	//	sample()
	
}	//	SampleDataCrawling
