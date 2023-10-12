package com.spring.biz.orderset;

import java.util.ArrayList;

import com.spring.biz.order.OrderVO;
import com.spring.biz.orderdetail.OrderdetailVO;

public class OrderSet {
	
	//	1 : N 구조의 기능을 구현 하기 위한 클래스.
	//	Order : Orderdetail
	
	private OrderVO order;						//	1
	private ArrayList<OrderdetailVO> oddatas;	//	N
	
	public OrderVO getOrder() {
		return order;
	}
	public void setOrder(OrderVO order) {
		this.order = order;
	}
	public ArrayList<OrderdetailVO> getOddatas() {
		return oddatas;
	}
	public void setOddatas(ArrayList<OrderdetailVO> oddatas) {
		this.oddatas = oddatas;
	}
	
	@Override
	public String toString() {
		
		return "Order : " + order + " / Orderdetail : " + oddatas;
	}
	
}	//	OrderSet
