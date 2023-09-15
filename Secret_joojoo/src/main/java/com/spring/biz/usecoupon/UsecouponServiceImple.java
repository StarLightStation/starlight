package com.spring.biz.usecoupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("usecouponService")
public class UsecouponServiceImple implements UsecouponService {

	@Autowired
	private UsecouponDAO2 usecouponDAO;
	
	@Override
	public boolean insert(UsecouponVO usecouponVO) {
		return usecouponDAO.insert(usecouponVO);
	}

	@Override
	public List<UsecouponVO> selectAll(UsecouponVO usecouponVO) {
		return usecouponDAO.selectAll(usecouponVO);
	}

	@Override
	public UsecouponVO selectOne(UsecouponVO usecouponVO) {
		return usecouponDAO.selectOne(usecouponVO); 
	}

	@Override
	public boolean update(UsecouponVO usecouponVO) {
		return usecouponDAO.update(usecouponVO);
	}

	@Override
	public boolean delete(UsecouponVO usecouponVO) {
		return false;
	}
}	//	UsecouponServiceImple