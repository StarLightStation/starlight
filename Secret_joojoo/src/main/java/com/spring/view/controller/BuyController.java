package com.spring.view.controller;

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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;
import com.spring.biz.orderdetail.OrderdetailService;
import com.spring.biz.orderdetail.OrderdetailVO;
import com.spring.biz.product.ProductService;
import com.spring.biz.product.ProductVO;
import com.spring.biz.subscription.SubscriptionService;
import com.spring.biz.subscription.SubscriptionVO;
import com.spring.biz.subsinfo.SubsinfoService;
import com.spring.biz.subsinfo.SubsinfoVO;
import com.spring.biz.usecoupon.UsecouponService;
import com.spring.biz.usecoupon.UsecouponVO;

@Controller
public class BuyController {

    @Autowired
    MemberService memberService; // mDAO
    @Autowired
    ProductService productService; // pDAO
    @Autowired
    SubscriptionService subscriptionService; // subDAO
    @Autowired
    OrderService orderService; // oDAO
    @Autowired
    OrderdetailService orderdetailService; // odDAO
    @Autowired
    SubsinfoService subsinfoService; // subsinfoDAO
    @Autowired
    UsecouponService usecouponService;  //  ucDAO

    // ==========================================================[구매]==================================================================

    /*
     * 요청 값: VO에 존재 : pNum / 세션: cart,subNum, mID / VO에 없음: cartFlag 요청 페이지:
     * cart.jsp / detail.jsp 리턴 값: model :
     * subdata(구독),total(total),count(count),mPhone(mVO.getmPhone),pdata(pVO) / 세션 :
     * cartFlag(cartFlag),pNum(pVO.getpNum) 기능: 구매 진행 ( buyPage.jsp 이동 )
     */

    @RequestMapping(value = "/buyPage.do")
    public String buyPage(MemberVO mVO, ProductVO pVO, SubscriptionVO subVO, UsecouponVO ucVO, HttpSession session, Model model, HttpServletRequest request) {

        String cartFlag = request.getParameter("cartFlag");    //   cartFlag 를 View 에서 받아 오기.

        System.out.println("log : cartFlag : " + cartFlag);

        List<ProductVO> cart = (ArrayList<ProductVO>) session.getAttribute("cart");    //   세션 장바구니 가져 오기.

        if (cartFlag.equals("detail")) { //  상세 페이지 에서 구매 하는 경우.

            int total = 0;
            int tmpCnt = pVO.getTmpCnt();    //  회원이 고른 상품의 개수.

            pVO = productService.selectOne(pVO);    //  상품 번호로 해당 상품에 대한 정보 가져 오기.
            pVO.setTmpCnt(tmpCnt);   //  회원이 고른 개수 보내기
            model.addAttribute("pdata", pVO);   //  해당 상품에 대한 정보 보내 주기.
            session.setAttribute("pNum", pVO.getpNum());    // 상품 번호를 세션 으로 보내 주기.

            total = tmpCnt * pVO.getpPrice();    // (회원이 고른 상품의 개수 * 상품 가격) = 총 가격

            String mID = (String) session.getAttribute("mID");  //  세션 mID
            mVO.setmID(mID);
            mVO.setSk("INFO");
            mVO = memberService.selectOne(mVO); //  회원에 대한 정보 불러 오기.

            model.addAttribute("mPhone", mVO.getmPhone());  //  회원 전화번호 보내 주기.
            session.setAttribute("total", total);   //  상품 총 가격.
            session.setAttribute("cartFlag", cartFlag); //  세션 cartFlag

        } else if (cartFlag.equals("cart")) {   //  장바구니인 경우.

            int total = 0;

            for (int i = 0; i < cart.size(); i++) {

                pVO = cart.get(i);  //  장바구니 안의 상품들 다 불러 오기.
                total += (pVO.getTmpCnt() * pVO.getpPrice()); //  (회원이 고른 상품의 개수 * 상품의 가격) 을 총 가격에 더해 주기.
            }
            String mID = (String) session.getAttribute("mID");  //  세션 mID.
            mVO.setmID(mID);
            mVO.setSk("INFO");
            mVO = memberService.selectOne(mVO); //  회원에 대한 정보 불러 오기.

            model.addAttribute("mPhone", mVO.getmPhone());  //  회원 전화번호 보내 주기.
            session.setAttribute("total", total);   //  상품 총 가격.
            session.setAttribute("cartFlag", cartFlag); //  세션 cartFlag
        } else { //  장바구니가 아니라면,

            int subNum = (Integer) session.getAttribute("subNum");  //  세션 subNum 받아 오기.

            subVO.setSubNum(subNum); // subNum set 하기.
            subVO = subscriptionService.selectOne(subVO);   //  구독 정보 가져 오기.

            session.setAttribute("subNum", subVO.getSubNum());  //   구독 번호 세션으로 보내 주기.

            int count = 1;  //  구독은 1개만 구매 가능한 설계.
            int total = subVO.getSubPrice();    //  구독 가격.

            model.addAttribute("count", count);    //   구독은 1개만 구매 가능한 설계.
            model.addAttribute("subdata", subVO);   //  구독에 대한 정보.
            session.setAttribute("total", total);   //    상품 총 가격.

        }

        //  ---------------------------------------- ( 쿠폰 데이터 ) ----------------------------------------
        //  모든 구매 경우의 수 일지라도 결제 페이지로 사용자의 사용 가능한 쿠폰 데이터를 보내 주기.
        String mID = (String) session.getAttribute("mID");  //  세션 mID 받아 오기.
        ucVO.setmID(mID);   // mID set 하기.
        ucVO.setSk("ABLE");
        List<UsecouponVO> ucdatas = usecouponService.selectAll(ucVO);

        model.addAttribute("ucdatas", ucdatas); //  회원의 사용 가능한 쿠폰 데이터를 보내 주기.

        return "buyPage.jsp";   //  구매 페이지로 이동
    }

