package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class GoogleSignupAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      ActionForward forward = null;// 객체 선언
      // →로그인에 성공했을 때 객체 생성(회원가입 실패시에 null값 반환)
      // 넘겨줄 값 : 없음
      // 이동할 페이지 : main.jsp

      // Google API 사용(가져온 값을 DB에 넣어줄 예정)
      // API
      MemberVO mVO = new MemberVO(); // mVO 객체 생성(멤버 정보)
      MemberDAO mDAO = new MemberDAO();// mDAO 객체 생성(회원가입)

      // Google API에서 가져온 정보를 세팅
      // 중복 검사
      mVO.setmID(request.getParameter("googleEmail"));// 사용자가 입력한 아이디 값(구글 이메일)
      mVO.setmPhone(request.getParameter("googleEmail") + 1);// 사용자가 입력한 핸드폰 번호 값(빈값으로 유지)
      // ◆그외 필요한 값 추가
      mVO.setSk("SIGNUP");
      mVO = mDAO.selectOne(mVO);
      System.out.println(mVO);
      if (mVO == null) {// 중복된 회원이 없다면
         mVO  = new MemberVO();//새로운 객체 생성
         mVO.setmID(request.getParameter("googleEmail"));// 사용자가 입력한 아이디 값(구글 이메일)
         mVO.setmPW(request.getParameter("googleEmail"));// 사용자가 입력한 비밀번호 값(구글 아이디 값)
         // 회원가입 진행
         mVO.setmName(request.getParameter("googleName"));// 사용자가 입력한 이름 값(구글 이름)
         mVO.setmPhone(request.getParameter("googleEmail") + 1);// 사용자가 입력한 핸드폰 번호 값(빈값으로 유지)
         mVO.setSignUpKind("구글");// 구글 가입인지 이메일 가입인지
         boolean flag = mDAO.insert(mVO);// DB에 새로운 정보 삽입 성공 유무(회원가입 성공 유무)
         if (flag) {// 회원가입에 성공했다면(DB에 없다면&입력한 정보들이 제대로 들어갔다면)
            forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
            forward.setPath("main.do");// 메인페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
            forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
         }
      } else { // 중복되는 아이디가 있다면
         // 다시 회원가입 페이지로
         forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
         forward.setPath("signupPage.do");// 회원가입 페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
         forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
      }

      return forward;
   }

}