package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.subsinfo.SubsinfoService;
import com.spring.biz.subsinfo.SubsinfoVO;
import com.spring.biz.usecoupon.UsecouponService;
import com.spring.biz.usecoupon.UsecouponVO;

@Controller
public class MyPageSubscriptionController {

	@Autowired
	MemberService memberService;    //	memberDAO
    @Autowired
    SubsinfoService subsinfoService;    //	subsinfoDAO
    @Autowired
    UsecouponService usecouponService;   //  usecouponDAO

    //==================================================[마이페이지 구독 및 쿠폰 페이지 이동]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 : login.tag, mypage.tag (member.jsp)
     * 리턴 값 : model : subsinfodata (본인의 구독 상세 데이터), ucdatas (본인의 보유 쿠폰 데이터) / 세션 : X
     * 기능 : 나의 구독 내역 보기 ( mysubscription.jsp 이동 )
     */

    @RequestMapping(value = "/mySubscription.do", method = RequestMethod.GET)
    public String mySubscription(MemberVO mVO, SubsinfoVO subsinfoVO, UsecouponVO ucVO, HttpSession session, Model model) {

        subsinfoVO.setmID((String) session.getAttribute("mID"));    //	세션 mID
        ucVO.setmID((String) session.getAttribute("mID"));  //  세션 mID
        subsinfoVO = subsinfoService.selectOne(subsinfoVO);
        
        ucVO.setSk("ALL");
        List<UsecouponVO> ucdatas = usecouponService.selectAll(ucVO);

        model.addAttribute("subsinfodata", subsinfoVO); //  회원의 구독 상세 정보.
        model.addAttribute("ucdatas", ucdatas); //  회원의 보유 쿠폰 내역.  
        
        return "mysubscription.jsp";    //	마이페이지 구독 및 쿠폰 페이지.
    }

    //==========================================================================================================================

}    //	MypageSubscriptionController
