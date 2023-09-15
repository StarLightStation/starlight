package com.spring.biz.order;

import java.util.List;

public interface OrderService {
	
	public boolean insert(OrderVO orderVO);
	public List<OrderVO> selectAll(OrderVO orderVO);
	public OrderVO selectOne(OrderVO orderVO);
	public boolean update(OrderVO orderVO);
	public boolean delete(OrderVO orderVO);

}	//	OrderService interface
