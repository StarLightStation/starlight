package com.spring.biz.product;

import java.util.List;

public interface ProductService {
	
	public boolean insert(ProductVO productVO);
	public List<ProductVO> selectAll(ProductVO productVO);
	public ProductVO selectOne(ProductVO productVO);
	public boolean update(ProductVO productVO);
	public boolean delete(ProductVO productVO);

}	//	ProductService interface
