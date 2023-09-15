package com.spring.view.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.biz.product.ProductService;
import com.spring.biz.product.ProductVO;

@Controller
public class CartController {
	@Autowired
	ProductService productService; // pDAO

//===========================================[장바구니 페이지 이동]===================================================
	/*
	 * 요청 값 : VO에 존재 : X / 세션 : X / VO에 없음 : X 요청 페이지 : login.tag 리턴 값 : model : X /
	 * 세션 : X 기능 : 장바구니 페이지로 이동.
	 */
	@RequestMapping(value = "/cart.do")
	public String cartPage() {

		return "cart.jsp"; // 장바구니 페이지.
	}

// =============================================================================================================
//=============================================[장바구니 추가]======================================================
	/*
	 * 요청 값 : VO에 존재 : pNum / 세션 : X / VO에 없음 : tmpCnt 요청 페이지 : detail.jsp 리턴 값 :
	 * model : pNum / 세션 : cart 기능 : 상품 상세 페이지 에서 특정한 상품을 장바구니에 담고, 다시 상품 상세 페이지로
	 * 이동.
	 */
	@RequestMapping(value = "/cartAdd.do" )
	public String cartAdd(ProductVO pVO, HttpSession session, Model model) {

		List<ProductVO> cart = null;

		if (session.getAttribute("cart") == null) { // 장바구니가 세션에 없으면,
			cart = new ArrayList<ProductVO>(); // 장바구니 생성.
			session.setAttribute("cart", cart); // 세션으로 장바구니 관리.
		}

		cart = (ArrayList<ProductVO>) session.getAttribute("cart"); // 세션 장바구니.
		int tmpCnt = pVO.getTmpCnt(); // 상품 개수.
		pVO = productService.selectOne(pVO); // 상품 번호 (요청 값) 에 해당 되는 상품.

		if (cart.contains(pVO)) { // 장바구니 상품 중에서, 상품 번호 (요청 값) 를 포함 하면,
			int index = cart.indexOf(pVO); // 상품 인덱스.
			int cartTmpCnt = cart.get(index).getTmpCnt(); // 기존 상품 개수 저장.
			cart.remove(index); // 해당 상품을 삭제.
			pVO.setTmpCnt(cartTmpCnt + tmpCnt); // 상품 개수 + 기존 상품 개수.
			cart.add(pVO); // 장바구니에 저장.
		} else { // 상품 번호 (요청 값) 를 포함 하고 있는 상품이 장바구니에 없다면,
			pVO.setTmpCnt(tmpCnt); // 장바구니에 담은 상품 개수 저장.
			cart.add(pVO); // 장바구니에 저장.
		}

		session.setAttribute("cart", cart); // 새 장바구니 세션에 갱신.
		model.addAttribute("pNum", pVO.getpNum()); // 상품 상세 페이지로 상품 번호 전송.

		return "detail.do"; // 상품 상세 페이지.
	}

// =============================================================================================================
//=============================================[장바구니 수정]======================================================	
	/*
	 * 요청 값 : VO에 존재 : pNum / 세션 : X / VO에 없음 : tmpCnt 요청 페이지 : cart.jsp 리턴 값 :
	 * model : X / 세션 : cart 기능 : 비동기처리 방식 으로, 장바구니에 담아 놓은 특정한 상품의 개수 변경.
	 */
	@RequestMapping(value = "/update.do")
	@ResponseBody
	public String cartUpdate(ProductVO pVO, HttpSession session, @RequestParam Map<String, Object> map) {

		ArrayList<ProductVO> cart = (ArrayList<ProductVO>) session.getAttribute("cart"); // 기존 세션 장바구니 저장.

		if (cart.contains(pVO)) { // 장바구니 상품 중에서, 상품 번호 (요청 값) 를 포함 하면,
			int index = cart.indexOf(pVO); // 상품 인덱스.
			cart.get(index).setTmpCnt(pVO.getTmpCnt()); // 상품 개수 갱신.
		}

		session.removeAttribute("cart"); // 기존 세션 장바구니 삭제.
		session.setAttribute("cart", cart); // 새 장바구니 세션에 갱신.

		return "ok";
	}

// =============================================================================================================
//=============================================[장바구니 삭제]======================================================
	/*
	 * 요청 값 : VO에 존재 : pNum / 세션 : X / VO에 없음 : X 요청 페이지 : cart.jsp 리턴 값 : model : X
	 * / 세션 : cart 기능 : 비동기처리 방식 으로, 장바구니에 담아 놓은 특정한 상품을 삭제.
	 */
	@RequestMapping(value = "/cartDelete.do")
	public String cartDelete(ProductVO pVO, HttpSession session, @RequestParam("pNum") int pNum) {

		ArrayList<ProductVO> cart = (ArrayList<ProductVO>) session.getAttribute("cart"); // 기존 세션 장바구니 저장.

		if (cart.contains(pVO)) { // 장바구니 상품 중에서, 상품 번호 (요청 값) 를 포함 하면,
			int index = cart.indexOf(pVO); // 상품 인덱스.
			cart.remove(index); // 해당 상품을 삭제.

			session.removeAttribute("cart"); // 기존 세션 장바구니 삭제.
			session.setAttribute("cart", cart); // 새 장바구니 세션에 갱신.
		}

		return "ok";
	}
// =============================================================================================================

}
