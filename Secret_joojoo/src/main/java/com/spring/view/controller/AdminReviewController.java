package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.declaration.DeclarationService;
import com.spring.biz.declaration.DeclarationVO;

@Controller
public class AdminReviewController {

	@Autowired
	DeclarationService declarationService;
	
	//==================================================[  ]==================================================
	
	/*
	 * 요청 값: VO에 존재 : X  / 세션: mID  / VO에 없음: X
	 * 요청 페이지: login.tag , mypage.tag(member.jsp)
	 * 리턴 값: model : subsinfodata(subsinfoVO) / 세션 : X
	 * 기능: 나의 구독 내역 보기 ( mysubscription.jsp 이동 )
	 */
	
	@RequestMapping(value = "/adminReview.do")
	public  String adminReview(DeclarationVO dVO, Model model) {
		
		dVO.setSk("ALL");
		List<DeclarationVO> ddatas = declarationService.selectAll(dVO);
		model.addAttribute("ddatas", ddatas);
		
		return "adminReviewPage.do"; 
	}
}
