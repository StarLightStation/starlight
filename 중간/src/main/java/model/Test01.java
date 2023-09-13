package model;

public class Test01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String pPrice = "10000";
		String pCnt = "100";
		String pAlcohol = "20.5";
		
		int price = Integer.parseInt(pPrice);
		int cnt = Integer.parseInt(pCnt);
		double alcohol = Double.parseDouble(pAlcohol);
		
		System.out.println("가격 : " + price);
		System.out.println("재고 : " + cnt);
		System.out.println("도수 : " + alcohol);

	}

}
