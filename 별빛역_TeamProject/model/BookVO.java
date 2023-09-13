package model;

public class BookVO {
	private int num; // PK
	private String title;
	private int price;
	private int cnt;
	private int category;
	
	private String sk; // 검색 Search Keyword
	private int Maxprice;
	private int Minprice;

	private int tmpCnt;
	private String changeTitle;
	private String tmpCategory;

	public BookVO(){
		this(0,"",0,0,0);
	}

	public BookVO(int num) {
		this(num, null, 0,0,0);
	}

	public BookVO(int num, String title, int price, int cnt, int category){
		this.num = num;
		this.title = title;
		this.price = price;
		this.cnt = cnt;
		this.category = category;
	}

	public String getTmpCategory() {
		return tmpCategory;
	}
	
	public void setTmpCategory(String tmpCategory) {
		this.tmpCategory = tmpCategory;
	}

	public String getSk() {
		return sk;
	}

	public void setSk(String sk) {
		this.sk = sk;
	}

	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}

	public int getMaxprice() {
		return Maxprice;
	}


	public void setMaxprice(int maxprice) {
		Maxprice = maxprice;
	}


	public int getMinprice() {
		return Minprice;
	}


	public void setMinprice(int minprice) {
		Minprice = minprice;
	}


	public String getChangeTitle() {
		return changeTitle;
	}


	public void setChangeTitle(String changeTitle) {
		this.changeTitle = changeTitle;
	}


	public int getTmpCnt() {
		return tmpCnt;
	}


	public void setTmpCnt(int tmpCnt) {
		this.tmpCnt = tmpCnt;
	}


	@Override
	public String toString() {
		return num + "번. " + title + " - " + price + "원" + "	[" + this.tmpCategory + "] \n";
	}

}
