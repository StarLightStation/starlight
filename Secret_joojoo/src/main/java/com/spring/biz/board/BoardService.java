package com.spring.biz.board;

import java.util.List;

public interface BoardService {
	
	public boolean insert(BoardVO boardVO);
	public List<BoardVO> selectAll(BoardVO boardVO);
	public BoardVO selectOne(BoardVO boardVO);
	public boolean update(BoardVO boardVO);
	public boolean delete(BoardVO boardVO);

}	//	BoardService interface
