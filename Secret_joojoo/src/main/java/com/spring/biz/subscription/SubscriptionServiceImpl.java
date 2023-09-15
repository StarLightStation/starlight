package com.spring.biz.subscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService {
	
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	
	@Autowired
	private SubscriptionDAO2 subscriptionDAO;

	@Override
	public boolean insert(SubscriptionVO subscriptionVO) {
		return false;
	}

	@Override
	public List<SubscriptionVO> selectAll(SubscriptionVO subscriptionVO) {
		return subscriptionDAO.selectAll(subscriptionVO);
	}

	@Override
	public SubscriptionVO selectOne(SubscriptionVO subscriptionVO) {
		return subscriptionDAO.selectOne(subscriptionVO);
	}

	@Override
	public boolean update(SubscriptionVO subscriptionVO) {
		return false;
	}

	@Override
	public boolean delete(SubscriptionVO subscriptionVO) {
		return false;
	}

}	//	SubscriptionServiceImpl