    // ================================================================================================================================

    @RequestMapping(value = "/buy.do")
    public String buy(ProductVO pVO, OrderVO oVO, UsecouponVO ucVO, HttpSession session) {

        session.setAttribute("useCouponDiscountTotal", session.getAttribute("total")); //  할인된 총 가격을 기존의 총 가격으로 초기화. (뒤로가기 유효성 검사)

        System.out.println("log : buy() : OK");

        System.out.println("log : ucNum : " + ucVO.getUcNum());
        System.out.println("log : cDiscount : " + ucVO.getcDiscount());
        System.out.println("log : sessionTotal : " + session.getAttribute("total"));
        System.out.println("log : Before : useCouponDiscountTotal : " + session.getAttribute("useCouponDiscountTotal"));

        if (ucVO.getUcNum() != 0 && ucVO.getcDiscount() != 0) {  //  사용자가 쿠폰을 선택 했으면 ~

            System.out.println("log : 쿠폰 선택 OK");

            int sessionTotal = (Integer) session.getAttribute("total"); //  기존에 세션에 있었던 구매 상품의 총 가격.
            double cDiscount = ucVO.getcDiscount();
            int useCouponDiscountTotal = (int) (sessionTotal * cDiscount); //  세션 에서 가져온 총 가격과 쿠폰 할인율을 계산하고,
            System.out.println("log : After : useCouponDiscountTotal : " + useCouponDiscountTotal);
            session.setAttribute("useCouponDiscountTotal", useCouponDiscountTotal); //  할인된 총 가격을 다시 세션으로 보내기.

            //  쿠폰을 적용 하고, 결제를 성공적으로 마치면, 쿠폰 사용 가능 여부를 업데이트 하기 위해 플래그값을 세션으로 보내기.
            session.setAttribute("isAppliedCoupon", true);
            session.setAttribute("ucNum", ucVO.getUcNum()); //  쿠폰 사용 여부를 업데이트 하기 위해 세션으로 보내기.
        }

        String address = oVO.getoAddress();
        String orderName = oVO.getOrderName();
        int tmpCnt = pVO.getTmpCnt();

        System.out.println("address : " + address);
        System.out.println("orderName : " + orderName);
        System.out.println("tmpCnt : " + tmpCnt);

        session.setAttribute("address", address);
        session.setAttribute("orderName", orderName);
        session.setAttribute("tmpCnt", tmpCnt);

        //  쿠폰을 적용 했을 때,
        //  buy.jsp 에서 사용자가 새로고침을 누르면, 누를때마다 이전 요청을 다시 서버로 보내기 때문에,
        //  할인율이 중복해서 적용되는 이슈를 막기 위해서, redirect: 를 작성 했다.
        //  ==  PRG (Post-Redirect-Get) 패턴
        return "redirect:buy.jsp";
    }

