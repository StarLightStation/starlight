package com.spring.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.board.BoardService;
import com.spring.biz.member.MemberService;
import com.spring.biz.member.MemberVO;

@Controller
public class MyPageMemberController {

    @Autowired
    BoardService boardService;    //	bDAO
    @Autowired
    MemberService memberService;    //	mDAO

    //==================================================[마이페이지 회원 탈퇴]==================================================

    /*
     * 요청 값 : VO에 존재 : X  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 : member.jsp
     * 리턴 값 : model : X / 세션 : X
     * 기능 : 회원 탈퇴 기능 ( logout.do 이동)
     */

    @RequestMapping(value = "/deleteMember.do", method = RequestMethod.GET)
    public String deleteMember(MemberVO mVO, HttpSession session) {

        mVO.setmID((String) session.getAttribute("mID"));    //	세션 mID
        memberService.delete(mVO);    // mID	//	회원 탈퇴

        return "redirect:logout.do";    //	회원탈퇴 후 로그아웃 진행
    }

    //=====================================================================================================================


    //==================================================[마이페이지 페이지 이동]==================================================

    /*
     * 요청 값 : VO에 존재 하는 거 - X / 세션 : mID / VO에 없는 거 - X
     * 요청 페이지 : mypage.tag (member.jsp)
     * 리턴 값 : model : mdata (회원 정보 데이터) / 세션 : X
     * 기능 : 회원 정보 보기 ( member.jsp 이동 )
     */

    @RequestMapping(value = "/member.do", method = RequestMethod.GET)
    public String member(MemberVO mVO, HttpSession session, Model model) {

        mVO.setSk("INFO");    //	회원 정보 불러오기
        mVO.setmID((String) session.getAttribute("mID"));    //	세션 mID
        mVO = memberService.selectOne(mVO);    //	mID	//	회원에 대한 전체 정보

        model.addAttribute("mdata", mVO);    //	회원에 대한 전체 정보 보내기

        return "member.jsp";    //	마이페이지 회원정보 페이지로 이동
    }

    //=======================================================================================================================

    //==================================================[마이페이지 회원 정보 변경]==================================================

    /*
     * 요청 값 : VO에 존재 : mName, mPW  / 세션 : mID  / VO에 없음 : X
     * 요청 페이지 : member.jsp
     * 리턴 값 : model : X / 세션 : X
     * 기능 : 회원 정보 변경 ( logout.do 이동 )
     */

    @RequestMapping(value = "/updateMember.do", method = RequestMethod.POST)
    public String updateMember(MemberVO mVO, HttpSession session, Model model) {

        mVO.setSk("CHANGE");    //	회원 정보 변경
        mVO.setmID((String) session.getAttribute("mID"));    //	세션 mID
        boolean flag = memberService.update(mVO);    //	mID	//	회원의 정보 변경	//	구글 로그인 , 주주 로그인

        if(flag) {
        mVO.setSweetAlert("success");
        mVO.setMessage("회원 정보 변경에 ");
        mVO.setLocation("logout.do");

        model.addAttribute("mdata", mVO);
        
        return "sweetAlert.do";    //	정보 변경 후 로그아웃 진행
        }
        
        mVO.setSweetAlert("fail");
        mVO.setMessage(" 번호 변경에 ");
        mVO.setLocation("member.do");

        model.addAttribute("mdata", mVO);
        
        return "sweetAlert.do";
    }

    //========================================================================================================================

}    //	MypageMemberController
