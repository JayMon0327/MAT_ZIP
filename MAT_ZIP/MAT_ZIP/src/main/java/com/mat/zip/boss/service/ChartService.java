package com.mat.zip.boss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mat.zip.boss.dao.ChartDAO;
import com.mat.zip.boss.model.ChartVO;
import com.mat.zip.boss.model.ReturnCustomerCountVO;
import com.mat.zip.boss.model.ReturnOrderCountVO;
import com.mat.zip.boss.model.ReturnOrderTotalVO;

@Service
public class ChartService {

    private final ChartDAO chartDAO;

    @Autowired
    public ChartService(ChartDAO chartDAO) {
        this.chartDAO = chartDAO;
    }

    /**
     * 매출차트
     */
    // 최근 7일 매출
    public List<ChartVO> DailyTotalAmount(String storeId) {
        return chartDAO.DailyTotalAmount(storeId);
    }

    // 한달전 7일 매출
    public List<ChartVO> XTotalAmount(String storeId) {
        return chartDAO.XTotalAmount(storeId);
    }

    // 2달전 7일 매출
    public List<ChartVO> twoTotalAmount(String storeId) {
        return chartDAO.XTotalAmount(storeId);
    }

    //  이번달 총 매출
    public List<ChartVO> thisMonthTotalAmount(String storeId) {
        return chartDAO.thisMonthTotalAmount(storeId);
    }

    //  저번달 총 매출
    public List<ChartVO> lastMonthTotalAmount(String storeId) {
        return chartDAO.lastMonthTotalAmount(storeId);
    }

    // 2달 전 총 매출
    public List<ChartVO> twoMonthsAgoTotalAmount(String storeId) {
        return chartDAO.twoMonthsAgoTotalAmount(storeId);
    }

    /**
     * 재방문 차트
     */
    // 이번달,지난달 재방문율 차트 조회
    public ReturnCustomerCountVO findthisMonthNewCustomers(String storeId) {
        return chartDAO.findthisMonthNewCustomers(storeId);
    }

    public ReturnCustomerCountVO findthisMonthReturningCustomers(String storeId) {
        return chartDAO.findthisMonthReturningCustomers(storeId);
    }

    public ReturnCustomerCountVO findLastMonthNewCustomers(String storeId) {
        return chartDAO.findLastMonthNewCustomers(storeId);
    }

    public ReturnCustomerCountVO findLastMonthReturningCustomers(String storeId) {
        return chartDAO.findLastMonthReturningCustomers(storeId);
    }

    // 주문 횟수별 고객 수 조회
    public ReturnOrderCountVO find2Customers(String storeId) {
        return chartDAO.find2Customers(storeId);
    }

    public ReturnOrderCountVO find3Customers(String storeId) {
        return chartDAO.find3Customers(storeId);
    }

    public ReturnOrderCountVO find4Customers(String storeId) {
        return chartDAO.find4Customers(storeId);
    }

    public ReturnOrderCountVO find5Customers(String storeId) {
        return chartDAO.find5Customers(storeId);
    }

    // 이번달, 지난달 신규 고객과 재방문 고객의 주문 총액 조회
    public ReturnOrderTotalVO thisMonthNewCustomerOrderTotal(String storeId) {
        return chartDAO.thisMonthNewCustomerOrderTotal(storeId);
    }

    public ReturnOrderTotalVO thisMonthReturnCustomerOrderTotal(String storeId) {
        return chartDAO.thisMonthReturnCustomerOrderTotal(storeId);
    }

    public ReturnOrderTotalVO lastMonthNewCustomerOrderTotal(String storeId) {
        return chartDAO.lastMonthNewCustomerOrderTotal(storeId);
    }

    public ReturnOrderTotalVO lastMonthReturnCustomerOrderTotal(String storeId) {
        return chartDAO.lastMonthReturnCustomerOrderTotal(storeId);
    }
}
