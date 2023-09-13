package model;

public class SubscriptionVO {	//	 구독

	private int subNum;	//	구독 번호. (PK)
	private String subName;	//	구독 종류 이름.
	private int subPrice;	//	구독 가격.

	public int getSubNum() {
		return subNum;
	}
	public void setSubNum(int subNum) {
		this.subNum = subNum;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public int getSubPrice() {
		return subPrice;
	}
	public void setSubPrice(int subPrice) {
		this.subPrice = subPrice;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}	//	SubscriptionVO
