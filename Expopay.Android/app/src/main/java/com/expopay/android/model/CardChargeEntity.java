package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/11/10.
 */
public class CardChargeEntity implements Serializable {
    private String orderAmount;
    private String chargeType;
    private String chargeTime;
    private String transactionNum;

    public CardChargeEntity() {
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(String chargeTime) {
        this.chargeTime = chargeTime;
    }

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }
}
