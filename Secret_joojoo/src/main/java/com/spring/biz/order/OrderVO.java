package com.spring.biz.order;

import java.sql.Date;

public class OrderVO {	//	주문
	// = "SELECT EXTRACT(YEAR FROM ODATE) AS YEAR, TO_NUMBER(TO_CHAR, 'Q')) AS QUARTER, SUM(OPRICE) AS TOTAL_PRICE"
	private int oNum;	//	주문 번호. (PK)
	private String mID;	//	회원 아이디. (FK)
	private Date oDate;	//	주문 일자. 
	private int oPrice;	//	총 가격.
	private String oState;	//	주문 상태.
	private String oAddress;	//	배송지.
	
	// ------------------------  임시 변수.
	
	private String sk;
	private int rnum;	//	ROW_NUMBER()를 사용 하기 위한 임시 변수.
	private int year;		// 통계때 쓰일 연도
	private int quarter;	// 통계때 쓰일 분기
	private int total_price;	// 통계때 쓰일 상품 + 구독 매출
	private int product_price;	// 통계때 쓰일 상품 매출
	private int subs_price;		// 통계때 쓰일 구독 매출
	private String orderName; 	// api에 쓰일 상품명
	
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public int getoNum() {
		return oNum;
	}
	public void setoNum(int oNum) {
		this.oNum = oNum;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public Date getoDate() {
		return oDate;
	}
	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}
	public int getoPrice() {
		return oPrice;
	}
	public void setoPrice(int oPrice) {
		this.oPrice = oPrice;
	}
	public String getoState() {
		return oState;
	}
	public void setoState(String oState) {
		this.oState = oState;
	}
	public String getoAddress() {
		return oAddress;
	}
	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}

	public String getSk() {
		return sk;
	}
	public void setSk(String sk) {
		this.sk = sk;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public int getSubs_price() {
		return subs_price;
	}
	public void setSubs_price(int subs_price) {
		this.subs_price = subs_price;
	}

	@Override
	public String toString() {
		return "OrderVO [oNum=" + oNum + ", mID=" + mID + ", oDate=" + oDate + ", oPrice=" + oPrice + ", oState="
				+ oState + ", oAddress=" + oAddress + ", sk=" + sk + ", rnum=" + rnum + ", year=" + year + ", quarter="
				+ quarter + ", total_price=" + total_price + ", product_price=" + product_price + ", subs_price="
				+ subs_price + "]";
	}
	
//	@Override
//	public String toString() {
//		
//		return "주문 번호 : " + this.oNum + " / 아이디 : " + this.mID + " / 주문 날짜 : " + this.oDate
//				+ " / 총 가격 : " + this.oPrice + " / 주문 상태 : " + this.oState
//				+ " / 배송지 주소 : " + this.oAddress;
//	}

}	//	OrderVO