    // =======================================[ 구매시 쿠폰 적용 여부 (비동기 처리) ]================================================

    @RequestMapping(value = "/couponStatus.do", method = RequestMethod.POST)
    @ResponseBody
    public String couponStatus(@RequestParam Map<String, Object> map) {

        System.out.println("log : couponStatus() : OK");
        System.out.println("log : couponStatus() : sendCouponStatusData : " + map.get("sendCouponStatusData"));

        int sendCouponStatusData = Integer.valueOf(map.get("sendCouponStatusData").toString());

        String couponStatus = "";

        if (sendCouponStatusData == 1) { //  모달창에서 사용 하기 버튼을 클릭 했으면,
            couponStatus = "적용";
        } else if (sendCouponStatusData == 2) {    //  모달창에서 사용 안함 버튼을 클릭 했으면,
            couponStatus = "미적용";
        }
        return couponStatus;
    }

    // =========================================================================================================================

    // ===============================================[구매 성공]=================================================================

    /*
     * 요청 값: VO에 존재 : address / 세션: mID, subNum, carFlag, pNum, cart
     * VO에 없음 : orderId, paymentKey, amount, address / 요청 페이지 : buyPage.jsp / 리턴 값 : model : / 세션 : X
     * 기능 : 결제 완료 (success.jsp 이동)
     */

