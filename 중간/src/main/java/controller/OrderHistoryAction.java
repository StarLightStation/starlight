package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OrderSet;
import model.OrderSetDAO;
import model.OrderVO;

public class OrderHistoryAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); //객체 생성(경로 & 리다이렉트 유무)
		//→페이지 이동만 하므로 객체를 바로 초기화
		//넘겨줄 값 : 없음
		//이동할 페이지 : orderHistory.jsp
		
		
		//1. model 생성(구매정보를 가지고있는 VO, DAO)
		OrderVO oVO = new OrderVO();//구매 목록 객체
		//2. selectAll로 정보 가져오기
		HttpSession session = request.getSession();//세션 객체 생생(로그인 정보를 세션으로 관리하기 위해서 생성)
		String mid = (String) session.getAttribute("mid");//세션에 저장된 mid값 형변환
		
		System.out.println("log : OrderHistoryAction : mid : " + (String) session.getAttribute("mid"));
		
		//board set
		//구매목록 세팅
		oVO.setmID(mid);//세션에서 가져온 아이디 값 세팅
		//구맥 상세 정보 세팅
		//3. 가져온 정보를 세팅
		OrderSetDAO osDAO = new OrderSetDAO();//set 기능
		ArrayList<OrderSet> odatas = osDAO.selectAll(oVO);//사용자가 구매한 목록 + 상세 상품 정보들 가져오기
		request.setAttribute("odatas", odatas);
		
		System.out.println("log : OrderHistoryAction : odatas : " + odatas);
		
		forward.setRedirect(false);//리다이렉트 : false (현재 사용자의 구매정보)
		forward.setPath("orderHistory.jsp");//경로 : 주문 내역 페이지 
		return forward;
	}

}
