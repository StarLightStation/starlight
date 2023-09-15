package com.spring.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminReviewPageController {

	@RequestMapping(value="/adminReviewPage.do")
	public String adminReviewPage() {
		return "adminReview.jsp";
	}
}
