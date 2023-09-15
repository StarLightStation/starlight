package com.spring.biz.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	
	@Autowired
	private OrderDAO2 orderDAO;

	@Override
	public boolean insert(OrderVO orderVO) {
		return orderDAO.insert(orderVO);
	}

	@Override
	public List<OrderVO> selectAll(OrderVO orderVO) {
		return orderDAO.selectAll(orderVO);
	}

	@Override
	public OrderVO selectOne(OrderVO orderVO) {
		return orderDAO.selectOne(orderVO);
	}

	@Override
	public boolean update(OrderVO orderVO) {
		return orderDAO.update(orderVO);
	}

	@Override
	public boolean delete(OrderVO orderVO) {
		return false;
	}
	
}	//	OrderServiceImpl
