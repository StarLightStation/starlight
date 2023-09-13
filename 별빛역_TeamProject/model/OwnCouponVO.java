package model;

import java.util.Date;

public class OwnCouponVO {
	private int num; 	// pk
	private String midfk;	// 멤버 pk
	private int cnumfk; // 쿠폰 pk
	private Date fDate; // 쿠폰 유효기간
	private String tmpCondition;
	private int tmpGrade;	// 등급 비교를 위해
	private String tmpName;		// 출력문을 위해
	private double tmpRate;
	private int tmpRowNum;
	
	
	public OwnCouponVO(String midfk) {
		this(0, midfk , 0, null, null, 0,0);
	}

	public OwnCouponVO(String midfk, int cnumfk) {
		this(0, midfk , cnumfk, null, null, 0,0);
	}
	
	public OwnCouponVO(Date fdate) {
		this(0,null,0,fdate,null,0,0);
	}
	
	public OwnCouponVO(int num, String midfk, int cnumfk, Date fdate, String tmpName, double tmpRate, int tmpRowNum) {
		this.num = num;
		this.midfk = midfk;
		this.cnumfk = cnumfk;
		this.fDate = fdate;
		this.tmpName = tmpName;
		this.tmpRate = tmpRate;
		this.tmpRowNum = tmpRowNum;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public String getMidfk() {
		return midfk;
	}
	public void setMidfk(String midfk) {
		this.midfk = midfk;
	}
	public int getCnumfk() {
		return cnumfk;
	}
	public void setCnumfk(int cnumfk) {
		this.cnumfk = cnumfk;
	}
	public Date getfDate() {
		return fDate;
	}
	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	public String getTmpCondition() {
		return tmpCondition;
	}

	public void setTmpCondition(String tmpCondition) {
		this.tmpCondition = tmpCondition;
	}
	public String getTmpName() {
		return tmpName;
	}
	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}
	public double getTmpRate() {
		return tmpRate;
	}
	public void setTmpRate(double tmpRate) {
		this.tmpRate = tmpRate;
	}

	public int getTmpRowNum() {
		return tmpRowNum;
	}

	public void setTmpRowNum(int tmpRowNum) {
		this.tmpRowNum = tmpRowNum;
	}

	public int getTmpGrade() {
		return tmpGrade;
	}

	public void setTmpGrade(int tmpGrade) {
		this.tmpGrade = tmpGrade;
	}

	@Override
	public String toString() {
		return tmpRowNum + "번 쿠폰 - " + tmpName  + "   할인율 [ " + tmpRate + " ]";
	}
	
	
	
}
