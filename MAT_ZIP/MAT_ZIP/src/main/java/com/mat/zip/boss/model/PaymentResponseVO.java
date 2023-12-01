package com.mat.zip.boss.model;

import java.util.Date;

public class PaymentResponseVO {

    private String paymentKey;
    private String orderId;
    private int amount;
    private int totalAmount;
    private String orderName;
    private Date requestedAt;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}