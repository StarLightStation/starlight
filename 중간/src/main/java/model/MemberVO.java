package model;

public class MemberVO {	//	회원 정보
	
	private String mID;	//	회원 아이디. (PK)
	private String mPW;	//	비밀번호 .
	private String mName;	//	닉네임.
	private int subscription;	//	구독 여부.	=>	구독 X : 0 | 구독 O : 1
	private int isAdmin;	//	관리자 여부.	=>	일반 : 0 | 관리자 : 1	
	private String mPhone;	//	전화번호.
	private String signUpKind;	//	구독 종류.
	
	private String sk;	//	Search Keyword.
	private String tmpMpw;	//	비밀번호 변경시 필요한 임시 변수.
	private String tmpMname;	//	닉네임 변경시 필요한 임시 변수.
	private String tmpMphone;	//	전화번호 변경시 필요한 임시 변수.
	//	얘는, 아이디 찾기 할 때, 식별자 대용으로 사용 되기 때문에, 유효성 필수.
	
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getmPW() {
		return mPW;
	}
	public void setmPW(String mPW) {
		this.mPW = mPW;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getSubscription() {
		return subscription;
	}
	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getSignUpKind() {
		return signUpKind;
	}
	public void setSignUpKind(String signUpKind) {
		this.signUpKind = signUpKind;
	}
	
	public String getSk() {
		return sk;
	}
	public void setSk(String sk) {
		this.sk = sk;
	}
	public String getTmpMpw() {
		return tmpMpw;
	}
	public void setTmpMpw(String tmpMpw) {
		this.tmpMpw = tmpMpw;
	}
	public String getTmpMname() {
		return tmpMname;
	}
	public void setTmpMname(String tmpMname) {
		this.tmpMname = tmpMname;
	}
	public String getTmpMphone() {
		return tmpMphone;
	}
	public void setTmpMphone(String tmpMphone) {
		this.tmpMphone = tmpMphone;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}	//	MemberVO
