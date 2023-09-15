package com.spring.view.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 요청 값: VO에 존재 : mID, mPW  / 세션: X  / VO에 없음: X
 * 요청 페이지: login.jsp
 * 리턴 값: model : X / 세션 : mID(mVO.getmID), mName(mVO.getmName)
 * 기능: 주주 회원 로그인
 */

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.usecoupon.UsecouponService;
import com.spring.biz.usecoupon.UsecouponVO;

@Controller
public class LoginController {

    @Autowired
    MemberService memberService;    //  mDAO

    @Autowired
    UsecouponService usecouponService;   //  ucDAO

    // =============================================[ 주주 로그인 ]======================================================

    /*
     * 요청 값: VO에 존재 : X / 세션: X / VO에 없음: X 요청 페이지:
     * login.tag(공통),subscription.tag(subscription.jsp) 리턴 값: model : X / 세션 : X 기능:
     * login.jsp로 이동
     */

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String loginPage() {
        System.out.println("로그 get");
        return "redirect:login.jsp"; // login.jsp로 이동
    }

    /*
     * 요청 값: VO에 존재 : mID, mPW / 세션: X / VO에 없음: X 요청 페이지: login.jsp 리턴 값: model : X
     * / 세션 : mID(mVO.getmID), mName(mVO.getmName) 기능: 주주 회원 로그인
     */

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(MemberVO mVO, HttpSession session, Model model) {
        System.out.println("로그 post");
        mVO.setSk("SIGNIN"); // 임시변수에 SIGNIN 셋팅

        mVO = memberService.selectOne(mVO); // view에서 받아온 mVO의 정보들을 member에 selectOne

        if (mVO != null) { // 회원정보가 있다면,
            session.setAttribute("mID", mVO.getmID()); // 세션에 mID 셋팅
            session.setAttribute("mName", mVO.getmName()); // 세션에 mName 셋팅
            session.setAttribute("mGrade", mVO.getmGrade()); // 세션에 mGrade 셋팅


            mVO.setSweetAlert("success");
            mVO.setMessage("로그인에 ");
            mVO.setLocation("main.do");

            model.addAttribute("mdata", mVO);

            return "sweetAlert.jsp";    //   sweetAlert 창을 띄우기 위한 페이지로 이동 하기.
        }

        mVO = new MemberVO();

        mVO.setSweetAlert("fail");
        mVO.setMessage("로그인에 ");
        mVO.setLocation("main.do");

        model.addAttribute("mdata", mVO);

        return "sweetAlert.jsp";    //   sweetAlert 창을 띄우기 위한 페이지로 이동 하기.
    }

    // =============================================[ 구글 로그인 ]======================================================

    /*
     * 요청 값: VO에 존재 : X / 세션: X / VO에 없음: googleEmail, googleName 요청 페이지: X 리턴 값:
     * model : X / 세션 : mID(mID), mName(mName) 기능: 구글 회원가입, 구글 로그인
     */

