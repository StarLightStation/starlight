package com.spring.biz.orderdetail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderdetailService")
public class OrderdetailServiceImpl implements OrderdetailService {
	
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	
	@Autowired
	private OrderdetailDAO2 orderdetailDAO;

	@Override
	public boolean insert(OrderdetailVO orderdetailVO) {
		return orderdetailDAO.insert(orderdetailVO);
	}

	@Override
	public List<OrderdetailVO> selectAll(OrderdetailVO orderdetailVO) {
		return orderdetailDAO.selectAll(orderdetailVO);
	}

	@Override
	public OrderdetailVO selectOne(OrderdetailVO orderdetailVO) {
		return null;
	}

	@Override
	public boolean update(OrderdetailVO orderdetailVO) {
		return orderdetailDAO.update(orderdetailVO);
	}

	@Override
	public boolean delete(OrderdetailVO orderdetailVO) {
		return false;
	}

}	//	OrderdetailServiceImpl