    @RequestMapping(value = "/success.do")
    public String success(MemberVO mVO, OrderVO oVO, OrderdetailVO odVO, ProductVO pVO, UsecouponVO ucVO, HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        System.out.println("log : success() : OK");

        String orderId = request.getParameter("orderId");   //  주문 번호 받아 오기.
        String paymentKey = request.getParameter("paymentKey"); //  결제 key 받아 오기.
        int amount = (Integer) session.getAttribute("useCouponDiscountTotal");   //  회원이 주문한 상품 총 가격 받아 오기.
        String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R:"; //  결제 secretKey

        Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));  //  UTF-8 인코딩.
        String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

        paymentKey = URLEncoder.encode(paymentKey, StandardCharsets.UTF_8); //  결제 key UTF-8 인코딩.

        URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");  //  토스 페이로 이동.

        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // url 연결
        connection.setRequestProperty("Authorization", authorizations);
        connection.setRequestProperty("Content-Type", "application/json");  //   json 타입.
        connection.setRequestMethod("POST");    //  POST 방식.
        connection.setDoOutput(true);
        JSONObject obj = new JSONObject();

        obj.put("paymentKey", paymentKey);  //  결제 key 보내기.
        obj.put("orderId", orderId);    //  주문 번호 보내기.
        obj.put("amount", amount);  //  회원이 주문한 상품 마다의 개수.

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        int code = connection.getResponseCode();    //  에러코드 번호 (토스페이에서 제공하는 코드 번호)
        boolean isSuccess = code == 200 ? true : false; //  코드번호가 200 일때 true, 나머지는 false

        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream(); //  성공 하면 getInputStream() 연결.

        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);

        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            model.addAttribute("JSONObject", jsonObject);
            model.addAttribute("isSuccess", isSuccess);

            String mID = (String) session.getAttribute("mID");  //  세션 mID
            String address = (String) session.getAttribute("address");  //  주소 정보를 받아 오기.

            //  구독 구매 X 이면서, 상품 상세 페이지 에서 바로 구매 했을때.
            if (session.getAttribute("subNum") == null && session.getAttribute("cartFlag").equals("detail")) {

                System.out.println("상품 상세 페이지 결제");

                int pNum = (Integer) session.getAttribute("pNum");  //  세션으로 보낸 상품 번호 받아 오기.
                int tmpCnt = (Integer) session.getAttribute("tmpCnt");    //   세션으로 보낸 구매 하려는 상품의 개수 받아 오기.

                pVO.setpNum(pNum);
                pVO = productService.selectOne(pVO);    //  DB의 상품 정보 가져 오기.

                if (pVO.getpCnt() < tmpCnt) {  //  상품의 재고가 구매 하려는 상품의 개수 보다 적으면 ~

                   System.out.println("log : 재고부족");
                    model.addAttribute("fail", "재고부족");
                    return "success.jsp";
                }

                //  상품의 재고가 구매 하려는 상품의 개수보다 크거나 같으면 ~
                oVO.setmID(mID);    //  주문 정보에 mID set.
                oVO.setoAddress(address);   //  주문 정보에 주소 set.
                oVO.setoPrice(amount);  //  구매한 총 가격 set.
                oVO.setoState("결제 완료"); //  결제 상태
                orderService.insert(oVO);   //  mID, 주소, 구매한 총 가격, 결제 상태 데이터를 테이블에 저장 하기.

                oVO.setSk("MAXONUM");
                oVO = orderService.selectOne(oVO);  //  갱신된 정보 불러 오기.

                odVO.setpNum(pNum); // 상품 번호 set.
                odVO.setoNum(oVO.getoNum());    //  주문 번호 set.
                odVO.setOdCnt(tmpCnt); //  구매한 개수 set.
                orderdetailService.insert(odVO);    //  주문 상세 테이블에 데이터 저장 하기.

                pVO.setTmpCnt(tmpCnt);    //   주문한 상품 개수 set.
                pVO.setpNum(pNum);    //   상품 번호 set.
                pVO.setSk("CNT");
                productService.update(pVO);    //   상품 테이블에 사용자가 구매한 상품 개수만큼 재고 업데이트 하기.

                session.removeAttribute("pNum");
                session.removeAttribute("total");
                session.removeAttribute("cartFlag");
                session.removeAttribute("useCouponDiscountTotal");
                session.removeAttribute("address");
                session.removeAttribute("orderName");
                session.removeAttribute("tmpCnt");

                //  구독 구매 X 이면서, 장바구니 에서 구매 했을때.
            } else if (session.getAttribute("subNum") == null && session.getAttribute("cartFlag").equals("cart")) {

                System.out.println("장바구니 결제");

                List<ProductVO> cart = (ArrayList<ProductVO>) session.getAttribute("cart"); // 장바구니 생성 하기.

                for (int i = 0; i < cart.size(); i++) {  //  장바구니 안의 모든 상품 불러 오기.
                    pVO = cart.get(i);
                    int tmpCnt = pVO.getTmpCnt();
                    System.out.println("log : tmpCnt : " + tmpCnt);
                    pVO = productService.selectOne(pVO);

                    if (pVO.getpCnt() < tmpCnt) {  //  상품의 재고가 구매 하려는 상품의 개수 보다 적으면 ~
                       System.out.println("log : 재고부족");
                        model.addAttribute("fail", "재고부족");
                        return "success.jsp";
                    }
                }
                //  상품의 재고가 구매 하려는 상품의 개수보다 크거나 같으면 ~
                oVO.setmID(mID); // 회원 ID
                oVO.setoAddress(address); // 주소
                oVO.setoPrice(amount); // 총 가격
                oVO.setoState("결제 완료"); // 결제 상태
                orderService.insert(oVO); // 주문 테이블에 입력

                oVO.setSk("MAXONUM");
                oVO = orderService.selectOne(oVO);  //  주문 정보 불러 오기.

                for (int i = 0; i < cart.size(); i++) {
                    pVO = cart.get(i);  //  장바구니 안의 모든 상품 불러 오기.
                    odVO.setpNum(pVO.getpNum());    //  상품 번호 보내기.
                    odVO.setoNum(oVO.getoNum());    // 주문 번호 보내기.
                    odVO.setOdCnt(pVO.getTmpCnt()); //  회원이 고른 개수 보내기.
                    orderdetailService.insert(odVO);    // 주문 상세 데이터를 테이블에 저장 하기.

                    pVO.setTmpCnt(pVO.getTmpCnt());    //   주문한 상품 개수 set.
                    pVO.setpNum(pVO.getpNum());    //   상품 번호 set.
                    pVO.setSk("CNT");
                    productService.update(pVO);    //   상품 테이블에 사용자가 구매한 상품 개수만큼 재고 업데이트 하기.
                }

                session.removeAttribute("cart");    //  원래의 장바구니 목록은 구매 했기 때문에 지워 주기.
                session.removeAttribute("pNum");
                session.removeAttribute("total");
                session.removeAttribute("cartFlag");
                session.removeAttribute("useCouponDiscountTotal");
                session.removeAttribute("address");
                session.removeAttribute("orderName");
                session.removeAttribute("tmpCnt");

            } else {    //  구독 구매 일 때.

                System.out.println("구독 결제");

                int subNum = (Integer) session.getAttribute("subNum");  //  세션으로 보내준 구독 번호 받아 오기.
                SubsinfoVO subinfoVO = new SubsinfoVO();    //  구독 상세 정보 객체 생성 하기.

                subinfoVO.setmID(mID);  //  회원 mID.
                subinfoVO.setSubNum(subNum);    //  구독 번호 1,2,3 중 하나.

                subsinfoService.insert(subinfoVO);  //  구독 상세 테이블에 입력.

                mVO.setmID(mID);    //  회원 mID.
                mVO.setSk("CHANGESUBS");    //  회원의 구독 여부 불러오기.
                memberService.update(mVO);  //  회원의 구독 여부 바꿔주기.

                session.removeAttribute("subNum");
                session.removeAttribute("total");
                session.removeAttribute("useCouponDiscountTotal");
                session.removeAttribute("address");
                session.removeAttribute("orderName");
                session.removeAttribute("tmpCnt");

            }
            
            // 회원 등급 확인
         mVO.setmID((String) session.getAttribute("mID"));
         mVO.setSk("INFO");
         mVO = memberService.selectOne(mVO);
         
         session.setAttribute("mGrade", mVO.getmGrade());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        responseStream.close();

        //  ---------------------------------------- ( 쿠폰을 사용 했을 때, 쿠폰 사용 가능 여부를 바꿔주기 ) ----------------------------------------

        Boolean isAppliedCoupon = (Boolean) session.getAttribute("isAppliedCoupon");

        if (isAppliedCoupon != null && isAppliedCoupon == true) {    //  쿠폰을 사용 했다면 ~

            int ucNum = (Integer) session.getAttribute("ucNum");    //  세션에 있는 사용자가 선택한 쿠폰의 PK를 가져오고,
            ucVO.setUcNum(ucNum);   //  쿠폰의 PK를 set.
            boolean flag = usecouponService.update(ucVO);  //  쿠폰의 사용 가능 여부를 0으로 바꿔주기 위한 update().

            session.removeAttribute("isAppliedCoupon"); //  결제가 끝났으니,
            session.removeAttribute("ucNum");   //  세션을 비워 주기.

            if (flag) {
                System.out.println("log : OK");
            } else {
                System.out.println("log : Fail");
            }
        }

        //  ---------------------------------------------------------------------------------------------------------------------------------

        return "success.jsp";   //  결제 완료 페이지로 이동 하기.
    }

    // ====================================================================================================

}