    @RequestMapping(value = "/googleLogin.do")
    public String googleLogin(MemberVO mVO, HttpSession session, HttpServletRequest request, Model model) {
        System.out.println("구글 로그인");
        String mID = request.getParameter("googleEmail");
        String mPW = request.getParameter("googleEmail");
        String mName = request.getParameter("googleName");

        mVO.setSk("SIGNIN"); // SIGNIN 셋팅
        mVO.setmID(mID); // mID 셋팅
        mVO.setmPW(mPW); // mPW 셋팅

        mVO = memberService.selectOne(mVO); // view에서 받아온 mVO의 정보들을 member에 selectOne

        if (mVO == null) { // 구글 아이디가 없다면,
            mVO = new MemberVO();

            mVO.setmID(mID); // mID 셋팅
            mVO.setmPW(mPW); // mPW 셋팅
            mVO.setmName(mName); // mName 셋팅
            mVO.setmPhone(mID + 1); // mPhone 셋팅
            mVO.setSignUpKind("구글"); // 가입 종류 구글 셋팅

            memberService.insert(mVO); // 셋팅한 값들 insert
        }

        if (mVO.getIsAdmin() == 1) {

            mVO.setSweetAlert("success");
            mVO.setMessage("관리자 로그인에 ");
            mVO.setLocation("adminMain.do");

            model.addAttribute("mdata", mVO);

            return "sweetAlert.jsp";
        }
        
        session.setAttribute("mID", mID); // 세션에 mID 셋팅
        session.setAttribute("mName", mName); // 세션에 mName 셋팅
        session.setAttribute("mGrade", mVO.getmGrade());   //   세션에 mGrade 셋팅

        mVO.setSweetAlert("success");
        mVO.setMessage("구글 로그인에 ");
        mVO.setLocation("main.do");

        model.addAttribute("mdata", mVO);

        return "sweetAlert.jsp";    //   sweetAlert 창을 띄우기 위한 페이지로 이동 하기.
    }

    // =============================================[ 회원가입 ]======================================================

    /*
     * 요청 값: VO에 존재 : X / 세션: X / VO에 없음: X 요청 페이지: login.jsp 리턴 값: model : / 세션 : X
     * 기능: signup.jsp로 이동
     */

    @RequestMapping(value = "/signup.do", method = RequestMethod.GET)
    public String signupPage() {

        return "redirect:signup.jsp"; // signup.jsp로 이동
    }

    /*
     * 요청 값 : VO에 존재 : mID, mPW, mName, mPhone, signUpKind, tmpmID / 세션 : X / VO에 없음 : X
     * 페이지 : signup.jsp / 리턴 값 : model : X / 세션 : X / 기능 : 주주 회원 가입 & 추천인 입력시, 추천인 쿠폰 증정.
     */
    @RequestMapping(value = "/signup.do", method = RequestMethod.POST)
    public String signup(MemberVO mVO, UsecouponVO ucVO, Model model) {

        System.out.println("log : signup() : OK");
        System.out.println("log : mID : " + mVO.getmID());
        System.out.println("log : tmpmID : " + mVO.getTmpmID());

        boolean flag = memberService.insert(mVO);   // View 으로 부터 회원가입시 필요한 데이터 들을 mVO에 set 하기.

        //  View 에서 보내준 추천인이 유효하지 않았다면, ( => mVO.getTmpmID().equals("") )
        if (mVO.getTmpmID() == null || (mVO.getTmpmID().equals("")) || (mVO.getTmpmID().isEmpty()) || (mVO.getTmpmID().isBlank())) {
            System.out.println("log : 유효하지 않는 추천인.");
        }
        //  View 에서 보내준 추천인이 유효한 추천인 이였다면,
        else {
            System.out.println("log : 유효한 추천인.");

            boolean check1 = usecouponService.insert(ucVO);  //  신규 회원 에게 추천인 쿠폰을 지급 하기.

            ucVO.setmID(mVO.getTmpmID());   //  기존 회원의 mID 값을 set 하기.
            boolean check2 = usecouponService.insert(ucVO);  //  기존 회원 에게 추천인 쿠폰을 지급 하기.

            System.out.println("log : ucVO.getmID() : " + ucVO.getmID());
            System.out.println("log : check1 : " + check1);
            System.out.println("log : check2 : " + check2);
        }

        if (flag) {
            //   회원가입이 성공 했다면,
            mVO.setSweetAlert("success");
            mVO.setMessage("회원가입에 ");
            mVO.setLocation("main.do");

            model.addAttribute("mdata", mVO);

            return "sweetAlert.jsp";    //   sweetAlert 창을 띄우기 위한 페이지로 이동 하기.
        }
        //   회원가입에 실패 했다면,
        mVO.setSweetAlert("fail");
        mVO.setMessage("회원가입에 ");
        mVO.setLocation("main.do");

        model.addAttribute("mdata", mVO);

        return "sweetAlert.jsp";    //   sweetAlert 창을 띄우기 위한 페이지로 이동 하기.
    }

