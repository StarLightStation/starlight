package com.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spring.biz.board.BoardVO;

@Mapper
public interface BoardMapper {	//	== InterFaceBoardDAO

    boolean insert(Map<String, String> map);

    List<BoardVO> selectAll(Map<String, String> map);

    BoardVO selectOne(@Param("bNum") int bNum);

    boolean update(Map<String, String> map);

    boolean delete(@Param("bNum") int bNum);

}   //  BoardMapper

