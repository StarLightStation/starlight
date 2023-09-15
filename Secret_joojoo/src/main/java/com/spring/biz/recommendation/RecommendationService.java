package com.spring.biz.recommendation;

import java.util.List;

public interface RecommendationService {

	public boolean insert(RecommendationVO recommendationVO);
	public List<RecommendationVO> selectAll(RecommendationVO recommendationVO);
	public RecommendationVO selectOne(RecommendationVO recommendationVO);
	public boolean update(RecommendationVO recommendationVO);
	public boolean delete(RecommendationVO recommendationVO);

}	//	DeclarationService interface


