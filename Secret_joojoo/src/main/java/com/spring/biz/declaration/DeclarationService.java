package com.spring.biz.declaration;

import java.util.List;

import com.spring.biz.declaration.DeclarationVO;

public interface DeclarationService {

	public boolean insert(DeclarationVO declarationVO);
	public List<DeclarationVO> selectAll(DeclarationVO declarationVO);
	public DeclarationVO selectOne(DeclarationVO declarationVO);
	public boolean update(DeclarationVO declarationVO);
	public boolean delete(DeclarationVO declarationVO);

}	//	DeclarationService interface
