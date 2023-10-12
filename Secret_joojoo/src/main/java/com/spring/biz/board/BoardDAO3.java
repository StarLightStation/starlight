package com.spring.biz.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

//@Repository("boardDAO")
public class BoardDAO3 {

    //    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public boolean insert(Map<String, String> map) {

        int rs = sqlSessionTemplate.insert("BoardMapper.insert", map);

        return rs > 0 ? true : false;
    }

    public List<BoardVO> selectAll(Map<String, String> map) {

        return sqlSessionTemplate.selectList("BoardMapper.selectAll", map);

    }

    public BoardVO selectOne(int bNum) {

        return sqlSessionTemplate.selectOne("BoardMapper.selectOne", bNum);
    }

    public boolean update(Map<String, String> map) {

        int rs = sqlSessionTemplate.update("BoardMapper.update", map);

        return rs > 0 ? true : false;
    }


    public boolean delete(int bNum) {

        int rs = sqlSessionTemplate.delete("BoardMapper.delete", bNum);

        return rs > 0 ? true : false;
    }
}    //   BoardDAO3
