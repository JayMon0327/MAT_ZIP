package com.mat.zip.boss.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mat.zip.boss.dao.MemberDAO;
import com.mat.zip.boss.model.BossMemberVO;

@Service
public class RegisterMemberAndPaymentService {

    private final MemberDAO memberDAO;

    @Autowired
    public RegisterMemberAndPaymentService(MemberDAO memberDAO, PaymentService paymentService) {
        this.memberDAO = memberDAO;
    }

    @Transactional
    public void registerAndPay(BossMemberVO member) {
        memberDAO.innerJoinAndInsert(member);
    }
}

