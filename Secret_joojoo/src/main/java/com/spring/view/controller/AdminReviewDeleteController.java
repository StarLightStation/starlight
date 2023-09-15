package com.spring.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.declaration.DeclarationService;
import com.spring.biz.declaration.DeclarationVO;

@Controller
public class AdminReviewDeleteController {
	
	@Autowired
	DeclarationService declarationService;
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/adminReviewDelete.do")
	public String adminReviewDelete(BoardVO bVO, DeclarationVO dVO, Model model) {
		
		boardService.delete(bVO);
		declarationService.delete(dVO);
			
		return "adminReview.do";
	}

}
