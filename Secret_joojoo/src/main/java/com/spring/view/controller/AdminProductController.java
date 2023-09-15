package com.spring.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

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
   // ===============================================[관리자 - 상품 목록
   // 페이지]=================================================

   /*
    * 요청 값: VO에 존재 : x / 세션: x / VO에 없음: x 요청 페이지: admin.tag 리턴 값: model :
    * pdatas(상품 목록들) / 세션 : X 기능: admin-product.jsp에 상품 목록 출력
    */
   @RequestMapping(value = "/adminProduct.do")
   public String adminProduct(ProductVO pVO, Model model) {
      pVO.setSk("ALL");
      model.addAttribute("pdatas", productService.selectAll(pVO));// View로 상품 목록 전달

      return "adminProduct.jsp"; // 관리자 상품 목록 페이지
   }
   // ====================================================================================================
   // ===============================================[관리자 - 상품 편집
   // 페이지]=================================================

   /*
    * 요청 값: VO에 존재 : pNum / 세션: x / VO에 없음: x 요청 페이지: admin-product.jsp 리턴 값: model
    * : pdata(상품 하나의 정보) / 세션 : X 기능: admin-productUpdatePage.jsp로 이동
    */
   @RequestMapping(value = "/adminProductUpdatePage.do")
   public String adminProductUpdatePage(ProductVO pVO, Model model) {
      pVO = productService.selectOne(pVO);// 상품 하나 정보 가져오기
      model.addAttribute("pdata", pVO);// View 로 상품 하나 정보 전달

      return "adminProductUpdatePage.jsp"; // 관리자 상품 목록 페이지
   }
   // ====================================================================================================
   // ===============================================[관리자 - 상품 등록
   // 페이지]=================================================

   /*
    * 요청 값: VO에 존재 : x / 세션: x / VO에 없음: x 요청 페이지: buyPage.jsp 리턴 값: model : / 세션 :
    * 기능:
    */
   @RequestMapping(value = "/adminProductInsertPage.do")
   public String adminPrdouctInsertPage() {

      return "adminProductInsertPage.jsp"; // 관리자 상품 목록 페이지
   }
   // ====================================================================================================
   // ===============================================[관리자 -
   // 상품등록]=================================================

   /*
    * 요청 값: VO에 존재 : x / 세션: x / VO에 없음: x 요청 페이지: buyPage.jsp 리턴 값: model : / 세션 :
    * 기능:
    */
   @RequestMapping(value = "/adminProductInsert.do")
   public String adminProductInsert(ProductVO pVO) throws IllegalStateException, IOException {
      MultipartFile fileUpload1 = pVO.getFileUpload1();
      MultipartFile fileUpload2 = pVO.getFileUpload2();
      
      System.out.println("파일확인1 "+fileUpload1);
      System.out.println("파일확인2 "+fileUpload2);

      
      pVO.setSk("ALL");
      ArrayList<ProductVO> pdatas = (ArrayList<ProductVO>) productService.selectAll(pVO);
      String thumbName=fileUpload1.getOriginalFilename();
      String detail=fileUpload2.getOriginalFilename();
      String randFileNamethumbnail = Common.randomFileName(thumbName,pdatas.size());
      String randFileNameDetail = Common.randomFileName(thumbName,pdatas.size());
      
      // 파일 명에 pk+1 설정(아직 DB에 없기 때문에)
      System.out.println("파일명 : " + randFileNamethumbnail);
      System.out.println("파일명 : " + randFileNameDetail);

      if (fileUpload1 != null && fileUpload2 != null) {// 섬네일 && 상세이미지 둘 다 null값이 있어야 함

         pVO.setpImage(randFileNamethumbnail);// 섬네일 세팅
         fileUpload1.transferTo(new File( ProductVO.getFilePath()+ randFileNamethumbnail));// 폴더에 생성

         pVO.setpImagedetail(randFileNameDetail);// 상세 세팅
         fileUpload2.transferTo(new File( ProductVO.getFilePath() + randFileNameDetail));// 폴더에 생성
      }

      productService.insert(pVO);// 삽입
      return "redirect:adminProduct.do";
   }

   // ====================================================================================================
   // ===============================================[관리자 - 상품
   // 편집]=================================================

   /*
    * 요청 값: VO에 존재 : x / 세션: x / VO에 없음: x 요청 페이지: buyPage.jsp 리턴 값: model : / 세션 :
    * 기능:
    */
   @RequestMapping(value = "/adminProductUpdate.do")
   public String adminPrdouctUpdate(ProductVO pVO, Model model) {
      System.out.println("편집 들어옴 ");
      System.out.println("pNum: " + pVO.getpNum());
      pVO.setSk("UPDATEDETAIL");
      System.out.println("pVO : " + pVO);
      System.out.println(pVO.getSk());
      boolean flag = productService.update(pVO);
      productService.update(pVO);
      System.out.println(flag);
      return "redirect:adminProduct.do"; // 관리자 상품 목록 페이지
   }
   // ====================================================================================================
   // ===============================================[관리자 - 상품
   // 삭제]=================================================

   /*
    * 요청 값: VO에 존재 : x / 세션: x / VO에 없음: x 요청 페이지: buyPage.jsp 리턴 값: model : / 세션 :
    * 기능:
    */
   @RequestMapping(value = "/adminProductDelete.do")
   public String adminProudctDelete(ProductVO pVO) {

      productService.delete(pVO);// 상품 삭제

      return "redirect:adminProduct.do"; // 관리자 상품 목록 페이지
   }
   
   
   // ====================================================================================================
}