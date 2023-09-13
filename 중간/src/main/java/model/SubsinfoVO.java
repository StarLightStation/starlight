package model;

import java.sql.Date;

public class SubsinfoVO {	//	구독 정보
	
	private int sInfoNum;	//	구독 정보 번호. (PK)
	private String mID;	//	회원 아이디. (FK)
	private int subNum;	//	구독 번호. (FK)
	private Date sInfoPeriod;	//	구독 유효 기간.
	
	//--------------------------  임시 변수 
	
	private String subName;
	private int subPrice;
	
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
	public int getsInfoNum() {
		return sInfoNum;
	}
	public void setsInfoNum(int sInfoNum) {
		this.sInfoNum = sInfoNum;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public int getSubNum() {
		return subNum;
	}
	public void setSubNum(int subNum) {
		this.subNum = subNum;
	}
	public Date getsInfoPeriod() {
		return sInfoPeriod;
	}
	public void setsInfoPeriod(Date sInfoPeriod) {
		this.sInfoPeriod = sInfoPeriod;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}	//	SubsinfoVO
