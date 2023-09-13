package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class MemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward(); // 객체 생성(경로 & 리다이렉트 유무)
		// →페이지 이동만 하므로 객체를 바로 초기화
		// 넘겨줄 값 : 회원 정보
		// 이동할 페이지 : member.jsp

		// SK 세팅 필요
		MemberVO mVO = new MemberVO(); // mVO 객체 생성(멤버 정보)
		MemberDAO mDAO = new MemberDAO();// mDAO 객체 생성(회원 전체 정보)

		HttpSession session = request.getSession();// 세션을 사용하기 위한 세션 객체 생성

		mVO.setmID((String) session.getAttribute("mid"));// 세션에 저장된 mid값 가져오기(String으로 형변환)
		mVO.setSk("INFO");	//	SK set.
		mVO = mDAO.selectOne(mVO);// 회원 정보 저장 객체

		request.setAttribute("data", mVO);// 회원 정보 세팅
		forward.setRedirect(false);// 리다이렉트 : false(넘겨줄 값 : 현재 로그인한 사용자의 전체 정보)
		forward.setPath("member.jsp");// 회원 정보 페이지로 이동하기 위한 url 세팅
		return forward;
	}

}
