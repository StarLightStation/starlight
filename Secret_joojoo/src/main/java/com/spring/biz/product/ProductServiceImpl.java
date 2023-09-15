package com.spring.biz.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	
	@Autowired
	private ProductDAO2 productDAO;
	
	@Override
	public boolean insert(ProductVO productVO) {
		return productDAO.insert(productVO);
	}

	@Override
	public List<ProductVO> selectAll(ProductVO productVO) {
		return productDAO.selectAll(productVO);
	}

	@Override
	public ProductVO selectOne(ProductVO productVO) {
		return productDAO.selectOne(productVO);
	}

	@Override
	public boolean update(ProductVO productVO) {
		return productDAO.update(productVO);
	}

	@Override
	public boolean delete(ProductVO productVO) {
		return productDAO.delete(productVO);
	}

}	//	ProductServiceImpl
