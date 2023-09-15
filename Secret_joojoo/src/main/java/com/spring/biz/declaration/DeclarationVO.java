package com.spring.biz.declaration;

import java.util.Date;
//SELECT B.MID, B.BCONTENT, B.BSTAR, B.BDATE, B.BNAME, D.DNUM, D.BNUM, D.DMID, D.DDATE, P.PNAME, P.PIMAGE"
public class DeclarationVO {
	private int dNum;		// pk
	private int bNum;		// board fk
	private String mID;		// 신고한 회원
	private Date dDate;		// 신고한 날짜 
	
	//-----------------임시변수
	private int rnum;
	private String dmID;
	private String bContent;
	private double bStar;
	private Date bDate;
	private String bName;
	private int pNum;
	private String pName;
	private String pImage;
	private String sk;
	
	public int getdNum() {
		return dNum;
	}
	public void setdNum(int dNum) {
		this.dNum = dNum;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	public String getDmID() {
		return dmID;
	}
	public void setDmID(String dmID) {
		this.dmID = dmID;
	}
	public Date getdDate() {
		return dDate;
	}
	public void setdDate(Date dDate) {
		this.dDate = dDate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public double getbStar() {
		return bStar;
	}
	public void setbStar(double bStar) {
		this.bStar = bStar;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
	public String getbName() {
		return bName;
	}
	public void setbName(String bName) {
		this.bName = bName;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public String getSk() {
		return sk;
	}
	public void setSk(String sk) {
		this.sk = sk;
	}
	@Override
	public String toString() {
		return "DeclarationVO [dNum=" + dNum + ", bNum=" + bNum + ", mID=" + mID + ", dDate=" + dDate + ", rnum=" + rnum
				+ ", dmID=" + dmID + ", bContent=" + bContent + ", bStar=" + bStar + ", bDate=" + bDate + ", bName="
				+ bName + ", pNum=" + pNum + ", pName=" + pName + ", pImage=" + pImage + ", sk=" + sk + "]";
	}

}
