package com.spring.biz.recommendation;

import java.util.Date;

public class RecommendationVO {

	private int rcNum;    // PK
	private String rcId;  // 추천인  --> 중복 추천 막기 위해서 
	private int bNum;     // 추천한 글(쓴 리뷰)
	
	// -----------------임시변수 뭐 넣을지 기봉이한테 물어보기
	
	
	public int getRcNum() {
		return rcNum;
	}
	public void setRcNum(int rcNum) {
		this.rcNum = rcNum;
	}
	public String getRcId() {
		return rcId;
	}
	public void setRcId(String rcId) {
		this.rcId = rcId;
	}
	public int getbNum() {
		return bNum;
	}
	public void setbNum(int bNum) {
		this.bNum = bNum;
	}
	
	@Override
	public String toString() {
		return "RecommendationVO [rcNum=" + rcNum + ", rcId=" + rcId + ", bNum=" + bNum + "]";
	}
	
	
	
}
