package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;// 객체 선언
		// →로그아웃에 성공했을 때 객체 생성(로그아웃 실패시에 null값 반환)
		// 넘겨줄 값 : 없음
		// 이동할 페이지 : main.jsp
		HttpSession session=request.getSession(); // 세션을 사용하기 위한 세션 객체 생성
		session.removeAttribute("mid");//현재 세션에 저장되어있는 정보 삭제(로그아웃)
		session.removeAttribute("cart");//현재 세션에 저장되어있는 정보 삭제(장바구니)
		
		forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
        forward.setPath("main.do");//메인페이지로 이동하기 위한 url 세팅
        forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
        
		return forward;
	} 

}
