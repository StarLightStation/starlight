package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;
import model.BoardVO;
import model.ProductDAO;
import model.ProductVO;

public class DetailAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      ActionForward forward = new ActionForward(); // 객체 생성(경로 & 리다이렉트 유무)
      // →페이지 이동만 하므로 객체를 바로 초기화
      //넘겨줄 값 : 상품 하나의 정보
      //이동할 페이지 : detail.jsp
      
      
      ProductVO pVO = new ProductVO();//상품 정보가 있는 객체
      ProductDAO pDAO = new ProductDAO();
      
      BoardVO bVO = new BoardVO();
      BoardDAO bDAO = new BoardDAO();
      //1.사용자로부터 클릭한 상품의 상품 번호를 가져옴
      int pnum = Integer.parseInt(request.getParameter("pnum"));//상품의 PK번호 가져오기
      
      //2.selectOne으로 상품의 정보를 가져옴
      //3.이때 sk 키워드 필요
      pVO.setpNum(pnum);//객체에 사용자가 클릭한 상품 번호 세팅
      pVO = pDAO.selectOne(pVO);//상품 하나의 정보 가져오기
      bVO.setpNum(pVO.getpNum());//상품의 번호 세팅
      bVO.setSk("PRODUCT");
      ArrayList<BoardVO> bdatas = bDAO.selectAll(bVO);//상품의 리뷰 정보들 가져오기
      
      //4.selectOne으로 뽑아온 값을 View에 전달(세팅 필요)
      // 상품 정보들 가져오기
      request.setAttribute("pdata", pVO);//상품 하나의 정보 세팅
      if(bdatas != null) {
         request.setAttribute("bdatas", bdatas);//상품의 리뷰 정보들 세팅
      }
      
      forward.setRedirect(false);// 리다이렉트 : false(넘겨줄 값 : 사용자가 클릭한 상품의 상세 정보)
      forward.setPath("detail.jsp");// 경로 : 로그인 페이지
      
      return forward;
   }

}