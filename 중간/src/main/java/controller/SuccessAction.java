package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.MemberDAO;
import model.MemberVO;
import model.OrderDAO;
import model.OrderVO;
import model.OrderdetailDAO;
import model.OrderdetailVO;
import model.ProductDAO;
import model.ProductVO;
import model.SubsinfoDAO;
import model.SubsinfoVO;

public class SuccessAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      // 결제 승인 API 호출하기
      System.out.println(".do 들어왔음");
      ActionForward forward = null;
      String orderName = request.getParameter("orderName");
      String orderId = request.getParameter("orderId");
      String paymentKey = request.getParameter("paymentKey");
      int amount = Integer.parseInt(request.getParameter("amount"));
      String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R:";

      System.out.println("orderId" + orderId);
      System.out.println("orderName : " + orderName);
      System.out.println("paymentKey" + paymentKey);
      System.out.println("amount" + amount);
      Encoder encoder = Base64.getEncoder();
      byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
      String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

      paymentKey = URLEncoder.encode(paymentKey, StandardCharsets.UTF_8);

      URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("Authorization", authorizations);
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      JSONObject obj = new JSONObject();
      obj.put("paymentKey", paymentKey);
      obj.put("orderId", orderId);
      obj.put("amount", amount);
      obj.put("orderName", orderName);

      OutputStream outputStream = connection.getOutputStream();
      outputStream.write(obj.toString().getBytes("UTF-8"));

      int code = connection.getResponseCode();
      boolean isSuccess = code == 200 ? true : false;

      InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();

      Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
      JSONParser parser = new JSONParser();
      try {
         System.out.println("try 들어옴");
         JSONObject jsonObject = (JSONObject) parser.parse(reader);
         request.setAttribute("JSONObject", jsonObject);
         request.setAttribute("isSuccess", isSuccess);

         HttpSession session = request.getSession();// 세션 객체 생성
         String mid = (String) session.getAttribute("mid");// 세션에 저장된 사용자 아이디 값
         MemberVO mVO = new MemberVO();// 사용자 객체
         MemberDAO mDAO = new MemberDAO();

         OrderVO oVO = new OrderVO();// 구매 목록 객체
         OrderDAO oDAO = new OrderDAO();
         
         ProductVO pVO = new ProductVO();
         ProductDAO pDAO = new ProductDAO();
         
         OrderdetailVO odVO = new OrderdetailVO();// 구매 상세정보 객체
         OrderdetailDAO odDAO = new OrderdetailDAO();

         // 구독인지의 여부를 확인할 세션 가져오기
         if (session.getAttribute("subNum") == null) {// 물건 구매라면
            // 물건 여러개인지 아닌지 확인
            if (session.getAttribute("cartFlag") == null) {
               String address = request.getParameter("address");

               oVO.setmID(mid);// 아이디(세션)
               oVO.setoAddress("경기도");// 사용자가 입력한 주소
               oVO.setoPrice(amount);// 사용자가 구매한 가격
               oVO.setoState("결제 완료");// 결제 상태

               oDAO.insert(oVO);// DB에 삽입

               oVO = oDAO.selectOne(oVO);// 주문 번호를 가져오기 위한 장치
               int pnum = (int) session.getAttribute("pnum");
               pVO.setpNum(pnum);
               pVO = pDAO.selectOne(pVO);
               int cnt = amount/pVO.getpPrice();
               odVO.setpNum(pnum);// 상품 번호
               odVO.setoNum(oVO.getoNum());// 주문 번호 
               odVO.setOdCnt(cnt);// 구매한 상품 개수
               
               odDAO.insert(odVO);
               forward = new ActionForward();// 객체 생성
               forward.setPath("success.jsp");// 이동 경로
               forward.setRedirect(false);// 넘겨줄 값 없음
               
            }

            else if (session.getAttribute("cartFlag").equals("cart")) {// 장바구니 구매라면
               //장바구니 세션 가져오기
               ArrayList<HashMap<Integer, ProductVO>>cart = (ArrayList<HashMap<Integer, ProductVO>>) session.getAttribute("cart");
               //이거를 DB에 넣어주기
               oVO.setmID(mid);// 아이디(세션)
               oVO.setoAddress("경기도");// 사용자가 입력한 주소
               oVO.setoPrice(amount);// 사용자가 구매한 가격
               oVO.setoState("결제 완료");// 결제 상태

               oDAO.insert(oVO);// DB에 삽입

               oVO = oDAO.selectOne(oVO);// 주문 번호를 가져오기 위한 장치

               
               
               
               for(int i =0; i< cart.size();i++) {
                  HashMap<Integer, ProductVO> hashMap = cart.get(i);// 키 값
                  Set<Integer> keySet = hashMap.keySet();
                  ArrayList<Integer> keyList = new ArrayList<Integer>();
                  for (int v : keySet) {
                     keyList.add(v);
                  }
//                  System.out.println("확인: "+keyList);
                  for (int v : keyList) {
                     pVO = hashMap.get(v);
                     odVO.setpNum(pVO.getpNum());// 상품 번호
                     odVO.setoNum(oVO.getoNum());// 주문 번호
                     odVO.setOdCnt(pVO.getTmpCnt());// 구매한 상품 개수
                     odDAO.insert(odVO);
                  }
               }
               
               
               
               
               session.removeAttribute("cart");
               forward = new ActionForward();// 객체 생성
               forward.setPath("success.jsp");// 이동 경로
               forward.setRedirect(false);// 넘겨줄 값 없음

            }

         } else {// 구독 상품 구매라면
            int subNum = (int) session.getAttribute("subNum");
            SubsinfoVO subVO = new SubsinfoVO();
            SubsinfoDAO subDAO = new SubsinfoDAO();

            subVO.setmID(mid);// 사용자 아이디
            subVO.setSubNum(subNum);// 구매할려는 구독 상품 세팅

            subDAO.insert(subVO);// DB에 구독 정보 추가

            // 구독 정보 추가 후 구독 여부 변경하기
            mVO.setSk("CHANGESUBS");// 구독 변경 SK
            mVO.setmID(mid);// 사용자 아이디 세팅

            mDAO.update(mVO);// 구독 여부 변경(0에서 1로 변경)

            forward = new ActionForward();// 객체 생성
            forward.setPath("success.jsp");// 이동 경로
            forward.setRedirect(false);// 넘겨줄 값 없음

            session.removeAttribute("subNum");// 구매후 세션 제거(구독 상품 여부)

         }

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      responseStream.close();

//=================================================================

      return forward;
   }

}