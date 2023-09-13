package model;

public class CartVO {
	private int num;
	private String midfk; // member pk
	private int bnumfk; // book pk
	private int cnt;
	
	private String sk; //SearchKeyword
	private String tmpTitle;
	private int tmpPrice;
	private int tmpRowNum;

	public CartVO(String midfk) {
		this(0, midfk, 0, 0);
	}

	public CartVO(String midfk, int bnumfk) {
		this(0,midfk, bnumfk, 0);
	}
	public CartVO(String midfk, int bnumfk,int cnt) {
		this(0,midfk, bnumfk, cnt);
	}
	
	public CartVO(int num, String midfk, int bnumfk) {
		this(num, midfk, bnumfk, 0);
	}

	public CartVO(int num, String midfk, int bnumfk, int cnt) {
		this.num = num;
		this.midfk = midfk;
		this.bnumfk = bnumfk;
		this.cnt = cnt;
		
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

	public int getBnumfk() {
		return bnumfk;
	}
	public void setBnumfk(int bnumfk) {
		this.bnumfk = bnumfk;
	}

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}

	public String getTmpTitle() {
		return tmpTitle;
	}

	public void setTmpTitle(String tmpTitle) {
		this.tmpTitle = tmpTitle;
	}

	public int getTmpPrice() {
		return tmpPrice;
	}

	public void setTmpPrice(int tmpPrice) {
		this.tmpPrice = tmpPrice;
	}

	public int getTmpRowNum() {
		return tmpRowNum;
	}

	public void setTmpRowNum(int tmpRowNum) {
		this.tmpRowNum = tmpRowNum;
	}

	@Override
	public String toString() {
		return tmpRowNum + "번. " + tmpTitle + " - " + tmpPrice + "원   선택한 개수 [ " + cnt + " ]" ;
	}
	
}
