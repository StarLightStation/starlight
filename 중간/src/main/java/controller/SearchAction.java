package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;
import model.ProductVO;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); // 객체 생성(경로 & 리다이렉트 유무)
		// →페이지 이동만 하므로 객체를 바로 초기화
		// 넘겨줄 값 : 검색한 단어가 포함된 상품들 & 카테고리 값이 있는 상품들
		// 이동할 페이지 : searchFilter.jsp

		// 1.사용자로부터 입력한 값을 가져옴(검색한 단어 & 카테고리)
		// 2.selectAll으로 상품의 정보를 가져옴
		// 3.이때 sk 키워드 필요
		// 4.selectAll으로 뽑아온 값을 View에 전달(세팅 필요)

		System.out.println(request.getParameter("pcategory"));
		System.out.println(request.getParameter("psweet"));
		System.out.println(request.getParameter("psour"));
		System.out.println(request.getParameter("psparkle"));

		ProductVO pVO = new ProductVO(); // 상품 객체 생성
		ProductDAO pDAO = new ProductDAO();// 상품 정보 가져오기
		double alcohol = 0.0;
		if (request.getParameter("palcohol") != null) {
			alcohol = Double.parseDouble(request.getParameter("palcohol"));
		}

		String name = request.getParameter("pname");
		String category = request.getParameter("pcategory");

		String sweet = request.getParameter("psweet");
		String sour = request.getParameter("psour");
		String sparkle = request.getParameter("psparkle");

		// 사용자가 입력한 값들 가져오기 → 수정된거 보고 할 예정
		pVO.setpName(name);// 이름
		pVO.setpCategory(category);// 카테고리
		pVO.setpAlcohol(alcohol);// 알코올 농도
		pVO.setpSweet(sweet);// 당도
		pVO.setpSour(sour);// 신도
		pVO.setpSparkle(sparkle);// 탄산

		// 그냥 selectAll()로 사용
		ArrayList<ProductVO> pdata = pDAO.selectAll(pVO);// 상품 정보 가져오기
		
		System.out.println(pdata);
		request.setAttribute("datas", pdata);// View에서 사용가능하도록 가져온 상품 정보 값 세팅

		forward.setRedirect(false);// 리다이렉트 : false(넘겨줄 값 : 사용자가 검색한 상품들 정보)
		forward.setPath("searchFilter.jsp");// 경로 : 검색 필터 페이지

		return forward;
	}

}
