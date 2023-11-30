package com.mat.zip.boss.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mat.zip.boss.dao.ReviewAnalysisDAO;
import com.mat.zip.boss.service.ReviewAnalysisService;

import java.util.List;

@Service
public class _ReviewAnalysisServiceImpl implements ReviewAnalysisService {
    @Autowired
    private ReviewAnalysisDAO reviewDAO;

    @Override
    public List<String> TotalReview(String storeId) {
        return reviewDAO.TotalReview(storeId);
    }
    
}
