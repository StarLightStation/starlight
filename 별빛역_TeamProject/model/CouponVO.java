package model;

public class CouponVO {
	private int num;		// PK
	private String name;	// 쿠폰 종류
	private double rate;	// 할인율
	
	private double tmpprice;	// 
	

	public CouponVO(int num){ 
		this(num, null, 0);
	}
	
	public CouponVO(int num, String name, double rate){
		this.num = num;
		this.name = name;
		this.rate = rate;
	}
	
	// 테이블에 세팅 
	// 1  "추천인할인" /  0.9
	// 2  "실버등급할인"  / 0.9
	// 3  "골드등급할인"  / 0.85
	// 4  "다이아등급할인" / 0.80

	public double getTmpprice() {
		return tmpprice;
	}

	public void setTmpprice(double tmpprice) {
		this.tmpprice = tmpprice;
	}

	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
}
