package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class UpdateMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		ActionForward forward = null;// 객체 선언
		// →회원 정보 수정에 성공했을 때 객체 생성(회원 정보 수정 실패시에 null값 반환)
		// 넘겨줄 값 : 없음(로그아웃 되기 때문에)
		// 이동할 페이지 : 메인 페이지(로그아웃)

		MemberVO mVO = new MemberVO();
		MemberDAO mDAO = new MemberDAO();

		HttpSession session = request.getSession();// 세션을 사용하기 위한 세션 객체 생성
		
		//여기서 비밀번호 두개넘어오는거 비교?
		mVO.setmID((String) session.getAttribute("mid"));// 세션에 저장된 mid값 가져오기(String으로 형변환)
		mVO.setTmpMname(request.getParameter("mname"));
		mVO.setTmpMpw(request.getParameter("mpw")); 
		mVO.setTmpMphone(request.getParameter("mphone"));
		mVO.setSk("CHANGE");
		
		boolean flag = mDAO.update(mVO);// DB에 수정 성공 유무(회원 정보 수정 성공 유무)
		if (flag) {// 회원 정보 수정에 성공했다면(DB에 있다면&입력한 정보들이 제대로 들어갔다면)
			forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
			forward.setPath("logout.do");// 메인 페이지로 이동하기 위한 url 세팅
			forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
		}
		return forward;
	}

}
