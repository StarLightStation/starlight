package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SubscriptionDAO;
import model.SubscriptionVO;

public class SubscriptionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
		// 넘겨줄 값 : 구독 상품 정보
		// 이동할 페이지 : subscription.jsp

		// 1.구독 상품 정보 담겨있는 model 객체 생성(DAO, VO)
		SubscriptionDAO subDAO = new SubscriptionDAO();
		SubscriptionVO subVO = new SubscriptionVO();
		// 2.DB 에 접근해서 구독 정보 가져오기
		// 3.가져온 정보 set해서 값 넘겨주기
		// 4.view에서 넘어오는 값 설정
		String category = request.getParameter("category");//view에서 받아올 구독상품의 카테고리
		if (category == null || category.equals("1번째 상품")) {//처음 화면 들어가거나 1번 선택
			subVO.setSubNum(1);//구독 PK 세팅
		} else {
			if (category.equals("두번째")) {
				subVO.setSubNum(2);
			} else if (category.equals("세번째")) {
				subVO.setSubNum(3);
			}
		}

		subVO = subDAO.selectOne(subVO);// 구독 상품 정보 가져오기

		request.setAttribute("data", subVO);// View에게 보내줄 구독 정보

		forward.setPath("subscription.jsp");// 구독페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
		forward.setRedirect(false);// 리다이렉트 : false (구독 상품 정보)
		return forward;
	}

}
