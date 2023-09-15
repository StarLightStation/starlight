package com.spring.biz.coupon;

import java.util.List;

public interface CouponService {

	public boolean insert(CouponVO couponVO);
	public List<CouponVO> selectAll(CouponVO couponVO);
	public CouponVO selectOne(CouponVO couponVO);
	public boolean update(CouponVO couponVO);
	public boolean delete(CouponVO couponVO);

}
