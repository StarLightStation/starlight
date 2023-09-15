package com.spring.biz.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	
	@Autowired
	private MemberDAO2 memberDAO;
	
	@Override
	public boolean insert(MemberVO memberVO) {
		return memberDAO.insert(memberVO);
	}
	
	@Override
	public List<MemberVO> selectAll(MemberVO memberVO) {
		return memberDAO.selectAll(memberVO);
	}
	
	@Override
	public MemberVO selectOne(MemberVO memberVO) {
		return memberDAO.selectOne(memberVO);
	}

	@Override
	public boolean update(MemberVO memberVO) {
		return memberDAO.update(memberVO);
	}

	@Override
	public boolean delete(MemberVO memberVO) {
		return memberDAO.delete(memberVO);
	}
	
}	//	MemberServiceImpl
