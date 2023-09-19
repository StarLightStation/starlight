package com.spring.view.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;
import com.spring.biz.orderdetail.OrderdetailService;
import com.spring.biz.orderdetail.OrderdetailVO;

@Controller
public class MyPageOrderReviewController {

    @Autowired
    BoardService boardService; // bDAO
    @Autowired
    OrderService orderService; // oDAO
    @Autowired
    OrderdetailService orderdetailService; // odDAO

    //==================================================[마이페이지 구매 내역]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 : mypage.tag (member.jsp)
     * 리턴 값 : model : orderDatas (특정 회원이 주문한 상품 정보 + 특정 회원이 주문한 상품 상세 정보 데이터) / 세션 : X
     * 기능 : 주문 내역 확인 ( orderHistory.jsp 이동 )
     */

    @RequestMapping(value = "/orderHistory.do", method = RequestMethod.GET)
    public String orderHistory(OrderVO oVO, OrderdetailVO odVO, HttpSession session, Model model) {

        List<OrderdetailVO> oddatas = new ArrayList<OrderdetailVO>();    //	주문 상세 목록을 위한 리스트
        Map<OrderVO, List<OrderdetailVO>> orderMap = new LinkedHashMap<OrderVO, List<OrderdetailVO>>();    //	주문 목록 전체를 보내주기 위한 Map(주문 번호 : 주문 상세 목록)
        
        oVO.setmID((String) session.getAttribute("mID"));    //	필요한 값인 mID 세팅(세션에서 가져옴)
        oVO.setSk("MYORDER");    //	주문 번호를 가져오기 위한 sk 세팅
        List<OrderVO> odatas = orderService.selectAll(oVO);    //	가져온 주문 번호를 리스트에 저장(여러개이기 때문에, 몇개가 나올지 모르기 때문에)
       
        odVO.setSk("ORDERDETAIL");    //	주문 상세 목록을 가져오기 위한 sk 세팅
        for (OrderVO v : odatas) {    //	주문 번호에 대한 주문 상세목록 값을 가져오기 위해서 반복
            odVO.setoNum(v.getoNum());    //	주문 번호 세팅
            oddatas = orderdetailService.selectAll(odVO);    //	주문 번호에 대한 주문 상세 목록을 리스트에 저장
            orderMap.put(v, oddatas);    //	Map에다가 값을 저장
        }

        model.addAttribute("orderDatas", orderMap);    //	저장된 Map을 view로 전달

        return "orderHistory.jsp";
    }

    //=====================================================================================================================

    //==================================================[마이페이지 리뷰 수정]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 : updateReview.jsp / writeReview.jsp
     * 리턴 값 : model : bdatas (본인이 작성한 리뷰 데이터들) / 세션 : X
     * 기능 : 리뷰 수정 ( updateReview.jsp 이동 )
     */

    @RequestMapping(value = "/updateReview.do", method = RequestMethod.GET)
    public String updateReview(BoardVO bVO, HttpSession session, Model model) {

    	bVO.setmID((String) session.getAttribute("mID"));    //	세션 mID
        bVO.setSk("MYPAGE");    //	회원의 내가 작성한 리뷰 불러오기
        List<BoardVO> bdatas = boardService.selectAll(bVO);    //	mID	//	회원의 내가 작성한 리뷰 모두 불러오기

        model.addAttribute("bdatas", bdatas);    //	회원의 내가 작성한 리뷰 정보 보내기

        return "updateReview.jsp";    //	리뷰 수정 페이지로 이동
    }

    //====================================================================================================================

    //==================================================[마이페이지 리뷰 수정 페이지 이동]==================================================

    /*
     * 요청 값 : VO에 존재 : bNum, bContent, bStar  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 : updateReview.jsp
     * 리턴 값 : model : X / 세션 : X
     * 기능 : 리뷰 수정 완료 ( updateReview.do 이동 / 실패시 goback.jsp )
     */

    @RequestMapping(value = "/updateReview.do", method = RequestMethod.POST)
    public String updateReviewOK(BoardVO bVO) {

        boardService.update(bVO);    //	bNum,bContent,bStar 	//	세션 mID	//	리뷰 수정

        return "redirect:updateReview.do";    //	리뷰 수정 완료 후 다시 리뷰 수정 페이지로 이동
    }

    //==============================================================================================================================

    //==================================================[마이페이지 리뷰 작성 페이지 이동]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 : mypage.tag(member.jsp) / updateReview.jsp / writeReview.jsp
     * 리턴 값 : model : oddatas (본인의 작성 가능한 리뷰 데이터들) / 세션 : X
     * 기능 : 리뷰 작성 ( writeReview.jsp 이동 )
     */

    @RequestMapping(value = "/writeReview.do", method = RequestMethod.GET)
    public String writeReview(OrderdetailVO odVO, HttpSession session, Model model) {

    	odVO.setmID((String) session.getAttribute("mID"));    //	세션 mID
        odVO.setSk("REVIEW");    //	회원의 내가 작성할 리뷰 불러오기
        List<OrderdetailVO> oddatas = orderdetailService.selectAll(odVO);    //	mID	//	회원의 내가 작성할 리뷰 모두 불러오기

        model.addAttribute("oddatas", oddatas);    //	회원의 내가 작성할 리뷰 정보 보내기

        return "writeReview.jsp";    //	리뷰 작성 페이지로 이동
    }

    //==============================================================================================================================

    //==================================================[마이페이지 리뷰 작성]==================================================

    /*
     * 요청 값 : VO에 존재 : pNum, odNum, bStar, bContent  / 세션 : X  / VO에 없음 : X
     * 요청 페이지: writeReview.jsp
     * 리턴 값 : model : / 세션 : X
     * 기능: 리뷰 수정 완료 (updatereview.do 이동 / 실패시 goback.jsp )
     */

    @RequestMapping(value = "/writeReview.do", method = RequestMethod.POST)
    public String writeReviewOK(BoardVO bVO, OrderdetailVO odVO) {

        boardService.insert(bVO); // pNum, odNum, bStar, bContent // 리뷰 작성

        orderdetailService.update(odVO); // 리뷰 작성완료 시 주문상세 테이블에서 "작성완료"로 바꿔주기

        return "redirect:updateReview.do"; // 리뷰 수정 페이지로 이동
    }

    //=====================================================================================================================

    //==================================================[마이페이지 리뷰 삭제]==================================================

    /*
     * 요청 값 : VO에 존재 : bNum  / 세션 : X  / VO에 없음 : X
     * 요청 페이지 : updateReview.jsp
     * 리턴 값 : model : X / 세션 : X
     * 기능 : 리뷰를 삭제하는 기능 ( updatereview.do 로 이동)
     */

    @RequestMapping(value = "/deleteReview.do", method = RequestMethod.GET)    //	매핑
    public String deleteReview(BoardVO bVO) {

        boardService.delete(bVO);    //	bNum	//	리뷰 삭제

        return "redirect:updateReview.do";    //	리뷰 삭제 후 리뷰 수정 페이지로 이동
    }

    //=====================================================================================================================

}    //	MypageOrderReviewController
