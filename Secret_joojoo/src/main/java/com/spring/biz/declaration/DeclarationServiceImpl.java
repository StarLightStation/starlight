package com.spring.biz.declaration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("declarationService")
public class DeclarationServiceImpl implements DeclarationService{
	@Autowired
	private DeclarationDAO2 declarationDAO;
	
	@Override
	public boolean insert(DeclarationVO declarationVO) {
		return declarationDAO.insert(declarationVO);
	}

	@Override
	public List<DeclarationVO> selectAll(DeclarationVO declarationVO) {
		return declarationDAO.selectAll(declarationVO);
	}

	@Override
	public DeclarationVO selectOne(DeclarationVO declarationVO) {
		return declarationDAO.selectOne(declarationVO);
	}

	@Override
	public boolean update(DeclarationVO declarationVO) {
		return declarationDAO.update(declarationVO);
	}

	@Override
	public boolean delete(DeclarationVO declarationVO) {
		return declarationDAO.delete(declarationVO);
	}
}