package com.spring.biz.board;

import java.util.Date;

public class BoardVO {    //   리뷰 게시판

    private int bNum;    //   리뷰 번호. (PK)
    private String mID;    //   회원 아이디. (FK)
    private int pNum;    //   상품 번호. (FK)
    private String bContent;    //   리뷰 내용.
    private double bStar;    //   회원 별점.
    private Date bDate;    //   리뷰 작성 날짜.
    private String mName;    //   회원 닉네임. (FK)

    // ------------------------  임시 변수.

    private String sk;    //   Search Keyword.
    private int rnum;    // ROW_NUMBER()를 사용 하기 위한 임시 변수.
    private String pName;    // 상품 이름 (조인)
    private String pImage;    //    상품 이미지 (조인)
    private boolean check;	// 리뷰를 내가 신고 했는지 안 했는지를 체크하는 임시 변수
    
    public int getbNum() {
        return bNum;
    }

    public void setbNum(int bNum) {
        this.bNum = bNum;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
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

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
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
    
   public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

@Override
   public String toString() {
      return "BoardVO [bNum=" + bNum + ", mID=" + mID + ", pNum=" + pNum + ", bContent=" + bContent + ", bStar="
            + bStar + ", bDate=" + bDate + ", mName=" + mName + ", sk=" + sk + ", rnum=" + rnum + ", pName=" + pName
            + ", pImage=" + pImage + "]";
   }
    
}    //   BoardVO