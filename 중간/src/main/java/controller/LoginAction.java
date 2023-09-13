package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;// 객체 선언
		// →로그인에 성공했을 때 객체 생성(로그인 실패시에 null값 반환)
		// 넘겨줄 값 : 없음(로그인 정보 세션으로 관리)
		// 이동할 페이지 : main.jsp
		
		MemberVO mVO = new MemberVO(); //mVO 객체 생성(멤버 정보)
		MemberDAO mDAO = new MemberDAO();//mDAO 객체 생성(로그인)
		
		//SK(서치 키워드 세팅 필요)
		mVO.setSk("SIGNIN");//검색 키워드 세팅
		mVO.setmID(request.getParameter("mid"));//사용자가 입력한 아이디 값
		mVO.setmPW(request.getParameter("mpw"));//사용자가 입력한 비밀번호 값
		
		mVO = mDAO.selectOne(mVO);//로그인(아이디&비밀번호가 DB에 존재하는지 확인)
		
		if(mVO != null) {//존재한다면
			HttpSession session=request.getSession();//세션 객체 생생(로그인 정보를 세션으로 관리하기 위해서 생성)
			session.setAttribute("mid", mVO.getmID());//로그인 아이디 정보를 세션으로 세팅
			session.setAttribute("mname", mVO.getmName());//로그인 닉네임 정보를 세션으로 세팅

			forward = new ActionForward();//객체 생성(경로 & 리다이렉트 유무)
			forward.setPath("main.do");//메인페이지로 이동하기 위한 url 세팅
			forward.setRedirect(true);//리다이렉트 : true (세션으로 로그인 정보 관리)
		}
		
		return forward;
	}

}
