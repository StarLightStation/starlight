package com.spring.biz.subsinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subsinfoService")
public class SubsinfoServiceImpl implements SubsinfoService {
	
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스

	@Autowired
	private SubsinfoDAO2 subsinfoDAO;
	
	@Override
	public boolean insert(SubsinfoVO subsinfoVO) {
		return subsinfoDAO.insert(subsinfoVO);
	}

	@Override
	public List<SubsinfoVO> selectAll(SubsinfoVO subsinfoVO) {
		return subsinfoDAO.selectAll(subsinfoVO);
	}

	@Override
	public SubsinfoVO selectOne(SubsinfoVO subsinfoVO) {
		return subsinfoDAO.selectOne(subsinfoVO);
	}

	@Override
	public boolean update(SubsinfoVO subsinfoVO) {
		return false;
	}

	@Override
	public boolean delete(SubsinfoVO subsinfoVO) {
		return false;
	}

}	//	SubsinfoServiceImpl
