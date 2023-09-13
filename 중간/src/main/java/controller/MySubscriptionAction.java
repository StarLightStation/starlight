package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SubsinfoDAO;
import model.SubsinfoVO;

public class MySubscriptionAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); //객체 생성(경로 & 리다이렉트 유무)
		//→페이지 이동만 하므로 객체를 바로 초기화
		//넘겨줄 값 : 없음
		//이동할 페이지 : mysubscription.jsp
		
		//1. model 생성(구독정보를 가지고있는 VO, DAO)
		SubsinfoVO subinfoVO = new SubsinfoVO();//사용자가 어떤 상품을 구독했는지 알려주는 객체
		SubsinfoDAO subinfoDAO = new SubsinfoDAO();
		
//		SubscriptionDAO subDAO = new SubscriptionDAO();
//		SubscriptionVO subVO = new SubscriptionVO();//구독한 상품의 정보가 들어있는 객체
		
		//2. selectOne으로 정보 가져오기(구독은 하나만 가능하기 때문에)
		HttpSession session = request.getSession();//세션 객체 생생(로그인 정보를 세션으로 관리하기 위해서 생성)
		String mid = (String) session.getAttribute("mid");//세션에 저장된 mid값 형변환
		//사용자가 가지고 있는 구독 정보 가져오기
		subinfoVO.setmID(mid);
		subinfoVO = subinfoDAO.selectOne(subinfoVO);
		//가져온 구독에 대한 상세 정보 가져오기
		
//		subVO.setSubNum(subinfoVO.getSubNum());
//		subVO = subDAO.selectOne(subVO);//사용자가 구독한 정보 가져오기
		
		//3. 가져온 정보를 세팅
		request.setAttribute("data", subinfoVO);
		
		forward.setRedirect(false);//리다이렉트 : false (현재 사용자의 구독정보)
		forward.setPath("mysubscription.jsp");//경로 : 구독 내역 페이지 
		return forward;
	}

}
