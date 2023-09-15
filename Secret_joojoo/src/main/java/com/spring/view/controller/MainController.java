package com.spring.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;

@Controller
public class MainController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	MemberService memberService;

    //	================================================== [ 메인 페이지 ] ==================================================

    @RequestMapping(value = "/main.do")
    public String main() {    //	메인 페이지 이동.

        System.out.println("log : MainController : main()");

        return "redirect:main.jsp";
    }

    //	===================================================================================================================

    //	================================================== [ 관리자 메인 페이지 ] ==================================================

    @RequestMapping(value = "/adminMain.do")
    public String adminMain(MemberVO mVO, OrderVO oVO, Model model) {    //	관리자 메인 페이지 이동.

        System.out.println("log : MainController : adminMain()");
        
        oVO.setSk("YEAR_QUARTER_SALE_ALL"); // 연도별 분기별 총 액 
        List<OrderVO> yearQuarterSaleAll = orderService.selectAll(oVO);
        
        oVO.setSk("WEEK_SALE");// 최근 1주일 매출 
        List<OrderVO> weekSale =orderService.selectAll(oVO);
        
        oVO.setSk("TODAY_TOTAL"); // 오늘 매출 
        OrderVO todayTotal =orderService.selectOne(oVO);
        
        oVO.setSk("YESTERDAY_TOTAL"); // 오늘 매출 
        OrderVO yesterdayTotal =orderService.selectOne(oVO);
        
        oVO.setSk("TODAY_PRODUCTPRICE"); // 오늘 상품 매출
        OrderVO todayProductprice =orderService.selectOne(oVO);
        
        oVO.setSk("TODAY_SUBSPRICE"); // 오늘 구독 매출
        OrderVO todaySubsprice=orderService.selectOne(oVO);
        
        
        mVO.setSk("COUNT"); // 총 회원 수 
        mVO = memberService.selectOne(mVO);
        System.out.println(mVO);
        
        
        model.addAttribute("count", mVO);
        model.addAttribute("yearQuarterSaleAll", yearQuarterSaleAll);
        model.addAttribute("todayTotal", todayTotal);
        model.addAttribute("yesterdayTotal", yesterdayTotal);
        model.addAttribute("todayProductprice", todayProductprice);
        model.addAttribute("todaySubsprice", todaySubsprice);
        model.addAttribute("weekSale", weekSale);
        
        return "adminMain.jsp";
    }

    //	=========================================================================================================================

    
    //	================================================== [ 관리자 메인 페이지 ] ==================================================

    @RequestMapping(value = "/sweetAlert.do")
    public String sweetAlert() {    //	관리자 메인 페이지 이동.

        System.out.println("log : MainController : sweetAlert()");
        
        
        return "sweetAlert.jsp";
    }

    //	=========================================================================================================================

    
}    //	MainController