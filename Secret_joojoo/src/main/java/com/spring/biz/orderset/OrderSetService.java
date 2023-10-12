package com.spring.biz.orderset;

import java.util.List;

import com.spring.biz.order.OrderVO;

public interface OrderSetService {
	
	public boolean insert(OrderVO order);
	public List<OrderSet> selectAll(OrderVO order);
	public OrderSet selectOne(OrderVO order);
	public boolean update(OrderVO order);
	public boolean delete(OrderVO order);

}	//	OrderSetService interface
