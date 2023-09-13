package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawling {
	public static ArrayList<BookVO> sample() {
//		public static void main(String[] args) {
			
		int category = 0;

		ArrayList<String> cdatas = new ArrayList<String>();
		ArrayList<BookVO> mdatas= new ArrayList<BookVO>();
		
		cdatas.add("55890");	// 건강/취미
		cdatas.add("170");	// 경제 경영 
		cdatas.add("2105");	// 고전
		cdatas.add("987");	// 과학
		cdatas.add("2551");	// 만화

		
		for(int i = 0; i < cdatas.size(); i++) {
			final String url = "https://www.aladin.co.kr/shop/common/wbest.aspx?BestType=BlogBest&BranchType=1&CID=" + cdatas.get(i);
			Connection conn = Jsoup.connect(url);
			Random rand = new Random();
			Document doc = null;

			try {
				doc = conn.get();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Elements element1 = doc.select("a > b");
			Elements element2 = doc.select("span > b > span");

			Iterator<Element> itr = element1.iterator();
			Iterator<Element> itr2 = element2.iterator();

			int PK=1;
			category++;
			
			for(int j = 0; j < 20; j++) {
				String title = itr.next().text();
				String price = itr2.next().text();
				
				price = price.replace("," , "");
				int price2 = Integer.parseInt(price);

				System.out.print(title + " - ");
				System.out.println(price + "원" + "	" + category);
				mdatas.add(new BookVO(PK++,title,price2,rand.nextInt(90)+10,category));							
			}
			
			
//			while(itr.hasNext() && itr2.hasNext()) {
//				String title = itr.next().text();
//				String price = itr2.next().text();
//				
//				price = price.replace("," , "");
//				int price2 = Integer.parseInt(price);
//
//				System.out.print(title + " - ");
//				System.out.println(price + "원" + "	" + category);
//				mdatas.add(new BookVO(PK++,title,price2,rand.nextInt(90)+10,category));							
//			}
			
		}
		System.out.println("크롤링1");
		System.out.println(mdatas.size());
		return mdatas;
	}
}


