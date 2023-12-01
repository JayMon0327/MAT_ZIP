package com.mat.zip.boss.service;

import com.mat.zip.boss.dao.ReviewAnalysisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewAnalysisService {
    @Autowired
    private ReviewAnalysisDAO reviewDAO;

    public List<String> TotalReview(String storeId) {
        return reviewDAO.TotalReview(storeId);
    }
}
