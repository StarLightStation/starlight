package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class SignupAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;// 객체 선언
		// →회원가입에 성공했을 때 객체 생성(회원가입 실패시에 null값 반환)
		// 넘겨줄 값 : 없음
		// 이동할 페이지 : main.jsp
		
		MemberVO mVO = new MemberVO(); //mVO 객체 생성(멤버 정보)
		MemberDAO mDAO = new MemberDAO();//mDAO 객체 생성(회원가입)
		
		mVO.setSk("SIGNUP");//회원가입 중복검사
		mVO.setmID(request.getParameter("mid"));//사용자가 입력한 아이디 값
		mVO.setmPhone(request.getParameter("mphone"));//사용자가 입력한 전화번호 값
		mVO = mDAO.selectOne(mVO);//아이디가 중복되는지 확인
		//null인경우 : 중복되는 아이디 값이 없다.
		//null이 아닌경우 : 중복되는 아이디가 있다. 
		
		if(mVO == null) {//중복되는 아이디가 없다면
			mVO=new MemberVO();//null예외 발생하니까 새로운 객체로 생성
			//회원가입 진행
			mVO.setmID(request.getParameter("mid"));//사용자가 입력한 아이디 값
			mVO.setmPW(request.getParameter("mpw"));//사용자가 입력한 비밀번호 값
			mVO.setmName(request.getParameter("mname"));//사용자가 입력한 이름 값
			mVO.setmPhone(request.getParameter("mphone"));//사용자가 입력한 핸드폰 번호 값
			mVO.setSignUpKind("이메일");//구글 가입인지 이메일 가입인지
			boolean flag = mDAO.insert(mVO);//DB에 새로운 정보 삽입 성공 유무(회원가입 성공 유무)
			if(flag) {//회원가입에 성공했다면(DB에 없다면&입력한 정보들이 제대로 들어갔다면)
				forward = new ActionForward();//객체 생성(경로 & 리다이렉트 유무)
				forward.setPath("main.do");//메인페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
				forward.setRedirect(true);//리다이렉트 : true (넘겨줄 값 없음)
			}
		}else { // 중복되는 아이디가 있다면
			//다시 회원가입 페이지로
			forward = new ActionForward();//객체 생성(경로 & 리다이렉트 유무)
			forward.setPath("signupPage.do");//회원가입 페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
			forward.setRedirect(true);//리다이렉트 : true (넘겨줄 값 없음)
		}
		
		
		return forward;
	}

}
