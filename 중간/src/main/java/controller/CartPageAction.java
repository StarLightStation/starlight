package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); //객체 생성(경로 & 리다이렉트 유무)
		//→페이지 이동만 하므로 객체를 바로 초기화
		//넘겨줄 값 : 없음
		//이동할 페이지 : cart.jsp
		//세션에 저장된 장바구니 값 가져오기
		HttpSession session=request.getSession();//세션 객체 생생(로그인 정보를 세션으로 관리하기 위해서 생성)
		session.getAttribute("cart");
		
		
		forward.setRedirect(true);//리다이렉트 : true(넘겨줄 값 없음)
		forward.setPath("cart.jsp");//경로 : 로그인 페이지 
		return forward;
	}
	
}
