package com.spring.biz.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImple implements BoardService {

	// Service 레이어가 관념적으로 존재하는데, 그것을 구현한 클래스

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean insert(BoardVO boardVO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("mID", boardVO.getmID());
		map.put("mName", boardVO.getmName());
		map.put("pNum", String.valueOf(boardVO.getpNum()));
		map.put("bContent", boardVO.getbContent());
		map.put("bStar", String.valueOf(boardVO.getbStar()));
		return boardMapper.insert(map);
	}

	@Override
	public List<BoardVO> selectAll(BoardVO boardVO) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("sk", boardVO.getSk());
		map.put("pNum", String.valueOf(boardVO.getpNum()));
		map.put("mID", boardVO.getmID());
		return boardMapper.selectAll(map);
	}

	@Override
	public BoardVO selectOne(BoardVO boardVO) {
		return boardMapper.selectOne(boardVO.getbNum());
	}

	@Override
	public boolean update(BoardVO boardVO) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("bContent", boardVO.getbContent());
		map.put("bStar", String.valueOf(boardVO.getbStar()));
		map.put("bNum", String.valueOf(boardVO.getbNum()));
		return boardMapper.update(map);
	}

	@Override
	public boolean delete(BoardVO boardVO) {
		return boardMapper.delete(boardVO.getbNum());
	}

}    //	BoardServiceImple