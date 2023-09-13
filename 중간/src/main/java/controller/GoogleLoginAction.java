package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class GoogleLoginAction implements Action {
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      ActionForward forward = null;// 객체 선언
      // →로그인에 성공했을 때 객체 생성(로그인 실패시에 null값 반환)
      // 넘겨줄 값 : 없음(로그인 정보 세션으로 관리)
      // 이동할 페이지 : main.jsp

      // Google API 사용(여기서 아이디 정보값 가져와서 비교)
      HttpSession session = request.getSession();// 세션 객체 생성
      // API
      MemberVO mVO = new MemberVO(); // mVO 객체 생성(멤버 정보)
      MemberDAO mDAO = new MemberDAO();// mDAO 객체 생성(로그인)

      String mID = request.getParameter("googleEmail");
      String mPW = request.getParameter("googleEmail");
      String mName = request.getParameter("googleName");
      
      System.out.println(mID);
      System.out.println(mPW);
      System.out.println(mName);
      // Google API에서 가져온 정보를 세팅
      mVO.setmID(mID);// 사용자가 입력한 아이디 값(구글 이메일)
      mVO.setmPW(mPW);// 사용자가 입력한 비밀번호 값(구글 아이디 값)

      mVO.setSk("SIGNIN");
      mVO = mDAO.selectOne(mVO);// 로그인(아이디&비밀번호가 DB에 존재하는지 확인)
      System.out.println("로그인 확인" + mVO);
      if (mVO == null) {// 존재하지 않는다면
         System.out.println("존재하지 않음");
         mVO = new MemberVO();// 새로운 객체 생성
         mVO.setmID(mID);// 사용자가 입력한 아이디 값(구글 이메일)
         mVO.setmPW(mPW);// 사용자가 입력한 비밀번호 값(구글 아이디 값)
         // 회원가입 진행
         mVO.setmName(mName);// 사용자가 입력한 이름 값(구글 이름)
         mVO.setmPhone(mID + 1);// 사용자가 입력한 핸드폰 번호 값(빈값으로 유지)
         mVO.setSignUpKind("구글");// 구글 가입인지 이메일 가입인지
         boolean flag = mDAO.insert(mVO);// DB에 새로운 정보 삽입 성공 유무(회원가입 성공 유무)
         System.out.println("flag :" +flag);
         if (flag) {// 회원가입에 성공했다면(DB에 없다면&입력한 정보들이 제대로 들어갔다면)

            session.setAttribute("mid", mID);// 로그인 아이디 정보를 세션으로 세팅
            session.setAttribute("mname", mName);// 로그인 닉네임 정보를 세션으로 세팅
            forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
            forward.setPath("main.do");// 메인페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
            forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
         }

      } else {// 존재한다면
         session.setAttribute("mid", mID);// 로그인 아이디 정보를 세션으로 세팅
         session.setAttribute("mname", mName);// 로그인 닉네임 정보를 세션으로 세팅
         forward = new ActionForward();// 객체 생성(경로 & 리다이렉트 유무)
         forward.setPath("main.do");// 메인페이지로 이동하기 위한 url 세팅 → 알림창 띄우기 위해서 추후에 페이지 바꿀 예정
         forward.setRedirect(true);// 리다이렉트 : true (넘겨줄 값 없음)
      }

      return forward;
   }
}