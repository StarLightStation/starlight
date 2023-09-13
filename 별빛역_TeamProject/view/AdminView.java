package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.BookVO;
import model.MemberVO;

public class AdminView {

	//	[멤버변수]
	private static final int adminMenu = 4;
	private static final int bookCntChange = 2;

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

	//	관리자 페이지	--------------------------------------------------------
	public int adminMenu() {
		System.out.println("===== 관리자 페이지 =====");
		System.out.println("1. 책 재고 변경.");
		System.out.println("2. 쿠폰 증정.");
		System.out.println("3. 회원 목록 출력.");
		System.out.println("4. 관리자 모드 종료.");
		System.out.println("====================== \n");
		return adminMenu;
	}
	//	책 재고 변경	--------------------------------------------------------
	public int bookCntChange() {
		System.out.println("책 재고 변경을 시작 합니다. \n");
		System.out.println("재고 더하기 : 1번");
		System.out.println("재고 빼기 : 2번");
		System.out.println("뒤로 가기 : 0번");
		return bookCntChange;
	}
	public int bookCntChangeNum() {
		System.out.print("책 번호 ");
		int cnt = tryCatch();
		return cnt;
	}
	public void bookCntChangeNumTrue() {
		System.out.println("유효한 책 번호 입니다. \n");
	}
	public void bookCntChangeNumFalse() {
		System.out.println("유효하지 않는 책 번호 입니다. \n");
		System.out.println("책 번호를 다시 입력 하세요 .. \n");
	}
	public void printBookData(BookVO bdata) {
		if(bdata == null) {	//	굳이 없어도 되는듯.
			System.out.println("출력할 책이 없습니다.");
			return;
		}
		System.out.println(bdata);
	}
	public int bookCntChangeCnt() {
		System.out.print("변경 개수 ");
		int cnt = tryCatch();
		return cnt;
	}
	public void bookCntChangeFalse() {
		System.out.println("책 재고 변경 실패 .. \n");		//	기존 재고보다 삭제하려고 하는 개수가 더 많을때.
	}
	public void bookCntChangeEnd() {
		System.out.println("책 재고 변경 성공 ! \n");
	}
	//	쿠폰 증정	--------------------------------------------------------
	public void couponEvent() {	//	쿠폰 증정 메뉴 알림.
		System.out.println("===== 쿠폰 증정 메뉴 ===== \n");
	}
	public String couponEventmID() {	//	쿠폰 증정시, 어느 회원에게 줄지 아이디를 입력하게 하는 알림.
		System.out.println("어느 회원에게 쿠폰을 주시겠습니까 ? \n");
		System.out.print("아이디 입력 >> ");
		String mID = sc.next();
		return mID;
	}
	public void couponEventMIDTrue() {	//	유효한 아이디일 경우.
		System.out.println("유효한 회원 입니다 ! \n");
	}
	public void couponEventMIDFalse() {	//	유효하지 않은 아이디일 경우.
		System.out.println("유효하지 않는 회원 입니다.. \n");
	}
	public int couponEventCnt() {
		System.out.println("증정할 쿠폰의 개수를 입력 하세요. \n");
		System.out.print("개수 ");
		int cnt = tryCatch();
		return cnt;
	}
	public void couponEventEnd() {	//	쿠폰 증정 성공.
		System.out.println("===== 쿠폰 증정 성공 ===== \n");
	}
	//	회원 목록 출력	--------------------------------------------------------
	public void printMembersList(ArrayList<MemberVO> mdatas) {
		System.out.println("===== 회원 목록 출력 ===== \n");
		if(mdatas.isEmpty()) {
			System.out.println("출력할 리스트가 없습니다.");
			return;
		}
		for(MemberVO mdata : mdatas) {
			System.out.println(mdata);
		}
	}
	//	관리자 모드 종료	--------------------------------------------------------
	public void adminMenuEnd() {
		System.out.println("===== 관리자 모드 종료 ===== \n");
	}

}	//	AdminView
