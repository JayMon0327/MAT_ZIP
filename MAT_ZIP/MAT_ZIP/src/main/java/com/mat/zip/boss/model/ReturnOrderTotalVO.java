package com.mat.zip.boss.model;

public class ReturnOrderTotalVO {

    private String store_id;
    private int newOrdersThisMonth;
    private int newOrderTotalThisMonth;
    private int returningOrdersThisMonth;
    private int returningOrderTotalThisMonth;
    private int newOrdersLastMonth;
    private int newOrdersTotalLastMonth;
    private int returningOrdersLastMonth;
    private int returningOrderTotalLastMonth;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
