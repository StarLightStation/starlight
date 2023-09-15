package com.spring.biz.usecoupon;

import java.util.Date;

public class UsecouponVO {
	private int ucNum;		// pk
	private String mID;		// member fk
	private int cNum;		// coupon fk
	private Date ucFdate;	// 쿠폰 만료일
	private int ucAble;		// 쿠폰 사용 가능 여부 (사용 가능시 1, 쿠폰 만료일 or 사용했다면 0)
	
	// 임시변수
	private String sk;
	private int rnum;		// ROW_NUMBER 를 사용하기 위한 임시변수
	private String cName;	// 쿠폰이름(JOIN)
	private double cDiscount;	// 쿠폰할인율(JOIN)
	
	public int getUcNum() {
		return ucNum;
	}
	public void setUcNum(int ucNum) {
		this.ucNum = ucNum;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public Date getUcFdate() {
		return ucFdate;
	}
	public void setUcFdate(Date ucFdate) {
		this.ucFdate = ucFdate;
	}
	public int getUcAble() {
		return ucAble;
	}
	public void setUcAble(int ucAble) {
		this.ucAble = ucAble;
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
	@Override
	public String toString() {
		return "UsecouponVO [ucNum=" + ucNum + ", mID=" + mID + ", cNum=" + cNum + ", ucFdate=" + ucFdate + ", ucAble="
				+ ucAble + ", sk=" + sk + ", rnum=" + rnum + ", cName=" + cName + ", cDiscount=" + cDiscount + "]";
	}
}
