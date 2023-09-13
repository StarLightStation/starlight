package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;
import model.SubscriptionDAO;
import model.SubscriptionVO;

public class SubscriptionUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("log : SubscriptionUpdateAction : execute");

		ActionForward forward = null;

		HttpSession session = request.getSession();//세션 객체 생성
		String mid = (String)session.getAttribute("mid");//사용자 아이디 가져오기
		//여기서 구독되어있는지 확인
		//안되어있다면 진행
		//→ 구독 정보를 가져와서 결제 페이지로 넘겨줘야함

		//넘겨줄값: 없음( 구독 상품 정보 (세션) 상품을 사는 사람(세션))
		//되어있다면 null

		MemberVO mVO  = new MemberVO();//사용자 객체 생성
		MemberDAO mDAO  = new MemberDAO();

		SubscriptionVO subVO = new SubscriptionVO();//구독 정보 객체 생성
		SubscriptionDAO subDAO = new SubscriptionDAO();

		int subNum =Integer.parseInt(request.getParameter("subNum"));//사용자가 선택한 구독 상품 번호

		System.out.println("log : SubscriptionUpdateAction : execute : "
				+ Integer.parseInt(request.getParameter("subNum")));

		mVO.setmID(mid);
		mVO.setSk("INFO");
		mVO = mDAO.selectOne(mVO);

		if(mVO.getSubscription() == 0) {//구독을 안했다면
			forward = new ActionForward();

			subVO.setSubNum(subNum);//구독 번호 세팅
			subVO = subDAO.selectOne(subVO);//구독 정보 가져오기
			session.setAttribute("subNum", subVO.getSubNum());//구독 상품 정보를 세션으로 관리

			request.setAttribute("mname", mVO.getmName());
			request.setAttribute("mphone", mVO.getmPhone());


			forward.setRedirect(false);// 리다이렉트 : true(넘겨줄 값 : 사용자 정보-번호,이름-)
			forward.setPath("buyPage.do");// 경로 : 로그인 페이지

			// forward = new ActionForward();

		}      
		return forward;
	}

}
