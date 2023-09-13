package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.OrderDAO;
import model.OrderSet;
import model.OrderSetDAO;
import model.OrderVO;
import model.OrderdetailDAO;
import model.OrderdetailVO;

public class WriteReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); // 객체 생성(경로 & 리다이렉트 유무)
		// →페이지 이동만 하므로 객체를 바로 초기화
		// 넘겨줄 값 : 구매한 상품에 대한 정보(리뷰 작성x)
		// 이동할 페이지 : writeReview.jsp

		// 1. model(VO, DAO) 작성
		//	OrderVO oVO = new OrderVO();// 구매 목록 객체
		OrderdetailVO oVO = new OrderdetailVO();

		// 2. 세션에 저장된 사용자 로그인 정보 가져오기
		HttpSession session = request.getSession(); // 세션 객체 생성 (로그인 정보를 세션으로 관리하기 위해서 생성)
		String mid = (String) session.getAttribute("mid"); // 세션에 저장된 아이디 값 가져오기

		// 3. DAO에서 값 가져오기
		// board set
		// 구매목록 세팅
		oVO.setmID(mid);// 세션에서 가져온 아이디 값 세팅
		oVO.setSk("REVIEW");
		
		// 구매 상세 정보 세팅
		// 3. 가져온 정보를 세팅
		OrderdetailDAO oDAO = new OrderdetailDAO();
		
		ArrayList<OrderdetailVO> oddatas = oDAO.selectAll(oVO);// 사용자가 구매한 목록 + 상세 상품 정보들 가져오기

		// 4. 값들 세팅해주기
		request.setAttribute("oddatas", oddatas);
		// 5. 페이지 이동

		forward.setRedirect(false);// 리다이렉트 : false(넘겨줄 값 : 리뷰를 작성하지 않은 상품)
		forward.setPath("writeReview.jsp");// 경로 : 로그인 페이지
		return forward;

	}

}
