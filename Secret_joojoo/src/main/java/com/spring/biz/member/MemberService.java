package com.spring.biz.member;

import java.util.List;

public interface MemberService {	//	DAO의 메서드 시그니쳐를 맞추기 위한 인터페이스 이다.
	
	public boolean insert(MemberVO memberVO);
	public List<MemberVO> selectAll(MemberVO memberVO);
	public MemberVO selectOne(MemberVO memberVO);
	public boolean update(MemberVO memberVO);
	public boolean delete(MemberVO memberVO);

}	//	MemberService interface
