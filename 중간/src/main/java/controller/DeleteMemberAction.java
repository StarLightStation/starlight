package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class DeleteMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;// 객체 선언
		// →회원 정보 삭제에 성공했을 때 객체 생성(회원 정보 삭제 실패시에 null값 반환)
		// 넘겨줄 값 : 없음
		// 이동할 페이지 : 메인 페이지(로그아웃 + 사용자 이름 탈퇴한 사용자로 바꾸기 → 리뷰는 남기기 위해서)

		MemberVO mVO = new MemberVO();
		MemberDAO mDAO = new MemberDAO();

		HttpSession session = request.getSession();// 세션을 사용하기 위한 세션 객체 생성

		mVO.setmID((String) session.getAttribute("mid"));// 세션에 저장된 mid값 가져오기(String으로 형변환)

		boolean flag = mDAO.delete(mVO);// DB에 삭제 성공 유무(회원 정보 삭제 성공 유무)

		if (flag) {// 회원 정보 삭제에 성공했다면(DB에 있다면&입력한 정보들이 제대로 들어갔다면)
			// 여기서 삭제된 사용자의 리뷰 이름을 "탈퇴한 사용자"로 변경
			forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
			forward.setPath("logout.do");// 메인 페이지로 이동하기 위한 url 세팅
			forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
		}

		return forward;
	}

}
