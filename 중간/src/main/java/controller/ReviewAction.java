package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); //객체 생성(경로 & 리다이렉트 유무)
		//→페이지 이동만 하므로 객체를 바로 초기화
		//넘겨줄 값 : 없음
		//이동할 페이지 : review.jsp
		
		
		forward.setRedirect(false);//리다이렉트 : false (현재 사용자의 구매정보)
		forward.setPath("writeReview.jsp");//경로 : 리뷰 페이지 
		return forward;
	}

}
