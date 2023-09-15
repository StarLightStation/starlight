package com.spring.biz.orderdetail;

import java.util.List;

public interface OrderdetailService {
	
	public boolean insert(OrderdetailVO orderdetailVO);
	public List<OrderdetailVO> selectAll(OrderdetailVO orderdetailVO);
	public OrderdetailVO selectOne(OrderdetailVO orderdetailVO);
	public boolean update(OrderdetailVO orderdetailVO);
	public boolean delete(OrderdetailVO orderdetailVO);

}	//	OrderdetailService interface
