package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.declaration.DeclarationService;
import com.spring.biz.declaration.DeclarationVO;
import com.spring.biz.product.ProductVO;

@Controller
public class AdminReviewController {

	@Autowired
	DeclarationService declarationService;
	@Autowired
	BoardService boardService;

	//==========================================[ 관리자 - 리뷰 신고 전체 목록 ]=========================================

	/*
	 * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : admin.tag
	 * 리턴 값 : model : ddatas (신고된 리뷰 정보들) / 세션 : X
	 * 기능 : 신고된 리뷰 전체 목록
	 */

	@RequestMapping(value = "/adminReview.do")
	public  String adminReview(DeclarationVO dVO, Model model) {

		dVO.setSk("ALL");
		List<DeclarationVO> ddatas = declarationService.selectAll(dVO); // 신고된 리뷰 정보들

		model.addAttribute("ddatas", ddatas); // View로 신고된 리뷰 정보들 전달

		return "adminReviewPage.do"; 
	}

	// ======================================================================================================

	//======================================[ 관리자 - 리뷰 신고 목록 이동페이지 ]=========================================  

	/*
	 * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : X
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : 신고된 리뷰 전체 목록 이동 페이지
	 */

	@RequestMapping(value="/adminReviewPage.do")
	public String adminReviewPage() {

		return "adminReview.jsp";
	}

	// ========================================================================================================

	//============================================[ 관리자 - 리뷰 신고 상세 ]==========================================   

	/*
	 * 요청 값 : VO에 존재 : dNum  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : adminReview.jsp
	 * 리턴 값 : model : ddata (신고된 리뷰 하나 정보) / 세션 : X
	 * 기능 : 해당 신고된 리뷰 상세 페이지
	 */

	@RequestMapping(value="/adminReviewDetail.do")
	public String adminReviewDetailPage(DeclarationVO dVO, Model model) {

		dVO = declarationService.selectOne(dVO); // 해당 신고된 리뷰 정보 하나

		model.addAttribute("ddata", dVO); // View로 해당 신고된 리뷰 정보 하나 전달

		return "adminReviewDetailPage.do";
	}

	// ======================================================================================================

	//======================================[ 관리자 - 리뷰 신고 상세 이동페이지 ]===========================================

	/*
	 * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : X
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : 해당 신고된 리뷰 상세페이지 이동 페이지
	 */

	@RequestMapping(value="/adminReviewDetailPage.do")
	public String adminReviewDetailPage() {
		return "adminReviewDetail.jsp";
	}

	// ======================================================================================================

	//============================================[ 관리자 - 신고 리뷰 삭제 ]==========================================
	
	/*
	 * 요청 값 : VO에 존재 : bNum, dNum  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : adminReviewDetail.jsp
	 * 리턴 값 : X / 세션 : X
	 * 기능 : 신고된 리뷰 삭제
	 */
	
	@RequestMapping(value="/adminReviewDelete.do")
	public String adminReviewDelete(BoardVO bVO, DeclarationVO dVO, Model model) {

		boardService.delete(bVO); // 리뷰 테이블에서 삭제
		declarationService.delete(dVO); // 신고 테이블에서 삭제

		return "redirect:adminReview.do";
	}
	
	// ======================================================================================================

	//============================================[ 관리자 - 신고 리뷰 철회 ]==========================================
	
	/*
	 * 요청 값 : VO에 존재 : dNum  / 세션 : X  / VO에 없음 : X
	 * 요청 페이지 : adminReviewDetail.jsp
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : 신고된 리뷰에서 철회
	 */
	
	@RequestMapping(value="/adminReviewWithdraw.do")
	public String adminReviewDelete(DeclarationVO dVO, Model model) {

		declarationService.delete(dVO); // 신고 테이블에서 삭제

		return "redirect:adminReview.do";
	}
	
	// ======================================================================================================

	//============================================[ 관리자 - 신고 추가 ]==========================================
	
	/*
	 * 요청 값 : VO에 존재 : bNum, pNum / 세션 : mID  / VO에 없음 : X
	 * 요청 페이지 : detail.jsp
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : 신고 테이블에 추가
	 */
	
	@RequestMapping(value="/declarationInsert.do")
	public String declarationInsert(DeclarationVO dVO, ProductVO pVO, HttpSession session) {

		dVO.setmID((String)session.getAttribute("mID")); // 세션의 mID를 dVO의 mID에 셋팅
		declarationService.insert(dVO); // 신고 테이블에 추가

		pVO.setpNum(pVO.getpNum()); // 받아온 pNum을 pVO의 pNum에 셋팅

		return "detail.do";
	}
	
	// ======================================================================================================

}