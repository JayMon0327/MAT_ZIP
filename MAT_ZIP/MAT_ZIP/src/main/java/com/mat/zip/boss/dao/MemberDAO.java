package com.mat.zip.boss.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mat.zip.boss.model.BossMemberVO;

@Component
public class MemberDAO {
    @Autowired
    SqlSessionTemplate my;

    public void innerJoinAndInsert(BossMemberVO bag) {
        my.insert("boss.insertJoinedData", bag);
    }

    public int checkStoreId(String storeId) {
        return my.selectOne("boss.checkStoreId", storeId);
    }
}
