package com.spring.biz.subscription;

import java.util.List;

public interface SubscriptionService {
	
	public boolean insert(SubscriptionVO subscriptionVO);
	public List<SubscriptionVO> selectAll(SubscriptionVO subscriptionVO);
	public SubscriptionVO selectOne(SubscriptionVO subscriptionVO);
	public boolean update(SubscriptionVO subscriptionVO);
	public boolean delete(SubscriptionVO subscriptionVO);

}	//	SubscriptionService interface
