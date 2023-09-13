package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class SearchIDAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;// 객체 선언
		// →아이디 찾기에 성공했을 때 객체 생성(아이디 찾기 실패시에 null값 반환)
		// 넘겨줄 값 : 찾은 아이디, 전화번호
		// 이동할 페이지 : 문자 보내기
		// 찾은 아이디 값을 문자로 전송
		
		MemberVO mVO = new MemberVO(); //mVO 객체 생성(멤버 정보)
		MemberDAO mDAO = new MemberDAO();//mDAO 객체 생성(로그인)
		
		//SK(서치 키워드 세팅 필요)
		mVO.setSk("FINDID");//아이디 찾기를 위한 sk 세팅
		mVO.setmPhone(request.getParameter("mphone"));//사용자가 입력한 전화번호 값
		//→전화번호를 통해서 아이디 찾기
		mVO = mDAO.selectOne(mVO);//아이디 찾기(일치하는 전화번호를 검색)
		
		if(mVO != null) {//존재한다면
			//문자 API 사용
			request.setAttribute("mphone", mVO.getmPhone());//문자 보내기에 넘겨줄 전화번호 값
			request.setAttribute("mid", mVO.getmID());//문자 보내기에 넘겨줄 찾은 아이디 값
			forward = new ActionForward();//객체 생성(경로 & 리다이렉트 유무)
			forward.setPath("send.do");//문자 보내기로 이동하기 위한 url 세팅 → 서블릿 사용
			forward.setRedirect(false);//리다이렉트 : false (전화번호 넘겨주기)
		}
		
		return forward;
	}

}
