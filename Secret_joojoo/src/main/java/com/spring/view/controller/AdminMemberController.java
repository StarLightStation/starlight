package com.spring.view.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;
import com.spring.biz.orderdetail.OrderdetailService;
import com.spring.biz.orderdetail.OrderdetailVO;
import com.spring.biz.subsinfo.SubsinfoService;
import com.spring.biz.subsinfo.SubsinfoVO;

@Controller
public class AdminMemberController {    //  관리자 페이지 (회원 정보 관리)

    @Autowired
    BoardService boardService;  //  bDAO
    @Autowired
    MemberService memberService;    //  mDAO
    @Autowired
    OrderService orderService;  //  oDAO
    @Autowired
    OrderdetailService orderdetailService;   //  odDAO
    @Autowired
    SubsinfoService subsinfoService; //  subsinfoDAO

    //==================================================[관리자 페이지 회원 목록 출력]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : X  / VO에 없음 : X
     * 요청 페이지 : ?
     * 리턴 값 : model : mdatas (회원 정보들) / 세션 : X
     * 기능 : 회원 전체 목록 보여주기. (memberList.jsp 이동)
     */

    @RequestMapping(value = "/memberList.do")
    public String memberList(MemberVO mVO, Model model) {

        List<MemberVO> mdatas = memberService.selectAll(mVO);

        model.addAttribute("mdatas", mdatas);

        return "adminMemberList.jsp";    //  회원 목록 출력 페이지로 이동.
    }

    //===============================================================================================================================

    //==================================================[관리자 페이지 회원 상세 정보 : 메인]==================================================

    /*
     * 요청 값 : VO에 존재 : mID  / 세션 : X / VO에 없음 : X
     * 요청 페이지 :
     * memberList.jsp (회원 목록 출력 페이지), adminMemberInfoMain.jsp, adminMemberInfoOrder.jsp, adminMemberInfoReview.jsp, adminMemberInfoSubs.jsp
     * 리턴 값 : model : mdata (특정 회원 정보 데이터) / 세션 : mID
     * 기능 : 회원 상세 정보 페이지로 이동 하기. (memberInfoMain.jsp 이동)
     */

    @RequestMapping(value = "/memberInfoMain.do")
    public String memberInfoMain(MemberVO mVO, HttpSession session, Model model) {

        if (mVO.getmID() == null || mVO.getmID().equals("") || mVO.getmID().isBlank() || mVO.getmID().isEmpty()) {    //   유효성 검사.
            String mID = (String) session.getAttribute("mID");
            mVO.setmID(mID);
        }

        mVO.setSk("INFO");
        mVO = memberService.selectOne(mVO);

        model.addAttribute("mdata", mVO);
        session.setAttribute("mID", mVO.getmID());

        return "adminMemberInfoMain.jsp";
    }

    /*
     * 요청 값 : VO에 존재 : X / 세션 : mID / VO에 없음 : X
     * 요청 페이지 : adminMemberInfoMain.jsp (회원 상세 정보 페이지_ 메인)
     * 리턴 값 : model : mdata (특정 회원 데이터) / 세션 : mID
     * 기능 : 회원 상세 정보 페이지로 이동 하기. (memberInfoMain.jsp 이동)
     */

    @RequestMapping(value = "/memberInfoMainDeleteMember.do")
    public String memberInfoMainDeleteMember(MemberVO mVO, HttpSession session) {

        String mID = (String) session.getAttribute("mID");    //   세션에 있는 mID 가져오기.

        mVO.setmID(mID);
        memberService.delete(mVO);

        return "redirect:memberList.do";    //  회원 목록 출력 페이지로 이동. (보낼 데이터 없어서 redirect)
    }

    //===============================================================================================================================

    //==================================================[관리자 페이지 회원 상세 정보 : 리뷰]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 :
     * adminMemberInfoMain.jsp (회원 상세 정보 페이지_ 메인), adminMemberInfoReview.jsp, adminMemberInfoOrder.jsp, adminMemberInfoSubs.jsp
     * 리턴 값 : model : bdatas (회원이 작성한 모든 리뷰 데이터) / 세션 : X
     * 기능 : 회원이 작성한 모든 리뷰 보여주기. (memberInfoReview.jsp 이동)
     */

