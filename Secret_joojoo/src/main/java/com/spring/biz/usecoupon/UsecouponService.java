package com.spring.biz.usecoupon;

import java.util.List;

import com.spring.biz.usecoupon.UsecouponVO;

public interface UsecouponService {

	public boolean insert(UsecouponVO usecouponVO);
	public List<UsecouponVO> selectAll(UsecouponVO usecouponVO);
	public UsecouponVO selectOne(UsecouponVO usecouponVO);
	public boolean update(UsecouponVO usecouponVO);
	public boolean delete(UsecouponVO usecouponVO);

}	//	UsecouponService interface
