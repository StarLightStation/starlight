package com.spring.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.subscription.SubscriptionService;
import com.spring.biz.subscription.SubscriptionVO;

@Controller
public class SubscriptionController {

	@Autowired
	MemberService memberService;   //   mDAO.
	@Autowired
	SubscriptionService subscriptionService;   //   subDAO.

	//===============================================[구독]=================================================

	/*
   요청 값 : VO에 존재 : X / 세션 : X / VO에 없음 : X
   요청 페이지 : login.tag
   리턴 값 : model : X / 세션 : X
   기능 : 구독 페이지로 이동.
	 */

	@RequestMapping(value = "/subscription.do", method=RequestMethod.GET)
	public String subscription(HttpSession session) {

		session.removeAttribute("cartFlag");

		return "redirect:subscription.jsp";   //   구독 페이지.
	}
	//====================================================================================================

	//===============================================[구독 수정]==============================================

	/*
   요청 값 : VO에 존재 : subNum / 세션 : mID / VO에 없음 : X
   요청 페이지 : subscription.tag
   리턴 값 : model : X / 세션 : subNum, mName, mPhone
   기능 : 회원이 구독을 하고 있지 않았다면, 선택한 구독 정보를 가지고, 결제 페이지로 이동.
	 */

	@RequestMapping(value = "/subscription.do", method=RequestMethod.POST)
	public String subscriptionUpdate(MemberVO mVO, SubscriptionVO subVO, HttpSession session, Model model) {

		String mid = (String) session.getAttribute("mID");   //   세션 mID.
		mVO.setmID(mid);
		mVO.setSk("INFO");
		mVO = memberService.selectOne(mVO);   //   회원 아이디 (요청 값) 에 해당 되는 회원 정보.
		subVO = subscriptionService.selectOne(subVO);   //   구독 상품 번호 (요청 값) 에 해당 되는 구독 상품 정보.

		if (mVO.getSubscription() == 0) {   //   회원의 구독 여부 번호가 0 이라면,

			model.addAttribute("subdata", subVO);   //   결제 페이지로 구독 상품 정보 전송.   
			session.setAttribute("subNum", subVO.getSubNum());   //   세션 으로 구독 상품 번호 관리.
			session.setAttribute("mName", mVO.getmName());   //   세션 으로 회원 이름 관리.
			session.setAttribute("mPhone", mVO.getmPhone());   //   세션 으로 회원 전화 번호 관리.

			return "buyPage.do";   //   결제 페이지.
		}

		mVO.setSweetAlert("fail");
		mVO.setMessage("이미 구독중입니다. 구독결제에");
		mVO.setLocation("main.do");

		model.addAttribute("mdata", mVO);

		return "sweetAlert.do";   //   이미 구독 중이라면, 메인 페이지로 이동.
	}

	//====================================================================================================

}