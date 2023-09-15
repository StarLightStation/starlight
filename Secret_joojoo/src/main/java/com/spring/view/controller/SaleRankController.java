package com.spring.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;
import com.spring.biz.orderdetail.OrderdetailService;
import com.spring.biz.orderdetail.OrderdetailVO;

@Controller
public class SaleRankController {

	@Autowired
	OrderService orderService;
	@Autowired
	OrderdetailService orderdetailService;


	@RequestMapping(value="/saleRank.do") // 매출 순위 페이지 들어올때 
	public String memberRank(OrderVO oVO, Model model, HttpSession session) {

			oVO.setSk("MEMBER_TOTAL");
			List<OrderVO> memberTotalDatas = orderService.selectAll(oVO);

			model.addAttribute("memberTotalDatas", memberTotalDatas);

			return "adminSaleRank.jsp";
	
	}

	@RequestMapping(value = "/productRank.do")
	public String productRank(OrderdetailVO odVO, Model model) {

		odVO.setSk("CNT");
		List<OrderdetailVO> productDatas = orderdetailService.selectAll(odVO);

		System.out.println("로그 : productDatas :  " + productDatas);

		model.addAttribute("productDatas",  productDatas );

		return "adminProductRank.jsp";
	}

	@RequestMapping(value = "/productRankOption.do")
	public String productRankOption(@RequestParam("productRankOption")int rankOption, OrderdetailVO odVO, Model model ) {
		System.out.println("로그 : rankOption : " + rankOption);
		List<OrderdetailVO> rankOptionDatas =  new ArrayList<OrderdetailVO> ();
	
		if(rankOption == 1) {
			odVO.setSk("CNT");
			rankOptionDatas = orderdetailService.selectAll(odVO);
			System.out.println("로그 : rankOptionDatas  : " + rankOptionDatas);
		}else {
			odVO.setSk("TOTAL");
			rankOptionDatas = orderdetailService.selectAll(odVO);
			System.out.println("로그 : rankOptionDatas : " + rankOptionDatas);
		}

		model.addAttribute("rankOption", rankOption);
		model.addAttribute("rankOptionDatas",  rankOptionDatas );
		return "adminProductRank.jsp";

	}
}
