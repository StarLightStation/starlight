package com.spring.biz.subsinfo;

import java.util.List;

public interface SubsinfoService {	//	DAO의 메서드 시그니쳐를 맞추기 위한 인터페이스 이다.
	
	public boolean insert(SubsinfoVO subsinfoVO);
	public List<SubsinfoVO> selectAll(SubsinfoVO subsinfoVO);
	public SubsinfoVO selectOne(SubsinfoVO subsinfoVO);
	public boolean update(SubsinfoVO subsinfoVO);
	public boolean delete(SubsinfoVO subsinfoVO);

}	//	SubsinfoService interface
