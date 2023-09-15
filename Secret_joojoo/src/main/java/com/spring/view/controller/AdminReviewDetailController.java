package com.spring.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.declaration.DeclarationService;
import com.spring.biz.declaration.DeclarationVO;

@Controller
public class AdminReviewDetailController {
	
	@Autowired
	DeclarationService declarationService;
	
	@RequestMapping(value="/adminReviewDetail.do")
	public String adminReviewDetailPage(DeclarationVO dVO, Model model) {
				
		dVO = declarationService.selectOne(dVO);
		model.addAttribute("ddata", dVO);
		
		return "adminReviewDetailPage.do";
	}

}
