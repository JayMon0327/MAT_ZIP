package com.mat.zip.boss.model;

public class PaymentVO {

    private String storeId;
    private String orderId;
    private String orderName;
    private int amount;

    // getter, setter, toString methods
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentVO [storeId=" + storeId + ", orderId=" + orderId + ", orderName=" + orderName + ", amount="
                + amount + "]";
    }


}
