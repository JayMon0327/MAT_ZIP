package com.mat.zip.boss.controller;

import com.google.gson.JsonObject;
import com.mat.zip.boss.dao.BossMemberDAO;
import com.mat.zip.boss.dao.PaymentDAO;
import com.mat.zip.boss.model.BossMemberVO;
import com.mat.zip.boss.model.PaymentVO;
import com.mat.zip.boss.service.MemberAndPaymentService;
import com.mat.zip.boss.service.PaymentService;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/boss")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    PaymentDAO dao;
    @Autowired
    BossMemberDAO bossdao;
    @Autowired
    MemberAndPaymentService memberAndPaymentService;
    @Autowired
    HttpSession session;

    /**
     * 결제 전 중복체크
     */
    @PostMapping("/checkStoreId")
    @ResponseBody
    public String checkStoreId(@PathVariable String storeId) {
        int count = bossdao.checkStoreId(storeId);
        if (count > 0) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 결제하기 기능
     */
    @GetMapping("/tossSuccess")
    public String processPayment(@RequestParam("paymentKey") String paymentKey, @RequestParam("amount") int amount, @RequestParam("orderId") String orderId, Model model) {
        JsonObject paymentResponse = paymentService.processPayment(paymentKey, amount, orderId);

        if (paymentResponse != null) {
            model.addAttribute("paymentResponse", paymentResponse);
            return "/boss/tossSuccess";
        }
        else {
            return "/boss/tossFail";
        }
    }

    @GetMapping("/tossFail")
    public String processPaymentFail() {
        return "/boss/tossFail";
    }

    /**
     * 결제 후 실행 회원등록, 결제내역 등록
     */
    //회원등록
    @PostMapping("/innerJoinAndInsert")
    public String innerJoinAndInsert(@RequestParam String store_id, HttpSession session, Model model) {
        String user_id = (String) session.getAttribute("user_id");
        BossMemberVO member = new BossMemberVO();
        member.setUser_id(user_id);
        member.setStore_id(store_id);

        int count = bossdao.checkStoreId(store_id);
        if (count > 0) {
            model.addAttribute("error", "중복된 상점 ID입니다.");
            return "1";
        }

        memberAndPaymentService.registerAndPay(member);

        String boss_id = member.getUser_id();
        session.setAttribute("boss_id", boss_id);

        return "/boss/tossSuccess";
    }

    //결제내역 등록
    @PostMapping("/payment")
    public ResponseEntity<String> insert(@RequestBody PaymentVO bag) {
        String orderName = bag.getOrderName();
        String[] parts = orderName.split(" ", 2);
        bag.setStoreId(parts[0]);  // storeId에 parts[0] 설정
        bag.setOrderName(parts[1]);  // orderName에 parts[1] 설정

        String userId = (String) session.getAttribute("user_id");
        bag.setOrderId(userId);
        dao.insert(bag);
        return ResponseEntity.ok().body("결제 정보가 성공적으로 전송되었습니다.");
    }
}


 

    




