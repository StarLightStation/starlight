package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;

@Controller
public class SaleRankModalController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/saleRankModal.do", method=RequestMethod.POST)
	@ResponseBody // 뷰 리졸버가 작동 안하게 하기 위해 
	public String saleRankModal(@RequestParam("mID")String mID, OrderVO oVO, Gson gson) {
					// 이미 setter 되서 mid는 들어와있는데 RequestParam은 내가 사용하거나 담을 변수가 필요하다면 쓰는것 
		System.out.println("로그  mID : " + mID);
		
		// mID 에 대한 키를 주면 그에 해당하는 정보를 가져온다.
		oVO.setSk("MEMBER_SALE");
		List<OrderVO> modalDatas = orderService.selectAll(oVO); 

		System.out.println("로그로그 :  modalDatas : "+ modalDatas);
		return gson.toJson(modalDatas);
	}
}
