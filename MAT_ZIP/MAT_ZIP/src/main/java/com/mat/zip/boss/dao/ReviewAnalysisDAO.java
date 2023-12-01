package com.mat.zip.boss.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReviewAnalysisDAO {
    @Autowired
    private SqlSession sqlSession;

    public List<String> TotalReview(String storeId) {
        return sqlSession.selectList("TotalReview", storeId);
    }
}
