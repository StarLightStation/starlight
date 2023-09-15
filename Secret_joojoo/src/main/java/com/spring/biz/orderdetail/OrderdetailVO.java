package com.spring.biz.orderdetail;

import java.util.Date;

public class OrderdetailVO {    //   주문 상세

    private int odNum;    //   주문 상세 번호 (PK)
    private int pNum;    //   상품 번호. (FK)
    private int oNum;    //   주문 번호. (FK)
    private int odCnt;    //   구매한 상품 개수.
    private String isWrite;    //   리뷰 작성 전 / 작성 완료

    // ------------------------  임시 변수.

    private String sk;    //   Search Keyword.

    //   -----------------------   조인을 위한 임시 변수.

    private int rnum;    // ROW_NUMBER()를 사용 하기 위한 임시 변수.
    private String mID;    //   회원 번호. (FK) (조인)
    private Date oDate;    //   주문날짜 (조인)
    private String pName;    // 상품 이름 (조인)
    private int pPrice;    //   상품 가격 (조인)
    private String pImage;    //   상품 이미지 (조인)
    private int cnt;		// 상품 누적 판매량 (집계)
    private int total;		// 상품 누적 판매 총액 (집계)
    private int cnt_rank;	// 상품 누적 판매 순위 (분석)
    private int total_rank;	// 상품 누적 판매 총액 순위 (분석)
    
    public int getOdNum() {
        return odNum;
    }
    public void setOdNum(int odNum) {
        this.odNum = odNum;
    }
    public int getpNum() {
        return pNum;
    }
    public void setpNum(int pNum) {
        this.pNum = pNum;
    }
    public int getoNum() {
        return oNum;
    }
    public void setoNum(int oNum) {
        this.oNum = oNum;
    }
    public int getOdCnt() {
        return odCnt;
    }
    public void setOdCnt(int odCnt) {
        this.odCnt = odCnt;
    }
    public String getIsWrite() {
        return isWrite;
    }
    public void setIsWrite(String isWrite) {
        this.isWrite = isWrite;
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
    public String getmID() {
        return mID;
    }
    public void setmID(String mID) {
        this.mID = mID;
    }
    public String getpName() {
        return pName;
    }
    public void setpName(String pName) {
        this.pName = pName;
    }
    public int getpPrice() {
        return pPrice;
    }
    public void setpPrice(int pPrice) {
        this.pPrice = pPrice;
    }
    public String getpImage() {
        return pImage;
    }
    public void setpImage(String pImage) {
        this.pImage = pImage;
    }
    public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCnt_rank() {
		return cnt_rank;
	}
	public void setCnt_rank(int cnt_rank) {
		this.cnt_rank = cnt_rank;
	}
	public int getTotal_rank() {
		return total_rank;
	}
	public void setTotal_rank(int total_rank) {
		this.total_rank = total_rank;
	}

	public Date getoDate() {
		return oDate;
	}
	public void setoDate(Date oDate) {
		this.oDate = oDate;
	}
	@Override
	public String toString() {
		return "OrderdetailVO [odNum=" + odNum + ", pNum=" + pNum + ", oNum=" + oNum + ", odCnt=" + odCnt + ", isWrite="
				+ isWrite + ", sk=" + sk + ", rnum=" + rnum + ", mID=" + mID + ", pName=" + pName + ", pPrice=" + pPrice
				+ ", pImage=" + pImage + ", cnt=" + cnt + ", total=" + total + ", cnt_rank=" + cnt_rank
				+ ", total_rank=" + total_rank + "]";
	}


}    //   OrderdetailVO