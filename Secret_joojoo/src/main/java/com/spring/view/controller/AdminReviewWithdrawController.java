package com.spring.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.declaration.DeclarationService;
import com.spring.biz.declaration.DeclarationVO;

@Controller
public class AdminReviewWithdrawController {
	
	@Autowired
	DeclarationService declarationService;
	
	@RequestMapping(value="/adminReviewWithdraw.do")
	public String adminReviewDelete(DeclarationVO dVO, Model model) {
		
		System.out.println(dVO);
		declarationService.delete(dVO);
			
		return "adminReview.do";
	}

}
