package com.spring.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImple implements BoardService {
	
	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스
	
	@Autowired
	private BoardDAO2 boardDAO;
	
	@Override
	public boolean insert(BoardVO boardVO) {
		return boardDAO.insert(boardVO);
	}
	
	@Override
	public List<BoardVO> selectAll(BoardVO boardVO) {
		return boardDAO.selectAll(boardVO);
	}
	
	@Override
	public BoardVO selectOne(BoardVO boardVO) {
		return boardDAO.selectOne(boardVO);
	}

	@Override
	public boolean update(BoardVO boardVO) {
		return boardDAO.update(boardVO);
	}

	@Override
	public boolean delete(BoardVO boardVO) {
		return boardDAO.delete(boardVO);
	}
	
}	//	BoardServiceImple