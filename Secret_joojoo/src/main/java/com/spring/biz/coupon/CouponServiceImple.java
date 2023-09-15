package com.spring.biz.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("couponService")
public class CouponServiceImple implements CouponService {

	@Autowired
	private CouponDAO2 couponDAO;
	
	@Override
	public boolean insert(CouponVO couponVO) {
		return false;
	}

	@Override
	public List<CouponVO> selectAll(CouponVO couponVO) {
		return null;
	}

	@Override
	public CouponVO selectOne(CouponVO couponVO) {
		return couponDAO.selectOne(couponVO);
	}

	@Override
	public boolean update(CouponVO couponVO) {
		return false;
	}

	@Override
	public boolean delete(CouponVO couponVO) {
		return false;
	}
}	//	CouponServiceImple
