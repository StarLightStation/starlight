package model;

public class OrderdetailVO {	//	주문 상세
	
	private int odNum;	//	주문 상세 번호 (PK)
	private int pNum;	//	상품 번호. (FK)
	private int oNum;	//	주문 번호. (FK)
	private int odCnt;	//	구매한 상품 개수.
	private String isWrite;	//	리뷰 작성 전 / 작성 완료
	
	// ------------------------  임시 변수.
	
	private String sk;
	
	//	-----------------------	조인을 위한 임시 변수.
	
	private int rnum;	//	ROW_NUMBER()
	private String mID;
	private String pName;
	private int pPrice;
	
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
	
	@Override
	public String toString() {
		return "OrderdetailVO [odNum=" + odNum + ", pNum=" + pNum + ", oNum=" + oNum + ", odCnt=" + odCnt + ", isWrite="
				+ isWrite + ", sk=" + sk + ", rnum=" + rnum + ", mID=" + mID + ", pName=" + pName + ", pPrice=" + pPrice
				+ "]";
	}
	
}	//	OrderdetailVO
