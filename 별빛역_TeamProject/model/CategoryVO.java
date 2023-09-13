package model;

public class CategoryVO {
	private int num;
	private String category;
	
	public CategoryVO() {
		this(0,null);
	}
	
	public CategoryVO(int num, String category) {
		this.num = num;
		this.category = category;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return this.num +"번. " +this.category;
	}
	
}