    // =======================================[ 회원 가입 추천인 유효성 검사 (비동기 처리) ]================================================

    @RequestMapping(value = "/recommendationVaildity.do", method = RequestMethod.POST)
    @ResponseBody
    public String recommendationVaildity(MemberVO mVO, Gson gson) {

        System.out.println("log : recommendationVaildity() : OK");

        System.out.println("log : tmpmID : " + mVO.getTmpmID());

        mVO.setmID(mVO.getTmpmID());    //  tmpmID 값을 mID에 set 해주기. (sk 가 INFO인 selectOne() 메서드를 같이 사용 하기 위함)

        mVO.setSk("INFO");
        mVO = memberService.selectOne(mVO);

        System.out.println("log : mVO : " + mVO);

        if (mVO != null) {   //  유효한 회원이 있다면,
            String jdata = gson.toJson(mVO);
            return jdata;
        }
        return "fail";    //  유효한 회원이 없다면,
    }

    // ==============================================================================================================================

    // =============================================[ 아이디 찾기 ]======================================================

    /*
     * 요청 값: VO에 존재 : X / 세션: X / VO에 없음: X 요청 페이지: login.jsp 리턴 값: model : X / 세션 :
     * X 기능: searchID.jsp로 이동
     */

    @RequestMapping(value = "/searchID.do", method = RequestMethod.GET)
    public String searchIDPage() {

        return "redirect:searchID.jsp"; // searchID.jsp이동
    }

    /*
     * 요청 값: VO에 존재 : mPhone / 세션: X / VO에 없음: X 요청 페이지: searchID.jsp 리턴 값: model :
     * mPhone(mVO.getmPhone) / 세션 : X 기능: 아이디 찾기
     */

    @RequestMapping(value = "/searchID.do", method = RequestMethod.POST)
    public String searchID(MemberVO mVO, Model model) {

        mVO.setSk("FINDID"); // 임시변수에 FINDID 셋팅

        model.addAttribute("mPhone", mVO.getmPhone()); // 핸드폰번호를 mPhone이라는 이름으로 view에 전달
        return "send.do"; // send.do로 이동
    }

    // =============================================[ 아이디 문자 발송 ]======================================================

    /*
     * 요청 값: VO에 존재 : X / 세션: X / VO에 없음: X 요청 페이지: X 리턴 값: model : X / 세션 : X 기능:
     * 문자API
     */

