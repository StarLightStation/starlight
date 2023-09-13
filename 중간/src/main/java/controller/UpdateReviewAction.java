package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BoardDAO;
import model.BoardVO;

public class UpdateReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); // 객체 생성(경로 & 리다이렉트 유무)
		// →페이지 이동만 하므로 객체를 바로 초기화
		// 넘겨줄 값 : 사용자가 작성한 리뷰
		// 이동할 페이지 : updateReview.jsp

		// 1. model(VO, DAO) 작성
		BoardVO bVO = new BoardVO();
		BoardDAO bDAO = new BoardDAO();
		// 2. 세션에 저장된 사용자 로그인 정보 가져오기
		HttpSession session = request.getSession(); // 세션 객체 생성 (로그인 정보를 세션으로 관리하기 위해서 생성)
		String mid = (String) session.getAttribute("mid"); // 세션에 저장된 아이디 값 가져오기
		
		// 3. DAO에서 작성되어있는 리뷰 정보들 가져오기
		bVO.setSk("MYPAGE");
		bVO.setmID(mid);
		
		ArrayList<BoardVO> bdatas = bDAO.selectAll(bVO);//사용자가 쓴 리뷰 내용들 가져오기
		// 4. 값들 세팅해주기
		request.setAttribute("bdatas", bdatas);
		
		// 5. 페이지 이동
		forward.setRedirect(false);// 리다이렉트 : false(넘겨줄 값 : 사용자가 작성한 리뷰)
		forward.setPath("updateReview.jsp");// 경로 : 로그인 페이지
		return forward;
	}

}
