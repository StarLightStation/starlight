package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;
import model.ProductVO;

public class SearchPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		// →페이지 이동만 하므로 객체를 바로 초기화
		// 넘겨줄 값 : 검색한 이름이 포함된 상품들
		// 이동할 페이지 : search.jsp
		// 이곳은 비동기 할것
		// 값을 selectAll로 다 가져오기
		ProductVO pVO  = new ProductVO();
		ProductDAO pDAO = new ProductDAO();
		
		
		String recent = request.getParameter("pname");
		pVO.setpName(recent);//검색한 이름 
		pDAO.selectAll(pVO);//이름이 포함된 상품들 가져오기
		
		// 최근 검색한 검색어는 application으로 저장
		ServletContext application = request.getServletContext();//어플리케이션 객체 생성
		application.setAttribute("recent", recent);//최근 검색어를 어플리케이션으로 세팅
		
		forward.setRedirect(false);// 리다이렉트 : false(넘겨줄 값 : 검색한 값이 포함된 상품들)
		forward.setPath("search.jsp");// 경로 : 검색 페이지
		return forward;
	}

}
