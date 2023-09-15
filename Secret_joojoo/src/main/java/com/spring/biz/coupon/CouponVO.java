package com.spring.biz.coupon;

public class CouponVO {
	private int cNum;		// 쿠폰 pk
	private String cName;	// 쿠폰 이름
	private double cDiscount;	// 쿠폰 할인율
	//---------------------------  
	
	private int tmpprice;  // 임시 변수 
	
	
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public double getcDiscount() {
		return cDiscount;
	}
	public void setcDiscount(double cDiscount) {
		this.cDiscount = cDiscount;
	}

	public int getTmpprice() {
		return tmpprice;
	}
	public void setTmpprice(int tmpprice) {
		this.tmpprice = tmpprice;
	}
	
	@Override
	public String toString() {
		return "CouponVO [cNum=" + cNum + ", cName=" + cName + ", cDiscount=" + cDiscount + "]";
	}
}
