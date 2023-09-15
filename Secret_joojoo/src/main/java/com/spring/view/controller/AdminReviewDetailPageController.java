package com.spring.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminReviewDetailPageController {
	
	@RequestMapping(value="/adminReviewDetailPage.do")
	public String adminReviewDetailPage() {
		return "adminReviewDetail.jsp";
	}

}
