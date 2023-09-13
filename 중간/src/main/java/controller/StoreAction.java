package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;
import model.ProductVO;

public class StoreAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
		// 넘겨줄 값 : 상품 정보들
		// 이동할 페이지 : store.jsp
		
		// 1.스토어 상품 정보 담겨있는 model 객체 생성(DAO, VO)
		ProductVO pVO = new ProductVO();
		ProductDAO pDAO = new ProductDAO();
		// 2.DB 에 접근해서 상품 정보 가져오기
		ArrayList<ProductVO> pdatas= pDAO.selectAll(pVO);// 상품 정보들 가져오기
		// 3.가져온 정보 set해서 값 넘겨주기
		request.setAttribute("datas", pdatas);//View에게 보내줄 구독 정보

		forward.setPath("store.jsp");// 스토어 페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
		forward.setRedirect(false);// 리다이렉트 : false (구독 상품 정보)
		System.out.println(pdatas);
		
		return forward;
	}

}