    @RequestMapping(value = "/send.do")
    public String send(MemberVO mVO, HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("문자");

        String hostNameUrl = "https://sens.apigw.ntruss.com"; // 호스트 URL
        String requestUrl = "/sms/v2/services/"; // 요청 URL
        String requestUrlType = "/messages"; // 요청 URL
        String accessKey = "IKYVbyrHUoAUCqh6Er2k"; // 네이버 클라우드 플랫폼 회원에게 발급되는 개인 인증키
        String secretKey = "Z5AZcvXJZZg9NU8pdtixkQlY05ZhgDjZ0xn2FrPg"; // 2차 인증을 위해 서비스마다 할당되는 service secret
        String serviceId = "ncp:sms:kr:310876364452:smstest"; // 프로젝트에 할당된 SMS 서비스 ID
        String method = "POST"; // 요청 method
        String timestamp = Long.toString(System.currentTimeMillis()); // current timestamp (epoch)
        requestUrl += serviceId + requestUrlType;
        String apiUrl = hostNameUrl + requestUrl;

        // JSON 을 활용한 body data 생성

        JSONObject bodyJson = new JSONObject();
        JSONObject toJson = new JSONObject();
        JSONArray toArr = new JSONArray();
        String mPhone = request.getParameter("mPhone");
        mVO.setmPhone(mPhone); // mPhone 셋팅
        mVO.setSk("FINDID"); // 임시변수에 FINDID 셋팅
        mVO = memberService.selectOne(mVO); // mPhone 셋팅한 핸드폰번호와 동일한 정보들을 selectOne

        if (mVO == null) {
            //   동일한 핸드폰 번호가 없다면, (아이디 찾기 실패)
            mVO = new MemberVO();
            mVO.setSweetAlert("fail");
            mVO.setMessage("아이디 찾기에 ");
            mVO.setLocation("searchID.do");

            model.addAttribute("mdata", mVO);

            return "sweetAlert.jsp";    //   sweetAlert 창을 띄우기 위한 페이지로 이동 하기.

        } else {
            toJson.put("subject", ""); // 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
            toJson.put("content", "아이디 : " + mVO.getmID()); // 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS:
            // 2000byte
            toJson.put("to", request.getParameter("mPhone")); // 수신번호 목록 * 최대 50개까지 한번에 전송할 수 있습니다.
            toArr.add(toJson);

            bodyJson.put("type", "sms"); // 메시지 Type (sms | lms)
            bodyJson.put("contentType", "COMM"); // 메시지 내용 Type (AD | COMM) * AD: 광고용, COMM: 일반용 (default: COMM) * 광고용
            // 메시지
            // 발송 시 불법 스팸 방지를 위한 정보통신망법 (제 50조)가 적용됩니다.
            bodyJson.put("countryCode", "82"); // 국가 전화번호
            bodyJson.put("from", "01063566838"); // 발신번호 * 사전에 인증/등록된 번호만 사용할 수 있습니다.
            bodyJson.put("subject", ""); // 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
            bodyJson.put("content", "asdf2"); // 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
            bodyJson.put("messages", toArr);

            String body = bodyJson.toJSONString();

            System.out.println(body);

            try {

                URL url = new URL(apiUrl);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setUseCaches(false);
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("content-type", "application/json");
                con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
                con.setRequestProperty("x-ncp-iam-access-key", accessKey);
                con.setRequestProperty("x-ncp-apigw-signature-v2",
                        makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
                con.setRequestMethod(method);
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());

                wr.write(body.getBytes());
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();
                BufferedReader br;
                System.out.println("responseCode" + " " + responseCode);
                if (responseCode == 202) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else { // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }

                String inputLine;
                StringBuffer response1 = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response1.append(inputLine);
                }
                br.close();

                System.out.println(response1.toString());

            } catch (Exception e) {
                System.out.println(e);
            } finally {
                //   동일한 핸드폰 번호가 있다면, (아이디 찾기 성공)
                mVO.setSweetAlert("success");
                mVO.setMessage("문자 발송에 ");
                mVO.setLocation("main.do");

                model.addAttribute("mdata", mVO);
            }
            return "sweetAlert.jsp";    //   sweetAlert 창을 띄우기 위한 페이지로 이동 하기.
        }
    }

    public static String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeyException {
        String space = " "; // one space
        String newLine = "\n"; // new line

        String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp)
                .append(newLine).append(accessKey).toString();

        SecretKeySpec signingKey;
        String encodeBase64String;
        try {

            signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            encodeBase64String = e.toString();
        }

        return encodeBase64String;
    }

    // =============================================[ 로그아웃 ]======================================================

    /*
     * 요청 값: VO에 존재 : X / 세션: mID, cart / VO에 없음: X 요청 페이지: login.tag(공통) 리턴 값:
     * model : X / 세션 : X 기능: 로그아웃
     */

    @RequestMapping(value = "/logout.do")
    public String logout(HttpSession session) {
        session.removeAttribute("mID"); // 세션에 남아있는 mID 지우기
        session.removeAttribute("cart"); // 세션에 남아있는 cart 지우기
        session.removeAttribute("mGrade"); // 세션에 남아있는 mGrade 지우기

        return "redirect:main.do";    //   main.do로 이동
    }

}