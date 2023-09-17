package com.spring.view.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.declaration.DeclarationService;
import com.spring.biz.declaration.DeclarationVO;
import com.spring.biz.product.ProductService;
import com.spring.biz.product.ProductVO;

@Controller
public class SearchAndStore {
	@Autowired
	BoardService boardService;

	@Autowired
	ProductService productService;

	@Autowired
	DeclarationService declarationService;

	//============================================[검색 페이지 이동]====================================================
	/*
	 * 요청 값: VO에 존재 : X  / 세션: X  / VO에 없음: X
	 * 요청 페이지: login.tag
	 * 리턴 값: model : X / 세션 : X
	 * 기능: search.jsp 로 이동
	 */
	@RequestMapping(value = "/search.do",  method = RequestMethod.GET)
	public String searchPage(ProductVO pVO, Model model) {
		pVO.setSk("ALL");
		int maxSize = productService.selectAll(pVO).size();
		model.addAttribute("maxSize", maxSize);
		return "search.jsp";
	}
	// =============================================================================================================

	//============================================[검색 페이지 비동기]====================================================
	/*
	 * 요청 값: VO에 존재 : pName / 세션: X  / VO에 없음: X
	 * 요청 페이지:search.jsp
	 * 리턴 값: model : gson.toJson(pdatas)(json으로 형변환된 검샘된 상품 정보들) / 세션 : X
	 * 기능: 비동기로 검색된 상품 정보들 전달
	 */

	// produces는 C->V 갈때
	// response에 setCharacter << JSP에서 CTRL script xxx 화면에 뿌릴 DOC에 대해서
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	@ResponseBody
	public String search(ProductVO pVO, Gson gson) {
		pVO.setSk("FILTER");
		List<ProductVO> pdatas = productService.selectAll(pVO); // 검색어가 포함된 상품 정보들

		return gson.toJson(pdatas); // 상품 정보들을 Json으로 변환
	}

	// =============================================================================================================

	//============================================[필터 검색 페이지 이동]====================================================
	/*
	 * 요청 값: VO에 존재 : pCategory, pSweet, pSour, pSparkle, pAlcohol, pName  / 세션: X  / VO에 없음: X
	 * 요청 페이지: search.jsp / store.jsp
	 * 리턴 값: model : pdatas(검색된 상품 정보들) / 세션 : X
	 * 기능: 검색된 상품 정보들 출력(카테고리, 단맛, 신맛, 탄산감, 도수, 검색단어)
	 */

	@RequestMapping(value = "/searchFilter.do")
	public String search(ProductVO pVO, Model model) {
		pVO.setSk("FILTER");
		List<ProductVO> pdatas = productService.selectAll(pVO); // 검색된 상품 정보들
		model.addAttribute("pdatas", pdatas); // View로 검색 된 상품 정보들 전달

		return "searchFilter.jsp";
	}
	// =============================================================================================================
	//============================================[스토어 페이지 이동]====================================================
	/*
	 * 요청 값: VO에 존재 : X  / 세션: X  / VO에 없음: X
	 * 요청 페이지: login.tag
	 * 리턴 값: model : pdatas(상품 정보들) / 세션 : X
	 * 기능:stroe.jsp 로 이동
	 */
	@RequestMapping(value = "/store.do")
	public String store(ProductVO pVO, Model model) {
		pVO.setSk("FILTER");
		List<ProductVO> pdatas = productService.selectAll(pVO); // 상품 정보들
		model.addAttribute("pdatas", pdatas); // View로 상품 정보들 전달

		return "store.jsp";
	}
	// =============================================================================================================
	//============================================[스토어 상세 페이지 이동]====================================================
	/*
	 * 요청 값: VO에 존재 : pNum  / 세션: X  / VO에 없음: X
	 * 요청 페이지: searchFilter.jsp , store.jsp
	 * 리턴 값: model : pdata(상품 하나 정보) , bdatas(리뷰 정보들) / 세션 : X
	 * 기능:상품 하나의 정보 출력
	 */
	@RequestMapping(value="/detail.do")
	public String detail(BoardVO bVO, DeclarationVO dVO, ProductVO pVO, Model model, HttpSession session){

		pVO = productService.selectOne(pVO);//상품 하나 정보 

		if(pVO == null) {
			return "fallback/goback.jsp";
		}
		
		bVO.setSk("PRODUCT");//필요한 SK 세팅청
		model.addAttribute("pdata",pVO);//View로 상품 정보 전달

		String mID=(String) session.getAttribute("mID"); 
		dVO.setmID(mID);

		List<BoardVO> bdatas = boardService.selectAll(bVO);//가져온 상품에 대한 리뷰 정보들
		System.out.println(dVO);
		dVO.setSk("MEMBER_DECLARATION");
		List<DeclarationVO> ddatas = declarationService.selectAll(dVO);// 신고 당한 리뷰 정보들

		System.out.println("1111 : "+bVO);
		System.out.println("2222 : "+bdatas);
		System.out.println("3333 : "+ddatas);

		if(bdatas != null) { //리뷰가 존재 한다면
			for(int i=0; i<bdatas.size(); i++) { 
				bdatas.get(i).setCheck(false);  // 신고 버튼 활성화 셋팅
				for(int j=0; j<ddatas.size(); j++) {
					if(bdatas.get(i).getbNum()==ddatas.get(j).getbNum()) { // 신고한 내역이 있다면,
						bdatas.get(i).setCheck(true); // 신고 버튼 비활성화 셋팅
						break;
					}
				}
				System.out.println(bdatas.get(i).isCheck());
			}	
			model.addAttribute("bdatas",bdatas);//View로 리뷰 정보 전달
		}
		return "detail.jsp";
	}
	// =============================================================================================================

}