    @RequestMapping(value = "/memberInfoReview.do")
    public String memberInfoReview(BoardVO bVO, HttpSession session, Model model) {
    	
        String mID = (String) session.getAttribute("mID");    //  세션에 있는 mID 가져오기.

        bVO.setSk("MYPAGE");
        bVO.setmID(mID);
        List<BoardVO> bdatas = boardService.selectAll(bVO); //  회원이 작성한 모든 리뷰.
        
        System.out.println("bdatas : " + bdatas);

        model.addAttribute("bdatas", bdatas);

        return "adminMemberInfoReview.jsp";    //  회원 리뷰 상세 정보 페이지로 이동.
    }

    //===================================================================================================================================

    //==================================================[관리자 페이지 회원 상세 정보 : 주문]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 :
     * adminMemberInfoMain.jsp (회원 상세 정보 페이지_ 메인), adminMemberInfoReview.jsp, adminMemberInfoOrder.jsp, adminMemberInfoSubs.jsp
     * 리턴 값 : model : orderDatas (특정 회원이 주문한 상품 정보 + 특정 회원이 주문한 상품 상세 정보 데이터) / 세션 : X
     * 기능 : 회원이 주문한 모든 주문 내역 보여주기. (memberInfoOrder.jsp 이동)
     */

    @RequestMapping(value = "/memberInfoOrder.do")
    public String memberInfoOrder(OrderVO oVO, OrderdetailVO odVO, HttpSession session, Model model) {

        Map<OrderVO, List<OrderdetailVO>> orderMap = new LinkedHashMap<OrderVO, List<OrderdetailVO>>();

        String mID = (String) session.getAttribute("mID");    //  세션에 있는 mID 가져오기.

        oVO.setmID(mID);
        oVO.setSk("MYORDER");
        odVO.setSk("ORDERDETAIL");
        List<OrderVO> odatas = orderService.selectAll(oVO);

        for (int i = 0; i < odatas.size(); i++) {
            oVO = odatas.get(i);
            int oNum = odatas.get(i).getoNum();
            odVO.setoNum(oNum);
            List<OrderdetailVO> oddatas = orderdetailService.selectAll(odVO);
            orderMap.put(oVO, oddatas); //  회원이 주문한 상품 정보 + 회원이 주문한 상품 상세 정보.
        }

        model.addAttribute("orderDatas", orderMap);

        return "adminMemberInfoOrder.jsp";    //  회원 주문 상세 정보 페이지로 이동.
    }

    //==================================================[관리자 페이지 회원 상세 정보 : 구독]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 :
     * adminMemberInfoMain.jsp (회원 상세 정보 페이지_ 메인), adminMemberInfoReview.jsp, adminMemberInfoOrder.jsp, adminMemberInfoSubs.jsp
     * 리턴 값 : model : subsinfoData (회원 구독 정보 + 회원 구독 상세 정보 데이터) / 세션 : X
     * 기능 : 회원 상세 정보. (memberInfoSubs.jsp 이동)
     */

    @RequestMapping(value = "/memberInfoSubs.do")
    public String memberInfoSubs(SubsinfoVO subsinfoVO, HttpSession session, Model model) {    //   subsinfoService.selectAll() 로 수정 예정.

        String mID = (String) session.getAttribute("mID");    //  세션에 있는 mID 가져오기.

        subsinfoVO.setmID(mID);
        List<SubsinfoVO> subsinfoDatas = subsinfoService.selectAll(subsinfoVO); //  회원 구독 정보 + 회원 구독 상세 정보.

        model.addAttribute("subsinfoDatas", subsinfoDatas);

        return "adminMemberInfoSubs.jsp";    //  회원 구독 상세 정보 페이지로 이동.
    }

    //===================================================================================================================================

}   //  AdminMemberController