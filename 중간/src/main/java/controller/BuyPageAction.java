package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;
import model.ProductDAO;
import model.ProductVO;
import model.SubscriptionDAO;
import model.SubscriptionVO;

public class BuyPageAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      ActionForward forward = new ActionForward();// 객체 선언
      // →페이지 이동만 하므로 객체를 바로 초기화
      // 넘겨줄 값 : 없음(상품 정보 값)
      // 이동할 페이지 : buyPage.jsp
      HttpSession session = request.getSession();
      String cartFlag = request.getParameter("cartFlag");
      ArrayList<HashMap<Integer, ProductVO>> cart = (ArrayList<HashMap<Integer, ProductVO>>) session.getAttribute("cart");
      System.out.println(cartFlag);
      System.out.println("pnum : " + request.getParameter("pnum"));
      if (cartFlag == null) {
         if(request.getParameter("subNum") != null) {//구독 구매
            
            SubscriptionVO subVO = new SubscriptionVO(); //구독 정보 객체 생성
            SubscriptionDAO subDAO = new SubscriptionDAO();
            
            MemberVO mVO = new MemberVO();
            MemberDAO mDAO = new MemberDAO();
            
           int subNum = Integer.parseInt( request.getParameter("subNum"));// 구독할 상품의 번호 가져오기
           subVO.setSubNum(subNum);//구독 번호 세팅
           subVO = subDAO.selectOne(subVO);// 구독 정보 가져오기
           session.setAttribute("subNum", subNum);
           request.setAttribute("subdata", subVO);
           int count = 1;
            String mid = (String) session.getAttribute("mid");
            System.out.println("아이디 세션:" + mid);
            mVO.setmID(mid);
            mVO.setSk("INFO");
            mVO = mDAO.selectOne(mVO);
            System.out.println("mVO : " + mVO);
            int total = subVO.getSubPrice();
            request.setAttribute("mname", mVO.getmName());
            request.setAttribute("mphone", mVO.getmPhone());
            request.setAttribute("total", total);// 총가격
            request.setAttribute("count", count);// 총개수
           
           
         }else {//물건 구매
            // 한개만 결재 진행 할떄
                ProductVO pVO = new ProductVO(); // 상품 정보 객체
                ProductDAO pDAO = new ProductDAO();

                MemberVO mVO = new MemberVO();
                MemberDAO mDAO = new MemberDAO();

                // 사용자가 구매하기 위한 정보들 가져오기(사용자 정보, 구매할 상품 정보)
                pVO.setpNum(Integer.parseInt(request.getParameter("pnum")));// 사용자가 선택한 상품의 번호
                pVO = pDAO.selectOne(pVO);// 상품 정보 가져오기
                pVO.setTmpCnt(Integer.parseInt(request.getParameter("count")));
                request.setAttribute("pdata", pVO);
                // 상품 번호를 세션으로 관리(여러군데서 사용할 예정)
                session.setAttribute("pnum", pVO.getpNum());// 상품 번호

                int count = Integer.parseInt(request.getParameter("count"));
                String mid = (String) session.getAttribute("mid");
                System.out.println("아이디 세션:" + mid);
                mVO.setmID(mid);
                mVO.setSk("INFO");
                mVO = mDAO.selectOne(mVO);
                System.out.println("mVO : " + mVO);
                int total = count * pVO.getpPrice();
                request.setAttribute("mname", mVO.getmName());
                request.setAttribute("mphone", mVO.getmPhone());
                request.setAttribute("total", total);// 총가격
                request.setAttribute("count", count);// 총개수
         }
         
        

      } else if (cartFlag.equals("cart")) {
         int total = 0;
         for (int i = 0; i < cart.size(); i++) {
            // 키값에 대한 값을 가져와야함
            HashMap<Integer, ProductVO> hashMap = cart.get(i);// 키 값
            Set<Integer> keySet = hashMap.keySet();
            ArrayList<Integer> keyList = new ArrayList<Integer>();
            for (int v : keySet) {
               keyList.add(v);
            }
//            System.out.println("확인: "+keyList);
            for (int v : keyList) {
               ProductVO pVO = hashMap.get(v);
               total += pVO.getTmpCnt() * pVO.getpPrice();

            }
            session.setAttribute("cartFlag", cartFlag );//장바구니 구매인지 확인할 여부

            /// ProductVO pVO = HashMap.get(hashMap.keySet());
            // 값을 가져와서 그거에 있는 값 * tmpCnt를 해주고
            // 새로운 변수를 하나 만들어서 (총합) 그거 넘겨주기

         }
//         System.out.println("총 가격:" +total);

         request.setAttribute("total", total);// 총 가격 넘기기

      }
      forward.setPath("buyPage.jsp");// 구매정보 입력하기로 이동하기 위한 url 세팅
      forward.setRedirect(false);// 리다이렉트 : false (넘겨줄 값 : 사용자 정보-이름, 번호 -)
      return forward;

   }

}