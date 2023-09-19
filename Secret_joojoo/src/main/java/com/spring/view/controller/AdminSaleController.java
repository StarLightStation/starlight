package com.spring.view.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;
import com.spring.biz.orderdetail.OrderdetailService;
import com.spring.biz.orderdetail.OrderdetailVO;

@Controller
public class AdminSaleController {

	@Autowired
	OrderService orderService;
	@Autowired
	OrderdetailService orderdetailService;

	//==================================================[매출 관리]==================================================

	/*
	 * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : admin.tag
	 * 리턴 값 : yearOdatas (연도 매출) ,totalOdata (연도 총 매출) / 세션 : X
	 * 기능 : 누적 판매 개수 / 금액 옵션 (adminSale.jsp 이동)
	 */

	@RequestMapping(value = "/sale.do", method = RequestMethod.GET) // 매출관리 첫 페이지 이동
	public String salePage(OrderVO oVO, Model model) {

		oVO.setSk("YEAR_SALE"); // 연도 매출
		List<OrderVO> yearOdatas = orderService.selectAll(oVO);

		oVO.setSk("TOTAL"); // 전체 총 매출 
		oVO = orderService.selectOne(oVO);

		model.addAttribute("yearOdatas", yearOdatas);
		model.addAttribute("totalOdata", oVO);

		return "adminSale.jsp"; // 매출관리.jsp 이동

	}

	// ====================================================================================================

	//==================================================[매출 관리 - 분기별 매출]=========================================

	/*
	 * 요청 값 : VO에 존재 : year (oVO)  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : adminSale.jsp
	 * 리턴 값 : year (해당 연도), yearQuarterOdatas (분기별 상품 매출, 구독 매출, 총 매출) / 세션 : X
	 * 기능 : 분기별 매출 (adminSaleQuarter.jsp 로 이동)
	 */

	@RequestMapping(value = "/saleQuarter.do", method = RequestMethod.GET) // 연도, 분기 별 매출
	public String yearQuarter(OrderVO oVO, Model model, @RequestParam("year") int year) {

		oVO.setSk("YEAR_QUARTER_SALE"); // 해당연도에 따른 분기별 상품매출, 구독매출 , 총매출 
		List<OrderVO> yearQuarterOdatas = orderService.selectAll(oVO);

		model.addAttribute("year", year); // 해당 연도
		model.addAttribute("yearQuarterOdatas", yearQuarterOdatas); // 분기별 상품매출 , 구독매출 , 총매출 

		return "adminSaleQuarter.jsp";
	}

	// ====================================================================================================

	//==================================================[회원 결제 내역 확인]==================================================

	/*
	 * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : adminSale.jsp, adminSaleQuarter.jsp, adminProductRank.jsp
	 * 리턴 값 : memberTotalDatas (회원 아이디, 회원의 총 누적 결제 금액) / 세션 : X
	 * 기능 : 회원 결제 내역 보여주기. (adminSaleRank.jsp 이동)
	 */

	@RequestMapping(value = "/saleRank.do") // 매출 순위 페이지 들어올때
	public String memberRank(OrderVO oVO, Model model, HttpSession session) {

		oVO.setSk("MEMBER_TOTAL"); // 회원 아이디, 회원의 총 누적 결제 금액
		List<OrderVO> memberTotalDatas = orderService.selectAll(oVO);

		model.addAttribute("memberTotalDatas", memberTotalDatas);

		return "adminSaleRank.jsp";

	}

	// ====================================================================================================

	//==================================================[회원 상세 매출 확인]==================================================

	/*
	 * 요청 값 : VO에 존재 : mID / 세션 : X / VO에 없음 : X
	 * 요청 페이지 : adminSaleRank.jsp
	 * 리턴 값 : modalDatas (회원의 연도 분기별 상품 매출, 구독 매출, 총 매출) / 세션 : X
	 * 기능 : 회원 상세 매출 확인. (요청 페이지로 이동)
	 */

	@RequestMapping(value = "/saleRankModal.do", method = RequestMethod.POST)
	@ResponseBody
	public String saleRankModal(@RequestParam("mID") String mID, OrderVO oVO, Gson gson) {

		// mID 에 대한 키를 주면 그에 해당하는 정보를 가져온다.
		oVO.setSk("MEMBER_SALE"); // 회원의 연도 분기별 상품 매출 , 구독 매출 , 총 매출 
		List<OrderVO> modalDatas = orderService.selectAll(oVO);

		return gson.toJson(modalDatas); // 비동기 처리 json 타입으로 변환해서 보낸다. 
	}

	// ====================================================================================================

	//==================================================[많이 팔린 상품 순 확인]==================================================

	/*
	 * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : adminSaleRank.jsp
	 * 리턴 값 : productDatas (누적 판매 개수 순위) / 세션 : X
	 * 기능 : 회원 상세 매출 확인. (adminProductRank.jsp 이동)
	 */

	@RequestMapping(value = "/productRank.do")
	public String productRank(OrderdetailVO odVO, Model model) {

		odVO.setSk("CNT");
		List<OrderdetailVO> productDatas = orderdetailService.selectAll(odVO);

		model.addAttribute("productDatas", productDatas);

		return "adminProductRank.jsp";
	}

	// ====================================================================================================

	//==================================================[누적 판매 개수/금액 옵션]==================================================

	/*
	 * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : rankOption
	 * 요청 페이지 : adminProductRank.jsp
	 * 리턴 값 : rankOption (누적 판매 개수 순위) ,rankOptionDatas (누적 판매 액수 순위) / 세션 : X
	 * 기능 : 누적 판매 개수 / 금액 옵션 (adminProductRank.jsp 로 이동)
	 */

	@RequestMapping(value = "/productRankOption.do")
	public String productRankOption(@RequestParam("productRankOption") int rankOption, OrderdetailVO odVO, Model model) {

		List<OrderdetailVO> rankOptionDatas = new ArrayList<OrderdetailVO>(); // 누적 판매 순 리스트

		if (rankOption == 1) { // rankOption 이 1 이라면
			odVO.setSk("CNT"); // 누적 판매 개수 순
			rankOptionDatas = orderdetailService.selectAll(odVO);
		} else { // rankOption 이 2 라면
			odVO.setSk("TOTAL"); // 누적 판매 액수 순
			rankOptionDatas = orderdetailService.selectAll(odVO);
		}

		model.addAttribute("rankOption", rankOption); // 1 또는 2의 숫자
		model.addAttribute("rankOptionDatas", rankOptionDatas); // 순위에 따른 정렬
		
		return "adminProductRank.jsp";

	}

}