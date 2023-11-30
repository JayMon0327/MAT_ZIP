package com.mat.zip.boss.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mat.zip.boss.dao.BossMemberDAO;
import com.mat.zip.boss.model.BossMemberVO;

@Service
public class MemberAndPaymentService {

    private final BossMemberDAO memberDAO;

    @Autowired
    public MemberAndPaymentService(BossMemberDAO memberDAO, PaymentService paymentService) {
        this.memberDAO = memberDAO;
    }

    @Transactional
    public void registerAndPay(BossMemberVO member) {
        // 회원 정보 저장
        memberDAO.innerJoinAndInsert(member);
        System.out.println(member);
    }
}

