package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProductVO;

public class DeleteCartAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
         
      ActionForward forward = null;
      HttpSession session = request.getSession();// 세션 객체 생성
      ArrayList< HashMap<ProductVO, Integer>> cart = new ArrayList< HashMap<ProductVO, Integer>>();//상품 정보들을 담아줄 장바구니
      if (session.getAttribute("cart") == null) {
          session.setAttribute("cart", cart); // YourCartClass는 실제 카트를 나타내는 클래스 이름으로 대체해야 합니다
      }
      HashMap<ProductVO, Integer> hashMap = new HashMap<>();//해쉬 맵 객체 생성
      
      int count = Integer.parseInt(request.getParameter("count"));//사용자가 입력한 장바구니에 담을 갯수
      ProductVO pVO = new ProductVO();//상품 객체 생성
      pVO.setpNum(Integer.parseInt(request.getParameter("pnum")));//상품 번호 세팅
      
      hashMap.put(pVO, count);
      
      for( int i = 0; i<cart.size();i++) {
         if(cart.get(i).containsKey(pVO)) {
            int cartCnt = hashMap.get(pVO); // 해당상품의 장바구니 갯수
            if(count == 0) {//해당 상품이 0이면 삭제라고 판단
               cart.remove(i);
            }else {
               cartCnt = cartCnt-count;//업데이트 된 장바구니 수
               hashMap.put(pVO, cartCnt);//새롭게 저장
               cart.remove(i);
               cart.add(hashMap);
            }
         }
         
      }
      session.setAttribute("cart", cart);
      
      
      
      
      //   session값 (cart) 을 받아옴.
      //   사용자가 상품 개수 줄이거나 삭제 ? 누르면 count 줄이거나 상품을 삭제 해야함.
      //   
      
      
      return forward;
   }

}