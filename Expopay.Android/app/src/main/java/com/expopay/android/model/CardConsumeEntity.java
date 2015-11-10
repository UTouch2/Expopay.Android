package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/11/10.
 */
public class CardConsumeEntity implements Serializable {
    private String orderAmount;
    private String consumeType;
    private String consumeTime;
    private String publicUtilityComp;
    private String publicUtilityNum;

    public CardConsumeEntity() {
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType;
    }

    public String getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(String consumeTime) {
        this.consumeTime = consumeTime;
    }

    public String getPublicUtilityComp() {
        return publicUtilityComp;
    }

    public void setPublicUtilityComp(String publicUtilityComp) {
        this.publicUtilityComp = publicUtilityComp;
    }

    public String getPublicUtilityNum() {
        return publicUtilityNum;
    }

    public void setPublicUtilityNum(String publicUtilityNum) {
        this.publicUtilityNum = publicUtilityNum;
    }
}
