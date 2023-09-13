package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;

public class DeleteReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;// 객체 선언
		// →리뷰 삭제에 성공했을 때 객체 생성(리뷰 삭제 실패시에 null값 반환)
		// 넘겨줄 값 : 없음 (리뷰 수정 페이지에서 DB에서 값을 가져오기 때문에)
		// 이동할 페이지 : 리뷰 수정 페이지(삭제된 리뷰 확인)
		
		// 1. Model 객체 생성(리뷰 VO, 리뷰 DAO)
		BoardVO bVO = new BoardVO();
		BoardDAO bDAO = new BoardDAO();
		// 2. View에서 넘어오는 리뷰 글 번호 세팅
		bVO.setbNum(Integer.parseInt(request.getParameter("bnum")));
		// 3. 세팅된 값 delete
		boolean flag = bDAO.delete(bVO);// DB에 삭제 성공 유무(리뷰삭제 성공 유무)

		if (flag) {// 리뷰 삭제에 성공했다면(DB에 있다면&입력한 정보들이 제대로 들어갔다면)
			forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
			forward.setPath("updatereview.do");// 리뷰 수정 페이지로 이동하기 위한 url 세팅
			forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
		}
		return forward;
	}

}
