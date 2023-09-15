package com.spring.biz.recommendation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RecommendationService")
public class RecommendationServiceImpl implements RecommendationService{

	@Autowired
	private RecommendationDAO2 recommendationDAO;
	
	@Override
	public boolean insert(RecommendationVO recommendationVO) {
		return false;
	}

	@Override
	public List<RecommendationVO> selectAll(RecommendationVO recommendationVO) {
		return null;
	}

	@Override
	public RecommendationVO selectOne(RecommendationVO recommendationVO) {
		return null;
	}

	@Override
	public boolean update(RecommendationVO recommendationVO) {
		return false;
	}

	@Override
	public boolean delete(RecommendationVO recommendationVO) {
		return false;
	}

}
