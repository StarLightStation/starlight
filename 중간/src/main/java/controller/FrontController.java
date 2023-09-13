package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. 요청을 추출
		String uri = request.getRequestURI();
        String cp = request.getContextPath();
        String command = uri.substring(cp.length());
        command = command.substring(command.lastIndexOf("/"));
        
		System.out.println("FrontController 클래스 : doAction() 메서드 : command : " + command);

		// 2. Action 클래스의 execute() 메서드를 호출
		ActionForward forward = null;
		// =============================[메인 페이지]========================================
		if (command.equals("/main.do")) {
			// main 화면으로 이동
			forward = new MainAction().execute(request, response);
		}
		// ===============================================================================

		// =================================[로그인]========================================

		else if (command.equals("/loginPage.do")) {
			// 로그인 페이지 화면으로 이동
			forward = new LoginPageAction().execute(request, response);
		}
		else if (command.equals("/login.do")) {
			// 로그인 버튼
			forward = new LoginAction().execute(request, response);
		}
		else if (command.equals("/logout.do")) {
			// 로그아웃 버튼
			forward = new LogoutAction().execute(request, response);
		}
		else if (command.equals("/googleLogin.do")) {
			// 구글 로그인 버튼(API)
			forward = new GoogleLoginAction().execute(request, response);
		} 
		// ===============================================================================

		// =================================[회원가입]========================================

		else if (command.equals("/signupPage.do")) {
			// 회원가입 페이지 화면으로 이동
			forward = new SignupPageAction().execute(request, response);
		}else if (command.equals("/signup.do")) {
			// 회원가입 버튼
			forward = new SignupAction().execute(request, response);
		} else if (command.equals("/googleSignup.do")) {
			// 구글 회원가입 버튼
			forward = new GoogleSignupAction().execute(request, response);
		}
		// ===============================================================================
		// ===============================[아이디 찾기]=======================================

		else if (command.equals("/searchIDPage.do")) {
			// 아이디 찾기 페이지
			forward = new SearchIDPageAction().execute(request, response);
		} else if (command.equals("/searchID.do")) {
			// 아이디 찾기
			forward = new SearchIDAction().execute(request, response);
		} else if (command.equals("/send.do")) {
			// 문자 보내기(찾은 아이디 전송)
			forward = new SendAction().execute(request, response);
		}
		// ===============================================================================


		// ================================[마이페이지]=======================================

		else if (command.equals("/mypage.do")) {
			// 마이 페이지
			forward = new MypageAction().execute(request, response);
		} else if (command.equals("/mysubscription.do")) {
			// 구독 페이지(구독한 정보)
			forward = new MySubscriptionAction().execute(request, response);
		} else if (command.equals("/orderHistory.do")) {
			// 주문 내역 페이지(구매한 상품들 정보)
			forward = new OrderHistoryAction().execute(request, response);
		}
		else if (command.equals("/review.do")) {
			// 리뷰 페이지(작성가능한 리뷰 & 작성한 리뷰 수정 → 버튼으로 페이지 이동)
			forward = new ReviewAction().execute(request, response);
		}
		else if (command.equals("/writereview.do")) {
			// 작성 가능한 리뷰 페이지(작성가능한 상품들 출력 → 리뷰 작성 화면은 모달창)
			forward = new WriteReviewAction().execute(request, response);
		}
		else if (command.equals("/writereviewOK.do")) {
			// 리뷰 작성 완료(모달창 내부에서 버튼 클릭)
			forward = new WriteReviewOKAction().execute(request, response);
		} else if (command.equals("/updatereview.do")) {
			// 리뷰 수정 페이지(작성한 리뷰들 출력 → 리뷰 수정 화면은 모달창)
			forward = new UpdateReviewAction().execute(request, response);
		} else if (command.equals("/updatereviewOK.do")) {
			// 리뷰 수정 완료(모달창 내부에서 버튼 클릭)
			forward = new UpdateReviewOKAction().execute(request, response);
		} else if (command.equals("/deletereview.do")) {
			// 리뷰 삭제 완료(리뷰 수정 페이지에서 버튼 클릭)
			forward = new DeleteReviewAction().execute(request, response);
		} else if (command.equals("/member.do")) {
			// 회원정보 페이지
			forward = new MemberAction().execute(request, response);
		} else if (command.equals("/updatemember.do")) {
			// 회원 정보 수정 버튼(페이지에서 바로 수정)
			forward = new UpdateMemberAction().execute(request, response);
		} else if (command.equals("/deletemember.do")) {
			// 회원 정보 삭제 버튼(페이지에서 바로 삭제)
			forward = new DeleteMemberAction().execute(request, response);
		}
		// ===============================================================================
		// =================================[구독]=========================================

		else if (command.equals("/subscription.do")) {
			// 구독 상품 페이지(상단 고정바에서 구독 클릭)
			forward = new SubscriptionAction().execute(request, response);
		}else if (command.equals("/subscriptionupdate.do")) {
			// 구독 상품 페이지(상단 고정바에서 구독 클릭)
			forward = new SubscriptionUpdateAction().execute(request, response);
		}
		// ===============================================================================

		// =================================[스토어]=========================================

		else if (command.equals("/store.do")) {
			// 스토어 페이지(상단 고정바에서 스토어 클릭)
			forward = new StoreAction().execute(request, response);
		} else if (command.equals("/detail.do")) {
			// 상품 상세 페이지(스토어 페이지에서 상품 이미지 클릭)
			forward = new DetailAction().execute(request, response);
		} else if (command.equals("/buyPage.do")) {
			// 상품 구매 페이지(상품 상세 페이지에서 구매 클릭)
			forward = new BuyPageAction().execute(request, response);
		} else if (command.equals("/success.do")) {
			// 상품 구매 성공
			forward = new SuccessAction().execute(request, response);
		} else if (command.equals("/fail.do")) {
			// 상품 구매 실패
			forward = new FailAction().execute(request, response);
		}
		// ===============================================================================
		// =================================[검색]=========================================
		else if (command.equals("/searchPage.do")) {
			// 상품 검색 페이지(검색 페이지에서 검색 버튼 클릭)
			forward = new SearchPageAction().execute(request, response);
		} else if (command.equals("/search.do")) {
			// 상품 검색 완료 & 상품 필터 검색 페이지(필터 검색에서 검색된 값 & 검색에서 넘어온 값 보여주는 페이지)
			forward = new SearchAction().execute(request, response);
		}
		// ===============================================================================
		// =================================[장바구니]=======================================
		else if (command.equals("/cart.do")) {
			// 상품 검색 페이지(검색 페이지에서 검색 버튼 클릭)
			forward = new CartPageAction().execute(request, response);
		} else if (command.equals("/cartadd.do")) {
			// 상품 검색 완료 & 상품 필터 검색 페이지(필터 검색에서 검색된 값 & 검색에서 넘어온 값 보여주는 페이지)
			forward = new CartAddAction().execute(request, response);
		}
		
		// ===============================================================================


		// 3. 사용자에게 응답. View로 이동
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			response.sendRedirect("goback.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

}
