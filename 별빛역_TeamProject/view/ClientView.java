package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.BookVO;
import model.CartVO;
import model.CategoryVO;
import model.MemberVO;
import model.OwnCouponVO;

public class ClientView {

	//	[멤버변수]
	private static final int maxPrintMenuStart = 3;
	private static final int maxClientMenu = 6;
	private static final int maxPrintBookMenuStart = 4;
	private static final int maxMyPage = 7;
	private static final int maxCartMenu = 5;
	private static final int signDelete = 1;

	private static Scanner sc = new Scanner(System.in);

	//	[함수]
	//	tryCatch	--------------------------------------------------------
	public int tryCatch() {

		int retry = 0;
		while(true) {
			try {
				if(retry != 0) {
					System.out.print("재");
				}
				System.out.print("입력 >> ");
				int action = sc.nextInt();

				if(action < 0) {
					System.out.println("0 이상의 숫자만 입력 가능 ! \n");
					retry++;
					continue;
				}
				return action;
			}
			catch(Exception e) {
				sc.nextLine();
				System.out.println("숫자만 입력 가능 ! \n");
				retry++;
			}
		}
	}
	//	실행 번호 입력	--------------------------------------------------------
	public int getNum(int num) {	

		int retry = 0;
		while(true) {

			if(retry != 0) {			//	retry가 0이 아니면
				System.out.print("재");
			}
			else {
				System.out.print("번호 ");
			}
			int action = tryCatch();	//	try-catch 모듈화.

			if(action > num) {			//	최대 메뉴 개수를 뜻하는 num.
				System.out.println("\n번호가 일치하지 않습니다.");
				retry++;
				continue;
			}
			return action;
		}
	}

	//	프로그램 메뉴	--------------------------------------------------------
	public int printMenuStart() {	//	메인 페이지.
		System.out.println("===== 알 라 딘 =====");
		System.out.println("1. 로그인.");
		System.out.println("2. 회원가입.");
		System.out.println("3. 프로그램 종료.");
		System.out.println("================== \n");
		return maxPrintMenuStart;
	}
	//	1. 로그인.	
	public void signIn() {	//	로그인 시작.
		System.out.println("로그인을 시작 합니다. \n");
	}
	public String signInId() {	//	로그인 아이디 입력.
		System.out.print("아이디 입력 >> ");
		String mid = sc.next();
		return mid;
	}
	public String signInPw() {	//	로그인 비밀번호 입력.
		System.out.print("비밀번호 입력 >> ");
		String mpw = sc.next();
		return mpw;
	}
	public void signInTrue() {	//	로그인 성공시.
		System.out.println("로그인 성공 ! \n");
	}
	public void signInFalse() {	//	로그인 실패시.
		System.out.println("아이디 또는 비밀번호를 잘못 입력했습니다 ..");
		System.out.println("입력하신 내용을 다시 확인해주세요. \n");
	}
	//	2.	회원가입.
	public void signUp() {	//	회원가입 시작.
		System.out.println("\n회원가입을 시작 합니다. \n");
	}
	public String signUpId() {	//	회원가입 아이디 입력.
		System.out.print("아이디 입력 >> ");
		String mid = sc.next();
		return mid;
	}
	public void signUpIdTrue() {
		System.out.println("중복되는 아이디가 없습니다. \n");
	}
	public void signUpIdFalse() {
		System.out.println("아이디 중복 ..");
		System.out.println("아이디를 다시 입력 해주세요. \n");
	}
	public String signUpPw() {	//	회원가입 비밀번호 입력.
		while(true) {
			System.out.print("비밀번호 입력 >> ");
			String mpw = sc.next();
			System.out.print("비밀번호 재 입력 >> ");
			String checkMpw = sc.next();
			if(!(mpw.equals(checkMpw))) {
				System.out.println("비밀번호가 일치하지 않습니다. \n");
				continue;
			}
			return mpw;
		}
	}
	public String signUpName() {
		System.out.print("이름 (닉네임) 입력 >> ");
		String name = sc.next();
		return name;
	}
	public String signUpRecommend(String mid) {	//	회원가입 추천인 입력.
		System.out.println("추천인을 등록 하시겠습니까 ?");
		System.out.print("예 : 1번 ");
		System.out.println("/ 아니오 : 2번");
		int action = tryCatch();
		if(action == 1) {
			return signUpSelfCheck(mid);
		}
		else if(action == 2) {
			System.out.println("추천인을 등록 하지 않습니다. \n");
		}
		return "추천인등록안함";
	}
	public String signUpSelfCheck(String mid) {	//	자기 자신 추천인 등록 불가능.
		while(true) {
			System.out.print("추천인 입력 >> ");
			String recommender = sc.next();
			if(recommender.equals(mid)) {
				System.out.println("자기 자신은 추천인으로 사용할 수 없습니다 .. \n");
			}
			else if(!(recommender.equals(mid))) {
				return recommender;
			}
		}
	}
	public void signUpRecommendTrue() {
		System.out.println("추천인 등록 성공 ! \n");
	}
	public void signUpRecommendFalse() {
		System.out.println("일치하는 추천인이 없습니다 .. \n");
	}
	public void signUpTrue() {	//	회원가입 성공시.
		System.out.println("회원가입 성공 ! \n");
	}
	public void signUpFalse() {	//	회원가입 실패시.
		System.out.println("\n회원가입 실패 ..");
		System.out.println("이미 존재하는 아이디 입니다 .. \n");
	}
	//	3.	프로그램 종료.
	public void printMenuEnd() {
		System.out.println("\n===== 프로그램 종료 =====");
	}
	//	사용자 페이지	--------------------------------------------------------
	public int clientMenu() {	//	사용자 페이지.
		System.out.println("\n===== 사용자 페이지 =====");
		System.out.println("1. 책 목록 출력.");
		System.out.println("2. 책 검색.");
		System.out.println("3. 책 구매.");
		System.out.println("4. 책 추천.");
		System.out.println("5. 마이페이지.");
		System.out.println("6. 로그아웃.");
		System.out.println("====================== \n");
		return maxClientMenu;
	}

