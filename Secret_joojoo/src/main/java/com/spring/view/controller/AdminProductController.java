package com.spring.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.biz.common.Common;
import com.spring.biz.product.ProductService;
import com.spring.biz.product.ProductVO;

@Controller
public class AdminProductController {

	@Autowired
	ProductService productService; // pDAO
	
	// ===============================================[상품 목록 페이지]=================================================

	/*
	 * 요청 값 : VO에 존재 : X / 세션 : X / VO에 없음 : X 
	 * 요청 페이지 : admin.tag 
	 * 리턴 값 : model : pdatas (상품 목록들) / 세션 : X 
	 * 기능 : adminProduct.jsp 에 상품 목록 출력
	 */

	@RequestMapping(value = "/adminProduct.do")
	public String adminProduct(ProductVO pVO, Model model) {

		pVO.setSk("ALL");
		model.addAttribute("pdatas", productService.selectAll(pVO));	// View로 상품 목록 전달

		return "adminProduct.jsp"; // 관리자 상품 목록 페이지
	}
	
	// ====================================================================================================
	
	// ===============================================[상품 편집 페이지]=================================================

	/*
	 * 요청 값 : VO에 존재 : pNum / 세션 : X / VO에 없음 : X 
	 * 요청 페이지 : adminProduct.jsp 
	 * 리턴 값 : model : pdata (상품 하나의 정보) / 세션 : X 
	 * 기능 : 상품 업데이트 adminProductUpdatePage.jsp 로 이동
	 */
	
	@RequestMapping(value = "/adminProductUpdatePage.do")
	public String adminProductUpdatePage(ProductVO pVO, Model model) {
		
		pVO = productService.selectOne(pVO);	// 상품 하나 정보 가져오기
		
		model.addAttribute("pdata", pVO);	// View 로 상품 하나 정보 전달

		return "adminProductUpdatePage.jsp"; // 관리자 상품 목록 페이지
	}
	
	// ====================================================================================================
	
	// ===============================================[상품 등록 페이지]=================================================

	/*
	 * 요청 값 : VO에 존재 : X / 세션 : X / VO에 없음 : X 
	 * 요청 페이지 : adminProduct.jsp 
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : adminProductInsertPage.jsp 로 이동
	 */
	
	@RequestMapping(value = "/adminProductInsertPage.do")
	public String adminPrdouctInsertPage() {

		return "adminProductInsertPage.jsp"; // 관리자 상품 목록 페이지
	}
	
	// ====================================================================================================
	
	// ===============================================[상품 등록]=================================================

	/*
	 * 요청 값 : VO에 존재 : fileUpload1, fileUpload2, 상품의 모든 정보  / 세션 : X / VO에 없음 : X 
	 * 요청 페이지 : adminProductInsertPage.jsp
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : 상품 등록
	 */
	
	@RequestMapping(value = "/adminProductInsert.do")
	public String adminProductInsert(ProductVO pVO) throws IllegalStateException, IOException {
		
		MultipartFile fileUpload1 = pVO.getFileUpload1();
		MultipartFile fileUpload2 = pVO.getFileUpload2();

		System.out.println("파일확인1 " + fileUpload1);
		System.out.println("파일확인2 " + fileUpload2);

		pVO.setSk("ALL");
		ArrayList<ProductVO> pdatas = (ArrayList<ProductVO>) productService.selectAll(pVO);
		String thumbName=fileUpload1.getOriginalFilename();
		String detail=fileUpload2.getOriginalFilename();
		String randFileNamethumbnail = Common.randomFileName(thumbName,pdatas.size());
		String randFileNameDetail = Common.randomFileName(thumbName,pdatas.size());

		//	파일 명에 pk + 1 설정 (아직 DB에 없기 때문에)
		System.out.println("파일명 : " + randFileNamethumbnail);
		System.out.println("파일명 : " + randFileNameDetail);

		if (fileUpload1 != null && fileUpload2 != null) {	//	섬네일 && 상세이미지 둘 다 null값이 있어야 함

			pVO.setpImage(randFileNamethumbnail);	// 섬네일 세팅
			fileUpload1.transferTo(new File( ProductVO.getFilePath()+ randFileNamethumbnail));	// 폴더에 생성

			pVO.setpImagedetail(randFileNameDetail);	// 상세 세팅
			fileUpload2.transferTo(new File( ProductVO.getFilePath() + randFileNameDetail));	// 폴더에 생성
		}

		productService.insert(pVO);	// 삽입
		return "redirect:adminProduct.do";
	}

	// ====================================================================================================
	
	// ===============================================[상품 편집]=================================================

	/*
	 * 요청 값 : VO에 존재 : 상품의 모든 정보 / 세션 : X / VO에 없음 : X 
	 * 요청 페이지 : adminProductUpdatePage.jsp
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : 상품의 모든 데이터 수정 하기
	 */
	
	@RequestMapping(value = "/adminProductUpdate.do")
	public String adminPrdouctUpdate(ProductVO pVO, Model model) {
		
		pVO.setSk("UPDATEDETAIL");
		productService.update(pVO);
		
		return "redirect:adminProduct.do"; // 관리자 상품 목록 페이지
	}
	
	// ====================================================================================================
	
	// ===============================================[상품 삭제]=================================================

	/*
	 * 요청 값 : VO에 존재 : pNum / 세션 : X / VO에 없음 : X 
	 * 요청 페이지 : adminProduct.jsp
	 * 리턴 값 : model : X / 세션 : X
	 * 기능 : 상품 삭제
	 */
	
	@RequestMapping(value = "/adminProductDelete.do")
	public String adminProudctDelete(ProductVO pVO) {

		productService.delete(pVO);	// 상품 삭제

		return "redirect:adminProduct.do"; // 관리자 상품 목록 페이지
	}


	// ====================================================================================================
	
}