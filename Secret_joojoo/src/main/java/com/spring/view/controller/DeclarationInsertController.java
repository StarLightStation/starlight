package com.spring.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.declaration.DeclarationService;
import com.spring.biz.declaration.DeclarationVO;
import com.spring.biz.product.ProductVO;

@Controller
public class DeclarationInsertController {
	
	@Autowired
	DeclarationService declarationService;
	
	@RequestMapping(value="/declarationInsert.do")
	public String declarationInsert(DeclarationVO dVO, ProductVO pVO, HttpSession session) {
		
		dVO.setmID((String)session.getAttribute("mID"));
		declarationService.insert(dVO);
		
		pVO.setpNum(pVO.getpNum());
		
		return "detail.do";
	}
}