	//	책 목록 출력	--------------------------------------------------------
	public void printBookList(ArrayList<BookVO> bdatas) {
		System.out.println("===== 책 목록 출력 ===== \n");
		if(bdatas.isEmpty()) {
			System.out.println("출력할 리스트가 없습니다.");
			return;
		}
		for(BookVO bdata : bdatas) {
			System.out.println(bdata.getNum() + "번. " + bdata.getTitle() + " - " + bdata.getPrice() + "원 재고 : " + bdata.getCnt() + "권 [" + bdata.getTmpCategory() + "]");
		}
	}
	//	책 검색	--------------------------------------------------------
	public int printBookMenuStart() {	//	 책 검색 페이지.
		System.out.println("===== 검색 메뉴 =====");
		System.out.println("1. 최고가 검색.");
		System.out.println("2. 최저가 검색.");
		System.out.println("3. 이름 검색.");
		System.out.println("4. 필터 검색.");
		System.out.println("0. 뒤로 가기.");
		System.out.println("===================");
		return maxPrintBookMenuStart;
	}
	public void printBookMenuEnd() {
		System.out.println("===== 검색 종료 ===== \n");
	}
	public BookVO bookSearch(int bookSearchNum) {	//	책 검색.
		BookVO bVO = new BookVO();
		String sk = "";	//	searchKeyword.
		int num = bookSearchNum;	//	1, 2, 3, 4 중에 하나를 입력 받은 상황.

		if(num == 1) {	//	최고가 검색.
			System.out.println("최고가 검색을 시작 합니다.");
			sk += "최고가";
		}
		else if(num == 2) {	//	최저가 검색.
			System.out.println("최저가 검색을 시작 합니다.");
			sk += "최저가";
		}
		else if(num == 3) {	//	이름 검색.
			System.out.println("이름 검색을 시작 합니다.");
			sk += "이름검색";
			System.out.print("검색할 이름 입력 >> ");
			String title = sc.next();
			bVO.setChangeTitle(title);	//	검색할 이름의 값을 BookVO의 멤버변수 changeTitle의 값으로 set.
		}
		else if(num == 4) {	//	필터 검색.
			System.out.println("필터 검색을 시작 합니다.");
			sk += "필터검색";
			System.out.print("최저가 ");
			int minPrice = tryCatch();
			System.out.print("최고가 ");
			int maxPrice = tryCatch();

			if(minPrice > maxPrice) {	//	최저값이 최고값보다 클 경우, 그 값을 교환 알고리즘을 사용해서 바꾸는 로직.
				System.out.println("최저 가격 " + maxPrice + "\n 최고가격 " + minPrice + "로 검색 합니다. \n" );
				int tmp = minPrice;
				minPrice = maxPrice;
				maxPrice = tmp;
			}
			bVO.setMinprice(minPrice);	//	최저값을 멤버변수 minPrice의 값으로 set.
			bVO.setMaxprice(maxPrice);	//	최고값을 멤버변수 maxPrice의 값으로 set.
		}
		bVO.setSk(sk);	//	키워드로 사용하려고 만든 BookVO의 멤버변수 sk에 String 타입의 키워드를 담은 sk 변수를 대입 해서 set.
		return bVO;		//	각 if문 마다 존재하는 키워드용 멤버변수의 값이 set 되었고, 이 정보를 가진 객체 bVO를 return.
	}
	//	책 구매	--------------------------------------------------------
	public void bookBuy() {	//	책 구매 시작 알림.
		System.out.println("책 구매를 시작 합니다.");
	}
	public void printbookBuyList(ArrayList<BookVO> bdatas) {
		if(bdatas.isEmpty()) {
			System.out.println("출력할 리스트가 없습니다.");
			return;
		}
		for(BookVO bdata : bdatas) {
			System.out.println(bdata);
		}
	}
	public int bookBuyNum() {
		System.out.print("구매할 책 번호 ");
		int num = tryCatch();
		return num;
	}
	public int bookBuyCnt() {
		System.out.print("구매할 책 개수 ");
		int cnt = tryCatch();
		return cnt;
	}
	public void bookBuyFalseNum() {
		System.out.println("구매할 책의 번호가 일치하지 않습니다.. \n");
	}
	public void bookBuyFalseCnt() {
		System.out.println("구매할 책의 개수가 부족 합니다.. \n");
	}
	public void bookBuyTrue() {	//	책 구매 성공시.
		System.out.println("책 구매 성공 ! \n");
	}
	public int bookBuyEnd() {	//	책을 바로 구매할건지, 장바구니에 담을건지.
		System.out.println("바로 구매 : 1번");
		System.out.println("장바구니 담기 : 2번");
		int action = tryCatch();
		return action;
	}
	public int bookBuyCoupon() {	//	쿠폰 사용 여부.
		System.out.println("쿠폰 사용 : 1번");
		System.out.println("쿠폰 미사용 : 2번");
		int action = tryCatch();
		return action;
	}
	public void bookBuyCouponFalse() {
		System.out.println("쿠폰이 없습니다 .. \n");
	}
	public void printBookBuyCouponList(ArrayList<OwnCouponVO> cdatas) {	//	쿠폰 목록 출력.
		System.out.println("===== 쿠폰 목록 출력 ===== \n");
		if(cdatas.isEmpty()) {
			System.out.println("출력할 리스트가 없습니다.");
			return;
		}
		for(OwnCouponVO cdata : cdatas) {
			System.out.println(cdata);
		}
	}
	public void printBookData(BookVO bdata) {
		if(bdata == null) {	//	굳이 없어도 되는듯.
			System.out.println("출력할 책이 없습니다.");
			return;
		}
		System.out.println(bdata.getNum() + "번. " + bdata.getTitle() + " - "+bdata.getPrice() + "원 구매갯수 : " + bdata.getCnt() + "권 [" + bdata.getTmpCategory() + "]");
	}
	//	책 추천	--------------------------------------------------------
	public void bookRecommend() {
		System.out.println("책 추천을 시작 합니다.");
	}
	public void printCategoryList(ArrayList<CategoryVO> cdatas) {	//	카테고리 출력이 BookVO 배열리스트에서 CategoryVO 배열리스트로 변경.
		System.out.println("===== 책 목록 출력 ===== \n");
		if(cdatas.isEmpty()) {
			System.out.println("출력할 리스트가 없습니다.");
			return;
		}
		for(CategoryVO cdata : cdatas) {
			System.out.println(cdata);
		}
		System.out.println("0번. 뒤로가기");
	}
	public int CategoryNum() {
		System.out.print("카테고리 번호 ");
		int num = tryCatch();
		return num;
	}
	public void bookRecommendEnd(BookVO bdata) {
		if(bdata == null) {
			System.out.println("출력할 책이 없습니다.");
			return;
		}
		System.out.println(bdata);
	}
	//	마이 페이지	--------------------------------------------------------
	public int myPage(MemberVO mVO) {
		System.out.println("===== 마 이 페 이 지 =====");
		System.out.println(mVO.getName() + "님 환영 합니다.");
		System.out.println("1. 쿠폰 보기.");
		System.out.println("2. 이름 변경.");
		System.out.println("3. 비밀번호 변경.");
		System.out.println("4. 회원 탈퇴.");
		System.out.println("5. 로그 아웃.");
		System.out.println("6. 장바구니.");
		System.out.println("7. 마이페이지 종료.");
		System.out.println("========================");
		return maxMyPage;
	}
	public String myPageMpw() {	//	마이페이지 입장 전에 비밀번호 입력 받기.
		System.out.print("비밀번호 입력 >> ");
		String mpw = sc.next();
		return mpw;
	}
	public void myPageMpwTrue() {	//	올바른 비밀번호 일 경우.
		System.out.println("마이페이지로 진입 합니다. \n");
	}
	public void myPageMpwFalse() {
		System.out.println("비밀번호가 일치하지 않습니다.. \n");	//	올바르지 않은 비밀번호 일 경우.
	}
	public void printMyPageCouponList(ArrayList<OwnCouponVO> cdatas) {	//	마이페이지 쿠폰 보기.
		System.out.println("===== 쿠폰 목록 출력 ===== \n");
		if(cdatas.isEmpty()) {
			System.out.println("출력할 리스트가 없습니다.");
			return;
		}
		for(OwnCouponVO cdata : cdatas) {
			System.out.println(cdata);
		}
	}
	public String nameChange() {	//	이름 변경 시작 알림.
		System.out.println("이름 변경을 시작 합니다. \n");
		System.out.print("변경할 이름 입력 >> ");
		String name = sc.next();
		return name;
	}
	public void nameChangeTrue() {	//	이름 변경 성공시.
		System.out.println("이름 변경 성공 ! \n");
	}
	public void nameChangeFalse() {	//	이름 변경 실패시.	=> 이럴 경우 없음.
		System.out.println("이름 변경 실패 .. \n");
	}
	public String pwChange(String mpw) {	//	비밀번호 변경 시작 알림.
		while(true) {
			System.out.println("비밀번호 변경을 시작 합니다. \n");
			System.out.print("변경할 비밀번호 입력 >> ");
			String newMpw = sc.next();
			if(mpw.equals(newMpw)) {
				System.out.println("기존 비밀번호와 같습니다.. \n");
				continue;
			}
			System.out.print("변경할 비밀번호 재 입력 >> ");
			String checkNewMpw = sc.next();
			if(!(newMpw.equals(checkNewMpw))) {
				System.out.println("비밀번호가 일치하지 않습니다. \n");
				continue;
			}
			return newMpw;
		}
	}
	public void pwChangeTrue() {	//	비밀번호 변경 성공시.
		System.out.println("비밀번호 변경 성공 ! \n");
	}
	public void pwChangeFalse() {	//	비밀번호 변경 실패시.
		System.out.println("비밀번호 변경 실패 .. \n");
	}
	public void pwChangeEnd() {	//	비밀번호 변경 후 로그아웃 알림.
		System.out.println("\n비밀번호를 변경하여 로그아웃 합니다. \n");
	}
	public int signDelete() {	//	회원 탈퇴 시작 알림.
		System.out.println("회원 탈퇴를 시작 합니다. \n");
		System.out.println("회원 탈퇴 : 1번");
		System.out.println("뒤로 가기 : 0번");
		return signDelete;
	}
	public String signDeleteCheck() {
		System.out.println("정말로 탈퇴하시려면 \"회원탈퇴\" 를 입력 하세요. \n");	//	회원 탈퇴 정말 하려면 회원탈퇴 입력.
		String check = sc.next();
		if(check.equals("회원탈퇴")) {
			return check;
		}
		else if(!(check.equals("회원탈퇴"))) {
			System.out.println("\"회원탈퇴\" 를 정확히 입력해 주세요. \n");
		}
		return "";
	}
	public void signDeleteTrue() {	//	회원 탈퇴 성공시.
		System.out.println("회원 탈퇴 성공 ! \n");
	}
	public void signDeleteFalse() {	//	회원 탈퇴 실패시.
		System.out.println("회원 탈퇴 실패 .. \n");
	}
	public void signDeleteEnd() {
		System.out.println("이용해주셔서 감사합니다.");
		System.out.println("처음 화면으로 돌아갑니다. \n");
	}
	public void signOut() {	//	로그 아웃 시작 알림.
		System.out.println("로그 아웃을 시작 합니다. \n");
	}
	public void signOutTrue() {	//	로그아웃 성공시.
		System.out.println("로그 아웃 성공 ! \n");
	}
	public void signOutFalse() {	//	로그아웃 실패시 => 이런 경우의 수 없긴 할듯.
		System.out.println("로그 아웃 실패 .. \n");
	}
	//	마이페이지 장바구니.
	public int cartMenu() {
		System.out.println("===== 장 바 구 니 메 뉴 =====");
		System.out.println("1.장바구니 목록 출력.");
		System.out.println("2.장바구니 전체 구매.");
		System.out.println("3.책 한권 삭제.");
		System.out.println("4.책 전체 삭제.");
		System.out.println("5.장바구니 메뉴 종료.");
		System.out.println("========================== \n");
		return maxCartMenu;
	}
	//	장바구니 목록 출력.
	public void printCartList(ArrayList<CartVO> cdatas) {
		System.out.println("===== 장바구니 목록 출력 ===== \n");
		if(cdatas.isEmpty()) {
			System.out.println("출력할 리스트가 없습니다. \n");
			return;
		}
		for(CartVO cdata : cdatas) {
			System.out.println(cdata);
		}
	}
	//	장바구니 구매 .
	public void cartBuyAll() {	//	장바구니 책 모두 구매 알림.
		System.out.println("장바구니에 있는 책을 모두 구매 합니다. \n");
	}
	public void cartBuyAllTrue() {
		System.out.println("책 구매 성공 ! \n");
	}
	public void cartBuyAllFalse() {
		System.out.println("책 구매 실패 .. \n");
	}
	public void cartDeleteOne() {	//	장바구니의 책 1권 삭제.
		System.out.println("장바구니에 있는 책 1권을 삭제 합니다. \n");
	}
	public void printCartListEmpty() {
		System.out.println("장바구니가 비어있습니다. \n");
	}
	public int cartDeleteOneNum() {	
		System.out.print("삭제할 책 번호 ");
		int num = tryCatch();
		return num;
	}
	public void cartDeleteAll() {	//	장바구니의 책 모두 삭제.
		System.out.println("장바구니에 있는 모든 책을 삭제 합니다. \n");
	}
	public void cartDeleteTrue() {
		System.out.println("책 삭제 성공 ! \n");
	}
	public void cartDeleteFalse() {
		System.out.println("책 삭제 실패 .. \n");
	}
	//	카트 페이지 종료.
	public void cartMenuEnd() {
		System.out.println("마이 페이지로 돌아 갑니다. \n");
	}
	//	마이 페이지 종료.
	public void leaveMyPage() {	//	뒤로 가기. (마이 페이지 종료)
		System.out.println("사용자 페이지로 돌아 갑니다. \n");
	}
	//	사용자 페이지 로그아웃.
	public void logout() {	//	사용자 페이지 로그아웃.
		System.out.println("로그아웃 .. \n");
	}

}	//	ClientView
