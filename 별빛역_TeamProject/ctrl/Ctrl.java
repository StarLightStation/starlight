package ctrl;

import java.util.ArrayList;

import model.BookDAO;
import model.BookVO;
import model.CartDAO;
import model.CartVO;
import model.CategoryDAO;
import model.CategoryVO;
import model.CouponDAO;
import model.CouponVO;
import model.MemberDAO;
import model.MemberVO;
import model.OwnCouponDAO;
import model.OwnCouponVO;
import view.AdminView;
import view.ClientView;

public class Ctrl {

	private MemberDAO mDAO;
	private BookDAO bDAO;
	private ClientView clientView;
	private AdminView adminView;
	private CouponDAO cDAO;
	private MemberVO member;
	private CartDAO cartDAO;
	private OwnCouponDAO ocDAO;
	private CategoryDAO categoryDAO;

	public Ctrl() {

		this.clientView = new ClientView();
		this.adminView = new AdminView();
		this.mDAO = new MemberDAO();
		this.bDAO = new BookDAO();
		this.cartDAO = new CartDAO();
		this.ocDAO = new OwnCouponDAO();
		this.cDAO = new CouponDAO();
		this.categoryDAO = new CategoryDAO();
		this.member = null;

	}

	public void startApp() {

		/*
		 ArrayList<BookVO> mdatas1 = Crawling.sample();
		 for (int i = 0; i < mdatas1.size(); i++) {
		 BookVO mdata = mdatas1.get(i);
		 bDAO.insert(mdata);
		 }
		 */
		//	데이터 insert()

		while (true) {											// 	프로그램 메뉴.
			int maxMenu = clientView.printMenuStart();			//	프로그램 메뉴 출력.
			int action = clientView.getNum(maxMenu);			// 	프로그램 메뉴 번호 입력.

			if (action == 1) {									// 	로그인.
				String memberID = clientView.signUpId();		//	아이디 입력.
				String memberPW = clientView.signInPw();		// 	비밀번호 입력.
				MemberVO mVO = new MemberVO(memberID, memberPW);
				mVO.setTmpCondition("회원정보");
				member = mDAO.selectOne(mVO);					// 	아이디/비밀번호 비교후 참조변수 member에 로그인한 회원의 정보를 대입.
				if (member == null) {							// 	유효하지 않는 아이디 또는 올바르지 않는 비밀번호 일 경우.
					clientView.signInFalse();					// 	로그인 실패.
					continue;									// 	다시 프로그램 메뉴로 이동.
				}
				clientView.signInTrue();						// 	로그인 성공 출력.
				if (member.getMid().equals("admin")) {			// 	현재 로그인한 아이디가 관리자 아이디이면, 관리자 모드 실행. (관리자 아이디는 admin으로 고정).
					while (true) {												//	관리자 메뉴.
						int maxAdminMenu = adminView.adminMenu();				// 	관리자 메뉴 출력.
						action = clientView.getNum(maxAdminMenu);				// 	관리자 메뉴 번호 입력.

						if (action == 1) {										//	책 재고 변경.
							int maxCntAction = adminView.bookCntChange();		// 	책 재고를 더할지 뺄지 선택.
							int cntAction = adminView.getNum(maxCntAction);
							if(cntAction == 0) {								//	뒤로 가기 0번.
								continue;
							}
							else if (cntAction == 1) {							//	1번 => 재고 더하기.
								int getBookNum = adminView.bookCntChangeNum();	//	변경할 책의 번호 입력.
								BookVO bdata = new BookVO(getBookNum);
								bdata.setSk("한권출력");							//	selectOne()에 기능이 2개 이상이라, 필요한 키워드.
								bdata = bDAO.selectOne(bdata);
								adminView.printBookData(bdata);
								if (bdata == null) {							//	해당하는 번호의 책이 없을 경우.
									adminView.bookCntChangeNumFalse();			//	책 한권 출력 실패.
									continue;
								}
								int bookCnt = adminView.bookCntChangeCnt();		//	해당하는 번호의 책이 있을 경우, 변경할 재고 개수 입력.
								bdata.setSk("재고추가");
								bdata.setTmpCnt(bookCnt);
								boolean flag = bDAO.update(bdata);				//	update()에 기능이 2개 이상이라, 필요한 키워드.
								if (!flag) {	
									adminView.bookCntChangeFalse();				//	재고변경 실패.	=>	위에서, 책 번호를 확인하고 내려왔고,
								}												//	더하는 경우라 실패의 경우의 수 드물다.
								adminView.bookCntChangeEnd();					//	재고변경 성공.

							}
							else if (cntAction == 2) {							//	2번 => 재고 빼기.
								int getBookNum = adminView.bookCntChangeNum();	// 	변경할 책의 번호 입력
								BookVO bdata = new BookVO(getBookNum); 			// 	책의 번호를 넘겨준다.
								bdata.setSk("한권출력"); 							//	selectOne()에 기능이 2개 이상이라, 필요한 키워드.
								bdata = bDAO.selectOne(bdata); 					// 	책의 번호에 해당하는 책의 정보를 가져옴
								adminView.printBookData(bdata);
								if (bdata == null) {							//	해당하는 번호의 책이 없을 경우.
									adminView.bookCntChangeNumFalse();
									continue;
								}
								int bookCnt = adminView.bookCntChangeCnt();		//	해당하는 번호의 책이 있을 경우, 변경할 재고 개수 입력.
								if(bookCnt > bdata.getCnt()) {					//	책의 재고보다, 빼려고 하는 수가 더 클 경우.	=>	장치.
									adminView.bookCntChangeFalse();				//	재고 변경 실패 출력.
									continue;
								}
								bdata.setSk("재고빼기"); 
								bdata.setTmpCnt(bookCnt); 
								boolean flag = bDAO.update(bdata);				//	update()에 기능이 2개 이상이라, 필요한 키워드.
								if (!flag) {
									adminView.bookCntChangeFalse();				//	재고변경 실패.	=>	위에서, 책 번호를 확인하고 내려왔고,
								}												//	빼는 경우인데, 기존 책 재고보다 빼는 수가 많을 경우 재고변경 실패.
								adminView.bookCntChangeEnd();					// 	재고변경 성공.
							}
						}
						else if (action == 2) {									// 	쿠폰 증정.
							adminView.couponEvent();
							String eventID = adminView.couponEventmID();		// 	사용자에게 추천인 입력 받기.
							MemberVO mdata = new MemberVO(eventID);				// 	M의 selectOne()가 받아야 한다. MemberVO 타입의 객체로 보내 준다.
							mdata.setTmpCondition("중복검사");
							if (mDAO.selectOne(mdata) == null || mDAO.selectOne(mdata).getMid().equals("admin")) {	//	유효하지 않는 회원 또는 관리자 아이디 이면,
								adminView.couponEventMIDFalse();													//	설계상 관리자는 쿠폰 사용할 일이 없다.
								continue;
							}
							adminView.couponEventMIDTrue();						//	추천인이 유효한 경우 멘트 출력.
							OwnCouponVO ocVO = new OwnCouponVO(mdata.getMid());
							ocVO.setTmpCondition("추천인할인쿠폰증정");	
							ocDAO.insert(ocVO);									//	쿠폰 증정은 ownCouponDAO 에서 insert() 메서드.
							adminView.couponEventEnd();							//	쿠폰 증정 성공 알림.
						}
						else if (action == 3) {									//	회원 목록 출력.
							ArrayList<MemberVO> mdatas = new ArrayList<MemberVO>();
							mdatas = mDAO.selectAll(null);						//	DB에서 회원 정보를 전부 보내준다.				
							for(int i = 0;i < mdatas.size();i++) {				
								if(mdatas.get(i).getMid().equals("admin")) {	//	MemberVO의 배열리스트에 담긴 회원 정보들에서 mid가 admin이면 remove().
									mdatas.remove(i);
								}
							}
							adminView.printMembersList(mdatas);					//	관리자를 제외한 모든 회원을 출력.
						}
						else if (action == 4) {									// 	관리자 모드 종료.
							adminView.adminMenuEnd();
							break;
						}

					} // end while (관리자 모드)
				}
				else {															//	사용자 모드 실행.
					while (true) {
						if (member == null) {									//	로그아웃이 되서 member가 null이면, 사용자 페이지에서 나가지는 로직.
							break;
						}
						int maxClientMenu = clientView.clientMenu();			// 	사용자 메뉴 출력.
						action = clientView.getNum(maxClientMenu);				// 	사용자 메뉴 번호 입력.
						BookVO bVO = new BookVO();

						if (action == 1) {										// 	책 목록 출력.
							ArrayList<BookVO> bdatas = new ArrayList<BookVO>(); // 	책 목록을 담아줄 리스트.
							bVO.setSk("이름검색");	
							bVO.setChangeTitle("");								// 	전체 출력을 위한 장치.
							bdatas = bDAO.selectAll(bVO);						//	리스트에 책 목록 저장.
							clientView.printBookList(bdatas);
						}
						else if (action == 2) {											//	책 검색

							int maxSearchbookMenu = clientView.printBookMenuStart();	// 	책 검색 메뉴 4
							int searchNum = clientView.getNum(maxSearchbookMenu);		//	0 ~ 4
							if(searchNum == 0) {										//	뒤로가기
								continue;
							}
							clientView.printBookList(bDAO.selectAll(clientView.bookSearch(searchNum)));
						}
						else if (action == 3) {											//	책 구매.
							clientView.bookBuy();										//	책 구매 시작 안내 멘트.
							int bookNum = clientView.bookBuyNum();						//	책 번호 입력.
							BookVO bdata = new BookVO(bookNum,"",0,0,0);
							bdata.setSk("한권출력");
							if (bDAO.selectOne(bdata) == null) {						//	없다면.
								clientView.bookBuyFalseNum();							//	없다고 출력.
								continue;
							}
							int bookCnt = clientView.bookBuyCnt();						//	구매할 책 개수 입력.
							bdata = new BookVO(bookNum,"",0,bookCnt,0);
							bdata.setSk("한권출력");
							bdata = bDAO.selectOne(bdata);
							if (bdata.getCnt() < bookCnt) {								//	책 재고가 없다면,
								clientView.bookBuyFalseCnt();							//	없다고 출력 멘트.
								continue;
							}
							int insertCart = clientView.bookBuyEnd();					//	구매가 끝나면 바로 구매할건지 장바구니에 담을건지 물어보기.
							if (insertCart != 1) {										//	장바구니 담기. (V에서 1번 입력이 아니면 장바구니 담기)
								CartVO cVO = new CartVO(member.getMid(),bdata.getNum(),bookCnt);
								if (cartDAO.selectOne(cVO) != null) { 					//	만약 selectOne() 이 null이 아니라면 (중복이라면)
									cVO = cartDAO.selectOne(cVO);
									cVO.setCnt(bookCnt);
									cartDAO.update(cVO); 								//	update로 재고만 변경 시켜줌
									continue;
								}
								cartDAO.insert(cVO); 									//	중복되지 않았다면 insert().
								continue;											
							}
							// 쿠폰 들고있는지 확인하기
							int useCoupon = clientView.bookBuyCoupon();					//	구매할때, 쿠폰 사용 여부 물어보기.
							if (useCoupon == 1) {										//	V에서 1번이면 쿠폰 사용 하기.
								ArrayList<OwnCouponVO> ocdatas = ocDAO.selectAll(new OwnCouponVO(member.getMid()));	//	쿠폰의 목록 데이터를 배열리스트에 저장.
								if(!(ocdatas.isEmpty())) {
									clientView.printBookBuyCouponList(ocdatas); 									// 쿠폰의 전체 목록 출력
									int selectCouponNum = clientView.getNum(ocdatas.size());// 쿠폰의 사이즈 만큼의 범위에서 사용자가 쿠폰 선택
									// CouponVO cdata = new CouponVO();// tmp를 사용할 쿠폰 객체 생성

									CouponVO cVO = new CouponVO(ocdatas.get(selectCouponNum - 1).getCnumfk());		// 사용자에게 보이는
									// 쿠폰 번호가
									// 아니라 진짜 쿠폰
									// 번호를 가져옴
									cVO = cDAO.selectOne(cVO);												//	사용자가 선택한 쿠폰에 대해서 할인률 가져오기.
									cVO.setTmpprice(bdata.getPrice());										//	구매한 총 금액을 넘겨줌.
									cDAO.update(cVO);														//	가격에 쿠폰 적용.
									bdata = new BookVO(bookNum,null,(int) cVO.getTmpprice(),bookCnt,0);		//	double 타입이라 int 타입으로 형변환.
									bdata.setSk("한권출력");
									bdata = bDAO.selectOne(bdata);
									// 	할인이 적용된 현재의 책.
									//	clientView.printBookData(bdata);									//	책 정보 보여주기.
									OwnCouponVO oVO = new OwnCouponVO(member.getMid(),ocdatas.get(selectCouponNum - 1).getCnumfk());	//	현재 사용자가 들고있는 쿠폰 객체.
									oVO.setTmpCondition("쿠폰사용");
									ocDAO.delete(oVO);														//	사용한 쿠폰 삭제.
								}
								else {
									clientView.bookBuyCouponFalse();
								}
							}
							//	쿠폰 사용을 안했다면 그냥 원래 값을 보여 주는 로직.
							clientView.printBookData(bdata);							//	책 정보 보여주기.
							member.setTmpCondition("토탈변경");
							member.setTmpPrice(bdata.getPrice() * bookCnt);
							mDAO.update(member);
							member.setTmpCondition("회원정보");
							member = mDAO.selectOne(member);							//	★ 갱신된 DB정보 가져오기.
							bdata.setSk("재고빼기");
							bdata.setTmpCnt(bookCnt);									//	구매한 만큼 DB에서 빼주기.
							bDAO.update(bdata);											//	재고 마이너스.
							OwnCouponVO oVO = new OwnCouponVO(member.getMid());
							oVO.setTmpCondition("등급쿠폰증정");
							oVO.setTmpGrade(member.getGradeUp());
							// member.getTotal();// 현재 사용자의 총액
							// member.getMgrade();// 현재 사용자의 등급
							if (member.getTotal() >= 100000 && member.getMgrade().equals("BRONZE")
									&& member.getGradeUp() == 0) {
								member.setTmpCondition("등급변경");
								mDAO.update(member);
								member.setTmpCondition("회원정보");
								member = mDAO.selectOne(member);						//	★ 갱신된 DB정보 가져오기.
								ocDAO.insert(oVO);
								oVO.setTmpGrade(member.getGradeUp());
							}
							if (member.getTotal() >= 300000 && member.getMgrade().equals("SILVER")
									&& member.getGradeUp() == 1) {
								member.setTmpCondition("등급변경");
								mDAO.update(member);
								member.setTmpCondition("회원정보");
								member = mDAO.selectOne(member);						//	★ 갱신된 DB정보 가져오기.
								ocDAO.insert(oVO);
								oVO.setTmpGrade(member.getGradeUp());
							}
							if (member.getTotal() >= 600000 && member.getMgrade().equals("GOLD")
									&& member.getGradeUp() == 2) {
								member.setTmpCondition("등급변경");
								mDAO.update(member);
								member.setTmpCondition("회원정보");
								member = mDAO.selectOne(member);						//	★ 갱신된 DB정보 가져오기.
								ocDAO.insert(oVO);
								oVO.setTmpGrade(member.getGradeUp());
							}
							clientView.bookBuyTrue();									//	책 구매 성공 멘트 출력.
						}
						else if (action == 4) {											// 	책 추천.
							clientView.bookRecommend();									// 	책 추천 시작한다는 멘트 출력.
							// 사용자에게 책 카테고리 목록 보여주는 코드.
							CategoryVO cVO = new CategoryVO();							// 	카테고리 객체 생성.
							//	ArrayList<CategoryVO> cdatas = new ArrayList<CategoryVO>();
							//	cdatas = categoryDAO.selectAll(cVO);
							//	clientView.printCategoryList(cdatas);
							clientView.printCategoryList(categoryDAO.selectAll(cVO));	// 	카테고리 목록 출력.
							int selectCategory = clientView.CategoryNum();				//	사용자가 카테고리 입력.
							if(selectCategory == 0) {									//	뒤로가기.
								continue;
							}
							BookVO bdata = new BookVO(0,null,0,0,selectCategory);		// 	사용자가 입력한 카테고리 번호를 가진 책이 있는지 검사.
							bdata.setSk("랜덤추천");
							clientView.bookRecommendEnd(bDAO.selectOne(bdata));			//	카테고리에 해당하는 책을 하나 출력.
						}
						else if (action == 5) {										// 	마이페이지.
							clientView.myPageMpwTrue();
							if (!clientView.signInPw().equals(member.getMpw())) {	//	마이페이지 입장시, 비밀번호를 입력 후 입장.
								clientView.myPageMpwFalse();						//	만약에 회원의 비밀번호와 입력한 비밀번호가 다르면, 비밀번호 다르다는 멘트 출력.
								continue;
							}
							while (true) {											// 	비밀번호가 일치 하면 마이페이지 입장.
								if (member == null) {								//	로그아웃이 되서 member가 null이면, 마이페이지에서 나가지는 로직.
									break;
								}
								int maxMypageMenu = clientView.myPage(member);		//	마이페이지 메뉴 출력.
								int select = clientView.getNum(maxMypageMenu);		//	사용자로부터 마이페이지 프로그램 메뉴 입력 받기.

								if (select == 1) {									// 	본인 쿠폰 보기.
									ArrayList<OwnCouponVO> ocdatas = ocDAO.selectAll(new OwnCouponVO(member.getMid()));
									clientView.printBookBuyCouponList(ocdatas); 	// 	쿠폰의 전체 목록 출력.
								}
								else if (select == 2) {								//	이름 변경.
									String changeName = clientView.nameChange();	// 	변경할 이름 입력.
									MemberVO mdata = new MemberVO(member.getMid());
									mdata.setTmpString(changeName);
									mdata.setTmpCondition("이름변경");
									boolean flag = mDAO.update(mdata);				//	이름 변경.
									if (!flag) {									
										clientView.nameChangeFalse();				// 	이름 변경 실패시 => 이런 경우의 수 거의 없음.
										continue;
									}
									clientView.nameChangeTrue();					//	이름 변경 성공.
									mdata = new MemberVO(member.getMid(),member.getMpw(),changeName);
									mdata.setTmpCondition("회원정보");
									member = mDAO.selectOne(mdata);					//	현재 로그인 정보 최신화.
								}
								else if (select == 3) {								//	비밀번호 변경.
									String mpw = member.getMpw();
									String changePW = clientView.pwChange(mpw);		// 	변경할 비밀번호 입력.
									MemberVO mdata = new MemberVO(member.getMid());
									mdata.setTmpString(changePW);
									mdata.setTmpCondition("비번변경");
									boolean flag = mDAO.update(mdata);				// 	비번
									if (!flag) {									// 	비밀번호 변경 실패.
										clientView.pwChangeFalse();
										continue;
									}
									clientView.pwChangeTrue();
									clientView.pwChangeEnd();									// 	비밀번호 변경으로 인한 로그아웃 멘트.
									member = null;												// 	현재 로그인한 회원의 정보가 담겨져 있는 member == null. => 로그아웃.
								}
								else if (select == 4) {											// 	회원 탈퇴.
									int maxDeleteCheckNum = clientView.signDelete();			// 	1. 예 / 0. 뒤로가기.
									int deletecheck = clientView.getNum(maxDeleteCheckNum);		
									if (deletecheck != 1) {										
										continue;
									}
									String realCheck = clientView.signDeleteCheck();//	사용자로부터 확인받기.
									if (realCheck.equals("")) {						// 	체크한게 틀리다면,
										clientView.signDeleteFalse();				// 	실패할 경우 회원탈퇴 실패 멘트 출력.
										continue;									//	마이페이지로 continue.
									}
									mDAO.delete(member);							//	"회원탈퇴" 문구를 올바르게 작성했다면 현재 로그인한 회원의 정보가 담겨져있는 member를 delete().
									member = null;									// 	회원탈퇴를 했으니, member = null로 초기화.
									clientView.signDeleteTrue();					// 	회원탈퇴가 성공했음을 알리는 멘트 출력.
								}
								else if (select == 5) {								// 	로그 아웃.
									clientView.signOut();
									member = null;									//	현재 로그인한 정보 없애기. => 로그아웃.
									clientView.signOutTrue();
								}
								else if (select == 6) {								// 	장바구니.
									while (true) {
										int maxCartMenu = clientView.cartMenu();	//	 장바구니 메뉴 최대 갯수.
										int selectCart = clientView.getNum(maxCartMenu);

										ArrayList<CartVO> cdatas = cartDAO.selectAll(new CartVO(member.getMid()));
										//	DB에 저장되어 있는 장바구니 목록을 가져오는데, 장바구니는 회원이 들고있는 장바구니 이다.

										if (selectCart == 1) {						// 	장바구니 목록 출력.
											clientView.printCartList(cdatas);		//	장바구니 전체 목록 출력.
										} 
										else if (selectCart == 2) {					// 	장바구니 전체 구매.
											if (cdatas.isEmpty()) {
												clientView.printCartListEmpty();	//	장바구니가 비어있으면, 비어있다는 멘트 출력.
												continue;
											}
											clientView.cartBuyAll();				//	장바구니가 비어있지 않으면, 책을 전부 구매한다는 멘트 출력.
											ArrayList<BookVO> bdatas = new ArrayList<BookVO>();
											for (int i = 0; i < cdatas.size(); i++) {
												int cnt = cdatas.get(i).getCnt();   //	장바구니 재고.
												BookVO bdata = new BookVO (cdatas.get(i).getBnumfk());
												bdata.setSk("한권출력");
												bdata = bDAO.selectOne(bdata);      // 	실제 책에 있는 재고.
												if(bdata.getCnt() >= cnt) {    		// 	중간에 재고가 없다면 구매 실패                                    
													bdatas.add(new BookVO(cdatas.get(i).getBnumfk()));
													bdata.setTmpCnt(cnt);      		
												}
												else {								//	실제 책의 재고가 부족하면 구매 실패.
													//	System.out.println(bdata.getNum() +" " + bdata.getTitle() + " 의 재고가 부족합니다"); // 뷰
												}
											}
											//	장바구니에 담겨있는 갯수가 남은 재고보다 적은지 검사하는 로직.
											int total = 0;									//	장바구니 전체 구매를 한 가격
											if(cdatas.size() == bdatas.size()) {
												for(int i = 0; i < cdatas.size(); i++) {
													bdatas.get(i).setSk("재고빼기");
													bdatas.get(i).setTmpCnt(cdatas.get(i).getCnt());
													bDAO.update(bdatas.get(i));				//	재고 및 누적금액 갱신.

													total += bdatas.get(i).getPrice()*cdatas.get(i).getCnt();
													//	CartVO cVO = new CartVO(cdatas.get(i).getMidfk(), cdatas.get(i).getBnumfk(), cdatas.get(i).getCnt());
													// 	BookVO에 현재 사용자의 장바구니 목록에 있는 Book PK값을 넘겨준다.
												}
												member.setTmpCondition("토탈변경");
												member.setTmpPrice(total);
												mDAO.update(member);
												member.setTmpCondition("회원정보");
												member = mDAO.selectOne(member);			//	★ 갱신된 DB정보 가져오기.
												OwnCouponVO oVO = new OwnCouponVO(member.getMid());
												oVO.setTmpCondition("등급쿠폰증정");
												oVO.setTmpGrade(member.getGradeUp());

												if (member.getTotal() >= 100000 && member.getMgrade().equals("BRONZE")
														&& member.getGradeUp() == 0) {
													member.setTmpCondition("등급변경");
													mDAO.update(member);
													member.setTmpCondition("회원정보");
													member = mDAO.selectOne(member);		//	★ 갱신된 DB정보 가져오기.
													ocDAO.insert(oVO);
													oVO.setTmpGrade(member.getGradeUp());
												}
												if (member.getTotal() >= 300000 && member.getMgrade().equals("SILVER")
														&& member.getGradeUp() == 1) {
													member.setTmpCondition("등급변경");
													mDAO.update(member);
													member.setTmpCondition("회원정보");
													member = mDAO.selectOne(member);		//	★ 갱신된 DB정보 가져오기.
													ocDAO.insert(oVO);
													oVO.setTmpGrade(member.getGradeUp());
												}
												if (member.getTotal() >= 600000 && member.getMgrade().equals("GOLD")
														&& member.getGradeUp() == 2) {
													member.setTmpCondition("등급변경");
													mDAO.update(member);
													member.setTmpCondition("회원정보");
													member = mDAO.selectOne(member);		//	★ 갱신된 DB정보 가져오기.
													ocDAO.insert(oVO);
													oVO.setTmpGrade(member.getGradeUp());
												}
												CartVO cVO = new CartVO(member.getMid());
												cVO.setSk("전체삭제");
												cartDAO.delete(cVO);
												clientView.cartBuyAllTrue();
											}
											else {
												clientView.cartBuyAllFalse();
											}
										}
										else if (selectCart == 3) {							//	책 한권 삭제.
											if (cdatas.isEmpty()) {
												clientView.printCartListEmpty();
												continue;
											}
											clientView.cartDeleteOne();   
											int deleteNum = clientView.cartDeleteOneNum();										//	사용자로부터 삭제할 책 번호 입력.
											CartVO cdata = new CartVO(member.getMid(),cdatas.get(deleteNum - 1).getBnumfk());	// 	책 번호를 담을 객체.
											cdata.setSk("한개삭제");
											cartDAO.delete(cdata);							// 	책 삭제.
											clientView.cartDeleteTrue();

										}
										else if (selectCart == 4) {							//	책 전체 삭제.
											if (cdatas.isEmpty()) {
												clientView.printCartListEmpty();
												continue;
											}
											CartVO cdata = new CartVO(member.getMid());
											cdata.setSk("전체삭제");
											cartDAO.delete(cdata);
											clientView.cartDeleteAll();
											clientView.cartDeleteTrue();
										}
										else if (selectCart == 5) {		//	장바구니 메뉴 종료.
											clientView.cartMenuEnd();
											break;						//	마이페이지로 돌아가기.
										}
									}	// end while (마이페이지 장바구니)
								}
								else if (select == 7) {					//	마이페이지 종료.
									clientView.leaveMyPage();
									break;
								}
							}	//	end while (마이페이지)
						}
						else if (action == 6) {							// 	사용자 모드 종료.
							clientView.logout();
							break;
						}
					}	//	end while(사용자 모드)
				}	//	end else(사용자모드)
			}
			else if (action == 2) {							// 	회원가입.
				clientView.signUp(); 						// 	회원가입 시작 안내 멘트 출력.
				String mid = clientView.signInId(); 		//	사용할 아이디 입력 받기.
				MemberVO mVO = new MemberVO(mid);
				mVO.setTmpCondition("중복검사");				//	아이디가 중복인지 아닌지를 확인하려면 DB를 확인 해야 한다.
				if (mDAO.selectOne(mVO) != null) { 			// 	아이디가 중복되면,
					clientView.signUpIdFalse(); 			//	아이디 중복 멘트 출력후,
					continue; 								// 	프로그램 메뉴로 돌아가기.
				}
				clientView.signUpIdTrue(); 					//	중복되는 아이디가 없으면, 중복되는 아이디가 없다는것을 알려주고,

				String mpw = clientView.signUpPw(); 		// 	사용할 비밀번호 입력 받기. =>	V에서 비밀번호 재확인 기능 수행하고 비밀번호 값을 return.

				String name = clientView.signUpName(); 		// 	사용할 이름 (닉네임) 입력 받기.

				mVO = new MemberVO(mVO.getMid(),mpw,name);	//	아이디, 비밀번호, 이름 (닉네임)을 객체로 해서 M의 insert() 에게 전달.
				boolean flag = mDAO.insert(mVO);			
				if (!flag) {
					clientView.signUpFalse();				//	회원가입 실패시 => 위에서 아이디 중복검사를 마쳤기 때문에 실패할 경우의 수는 거의 드물다.
					continue;
				}
				clientView.signUpTrue(); 					//	회원가입 성공시 =>	추천인 입력 여부.
				while (true) {								//	추천인 입력	=>	while(true)로 한 이유 : 추천인을 혹시나 잘못 입력했을 경우를 고려.
					String recommender = clientView.signUpRecommend(mid);		//	자기 자신 추천인 등록은 안되기에, 자추를 방지하는 코드.	=>	V에서 로직 구현.
					if (!recommender.equals("추천인등록안함")) {					//	V에서 추천인 등록 하기를 선택했을 경우.

						MemberVO mdata = new MemberVO(recommender);				//	유효한 추천인 아이디인지 DB를 확인 해야 한다.
						mdata.setTmpCondition("중복검사");
						if (mDAO.selectOne(mdata) != null) {					//	추천인 아이디가 유효하다면.
							OwnCouponVO ocVO = new OwnCouponVO(mVO.getMid());	//	현재 회원가입에 성공한 회원의 아이디의 정보를 의미하는 mVO.getMid().
							ocVO.setTmpCondition("추천인할인쿠폰증정");				//	OwnCouponDAO에 insert()에 기능이 2개 이상이라 필요한 키워드.
							ocDAO.insert(ocVO);									// 	방금 회원가입한 회원에게 추천인할인쿠폰증정 성공.

							ocVO = new OwnCouponVO(recommender);				//	추천을 받은 사람도 쿠폰을 받아야 하는 상황.
							ocVO.setTmpCondition("추천인할인쿠폰증정");
							ocDAO.insert(ocVO);									//	추천을 받은 사람에게도 추천인할인쿠폰증정 성공.
							clientView.signUpRecommendTrue();					//	추천인 등록 성공.
							break;
						}
						else {													//	추천인 아이디가 유효하지 않으면.
							clientView.signUpRecommendFalse();					//	일치하는 추천인 없다고 출력.
							continue;
						}
					}	//	end if (추천인을 입력한 경우)
					else if(recommender.equals("추천인등록안함")) {					//	추천인을 입력하지 않은 경우.
						break;
					}
				}	//	end while (추천인 입력)	
				clientView.signUpTrue();										// 	회원가입 성공 안내 멘트.
			}
			else if (action == 3) {												// 	프로그램 종료.
				clientView.printMenuEnd();				
				break;
			}

		}	//	end while (프로그램 전체)

	}	//	end startApp()

}	//	ctrl
