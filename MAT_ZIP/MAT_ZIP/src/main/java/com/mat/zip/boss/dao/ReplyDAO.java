package com.mat.zip.boss.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mat.zip.boss.model.ReplyVO;

@Component
public class ReplyDAO {

    @Autowired
    SqlSessionTemplate my;

    public int insert(ReplyVO bag) {
        int result = my.insert("com.create", bag);
        return result;
    }

    public int update(ReplyVO bag) {
        int result = my.update("com.up", bag);
        return result;
    }

    public int delete(int com_id) {
        int result = my.update("com.del", com_id);
        return result;

    }

    public ReplyVO one(int board_id) {
        ReplyVO bag = my.selectOne("com.one", board_id);
        return bag;
    }

    public List<ReplyVO> list(int board_id) {
        List<ReplyVO> list = my.selectList("com.list", board_id);
        return list;

    }

    public int getCommentCount(int boardId) {
        return my.selectOne("com.count", boardId);
    }

}
