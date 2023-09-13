package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;
import model.OrderdetailDAO;
import model.OrderdetailVO;

public class WriteReviewOKAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      ActionForward forward = null;// 객체 선언
      // →리뷰 작성에 성공했을 때 객체 생성(리뷰 작성 실패시에 null값 반환)
      // 넘겨줄 값 : 사용자가 작성한 리뷰 정보(사용자 아이디, 리뷰 내용, 별점, 상품 정보...)
      // 이동할 페이지 : 리뷰 수정 페이지(여기에 작성한 리뷰들이 출력되기 때문에)
      
      //1. Model 객체 생성(리뷰 VO, 리뷰 DAO)
      BoardVO bVO = new BoardVO();
      BoardDAO bDAO = new BoardDAO();
      OrderdetailVO odVO = new OrderdetailVO();
      OrderdetailDAO odDAO = new OrderdetailDAO();
      
      
      //2. 사용자가 입력한 값 VO에 세팅
      bVO.setmID(request.getParameter("mid"));
      bVO.setmName(request.getParameter("mname"));
      bVO.setpNum(Integer.parseInt(request.getParameter("pnum")));
      bVO.setbContent(request.getParameter("bcontent"));
      bVO.setbStar(Double.parseDouble(request.getParameter("bstar")));
      odVO.setOdNum(Integer.parseInt(request.getParameter("odnum")));
      
      System.out.println(request.getParameter("mid"));
      System.out.println(request.getParameter("mname"));
      System.out.println(request.getParameter("pnum"));
      System.out.println(request.getParameter("bcontent"));
      System.out.println(request.getParameter("bstar"));
      System.out.println("odnum : " + request.getParameter("odnum"));
      //3. 세팅된 값 insert
      boolean flag = bDAO.insert(bVO);//DB에 새로운 정보 삽입 성공 유무(리뷰작성 성공 유무)
      
      
      
      System.out.println(flag);
      
      if(flag) {//리뷰 작성에 성공했다면(DB에 없다면&입력한 정보들이 제대로 들어갔다면)
         boolean flag2 = odDAO.update(odVO);
         System.out.println("odDAO.update : " + flag2);
         forward = new ActionForward();//객체 생성(경로 & 리다이렉트 유무)
         forward.setPath("updatereview.do");//리뷰 수정 페이지로 이동하기 위한 url 세팅 
         forward.setRedirect(true);//리다이렉트 : true (넘겨줄 값 없음)
      }
      return forward;
   }

}
