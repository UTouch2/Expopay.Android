package com.expopay.android.model;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class CardApplyEntity {
    private String applyTime;
    private String applyResult;
    private String cardNumber;

    public CardApplyEntity() {
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyResult() {
        return applyResult;
    }

    public void setApplyResult(String applyResult) {
        this.applyResult = applyResult;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
