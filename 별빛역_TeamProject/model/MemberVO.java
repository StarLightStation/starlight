package model;

public class MemberVO {
	private String mid;		// 아이디 PK
	private String mpw;		// 비밀번호
	private String name;	// 닉네임
	private int total;		// 총 쓴 가격
	private String mgrade;	// 멤버 등급
	private int gradeUp; 

	private String tmpCondition; // 회원가입할때면 tmpCondition에 "중복검사"  // 한명 정보 꺼낼때 "회원정보"
	private String tmpString;	// 비밀번호/닉네임 변경할때
	private int tmpPrice;	// 사용한 가격
	

	public MemberVO() {
		this(null,null,null,0,null,0);
	}
	
	public MemberVO(String mid) {
		this(mid,null,null,0,null,0);
	}
	
	public MemberVO(String mid, String mpw) {
		this(mid, mpw, null, 0,null,0);
	}
	
	public MemberVO(String mid, String mpw, String name) {
		this(mid, mpw, name, 0, "BRONZE", 0 );
	}
	
	public MemberVO(String mid,String mpw,String name,int total, String mgrade, int gradeUp) {
		this.mid = mid;
		this.mpw = mpw;
		this.name = name;
		this.total = total;
		this.mgrade = mgrade; // BRONZE SILVER GOLD DIAMOND 
		this.gradeUp = gradeUp;
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getMgrade() {
		return mgrade;
	}

	public void setMgrade(String mgrade) {
		this.mgrade = mgrade;
	}
	
	public int getGradeUp() {
		return gradeUp;
	}

	public void setGradeUp(int gradeUp) {
		this.gradeUp = gradeUp;
	}

	public String getTmpCondition() {
		return tmpCondition;
	}
	
	public void setTmpCondition(String tmpCondition) {
		this.tmpCondition = tmpCondition;
	}
	
	public String getTmpString() {
		return tmpString;
	}

	public void setTmpString(String tmpString) {
		this.tmpString = tmpString;
	}

	public int getTmpPrice() {
		return tmpPrice;
	}

	public void setTmpPrice(int tmpPrice) {
		this.tmpPrice = tmpPrice;
	}

	@Override
	public String toString() {
		return this.mid + " 회원님 / 비번 [" + this.mpw + "] / 현재 등급 : " + this.mgrade + " / 구매한 총 금액 : " + this.total + "\n";
	}

}
