package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProductDAO;
import model.ProductVO;

public class CartAddAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // 비동기 처리가 필요한 페이지
      ActionForward forward = new ActionForward();
      HashMap<Integer, ProductVO> hashMap = new HashMap<>();
      System.out.println("pnum" + request.getParameter("pnum"));
      System.out.println("count" + request.getParameter("count"));
      // detail.jsp 페이지에서 실행
      // pdata 라는 상품의 상세 정보들이 담겨져서 브라우저에 있음
      // 이것을 여기에 넘겨줘서
      // 세션에 저장
      // 넘어올때마다 값을 넣어줘야 함
      HttpSession session = request.getSession(); // 세션 객체 생성
//      //상품 정보들 가져오기
      ProductVO pVO = new ProductVO();// 상품 객체 생성
      ProductDAO pDAO = new ProductDAO();
      ArrayList<HashMap<Integer, ProductVO>> cart = new ArrayList<HashMap<Integer, ProductVO>>();// 상품 정보들을 담아줄 장바구니
      if (session.getAttribute("cart") == null) {
         session.setAttribute("cart", cart); // YourCartClass는 실제 카트를 나타내는 클래스 이름으로 대체해야 합니다
      }
      cart = (ArrayList<HashMap<Integer, ProductVO>>) session.getAttribute("cart");
      System.out.println("초기" + cart);
      // 장바구니 값을 넘겨줌
      // 장바구니에 담을 상품 정보
      // 여기서 pk를 비교(오버라이딩)
      // 장바구니에 상품이 없다면 map 에다가 추가
      int count = Integer.parseInt(request.getParameter("count"));// 사용자가 입력한 장바구니에 담을 갯수
      int pnum = Integer.parseInt(request.getParameter("pnum"));// 사용자가 선택한 상품
      pVO.setpNum(Integer.parseInt(request.getParameter("pnum")));// 상품 번호 세팅
      pVO = pDAO.selectOne(pVO);// 상품 정보 가져오기
      pVO.setTmpCnt(count);// 장바구니 재고 세팅

      System.out.println("count: "+count);
         for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).containsKey(pnum)) {//키가 포함된다면
               hashMap = cart.get(i);// 장바구니에 해당 인덱스 값 가져오기
               Integer key = pnum;// 키
               
               ProductVO value = hashMap.get(pnum);// value
               int newcount = value.getTmpCnt();//원래 가지고 있던 장바구니 재고
               hashMap.remove(key);
               count = newcount+count;//원래 가지고있던 재고 + 새롭게 추가한 재고
               
               pVO.setTmpCnt(count); // 장바구니 재고 변경
               /////hashMap.put(key, value);// 해쉬맵에 넣어두기 → 기존 값이 있다면 덮어씌우기
               cart.remove(i);// 중복된다면 원래 있던 값 삭제하고
               /////cart.add(hashMap);// 새롭게 넣어주기
               break;
            } 

         }
            hashMap.put(pnum, pVO);
            cart.add(hashMap);
         
      

      session.setAttribute("cart", cart);

      System.out.println("cartSession" + session.getAttribute("cart"));
      System.out.println("cartSession" + cart);

      forward.setPath("detail.do?pnum="+pnum);
      forward.setRedirect(true);
      return forward;
   }

}