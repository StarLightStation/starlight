package model;

import java.sql.Date;

public class OrderVO {	//	주문

	private int oNum;	//	주문 번호. (PK)
	private String mID;	//	회원 아이디. (FK)
	private Date oDate;	//	주문 일자. 
	private int oPrice;	//	총 가격.
	private String oState;	//	주문 상태.
	private String oAddress;	//	배송지.
	
	// ------------------------  임시 변수.
	
	private String sk;
	private int rnum;	//	ROW_NUMBER()를 사용 하기 위한 임시 변수.

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

	@Override
	public String toString() {
		
		return "주문 번호 : " + this.oNum + " / 아이디 : " + this.mID + " / 주문 날짜 : " + this.oDate
				+ " / 총 가격 : " + this.oPrice + " / 주문 상태 : " + this.oState
				+ " / 배송지 주소 : " + this.oAddress;
	}

}	//	OrderVO
