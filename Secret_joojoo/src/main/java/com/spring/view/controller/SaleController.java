package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;

@Controller
public class SaleController {

	@Autowired
	OrderService orderService;


	@RequestMapping(value = "/sale.do" , method = RequestMethod.GET) // 매출관리 첫 페이지이동
	public String salePage(OrderVO oVO, Model model) {

		oVO.setSk("YEAR_SALE");
		List<OrderVO> yearOdatas = orderService.selectAll(oVO);

		oVO.setSk("TOTAL");
		oVO = orderService.selectOne(oVO);

		model.addAttribute("yearOdatas", yearOdatas);
		System.out.println("로그 : yearOdatas : "+ yearOdatas );
		model.addAttribute("totalOdata", oVO);
		System.out.println("로그 : totalOdata : "+ oVO );


		return "adminSale.jsp"; // 매출관리.jsp 이동 



	}

	@RequestMapping(value = "/saleQuarter.do", method = RequestMethod.GET) // 연도, 분기 별 매출
	public String yearQuarter(OrderVO oVO, Model model, @RequestParam("year")int year) {
		// request.getParameter("year"); 
		// request.getParameter("quater"); setTmpQuater

			oVO.setSk("YEAR_QUARTER_SALE");
			List<OrderVO> yearQuarterOdatas =orderService.selectAll(oVO);
			
			model.addAttribute("year", year);
			model.addAttribute("yearQuarterOdatas", yearQuarterOdatas);
			System.out.println("로그 : yearQuarterOdatas : " + yearQuarterOdatas);

		return "adminSaleQuarter.jsp" ; // 
	}


}
