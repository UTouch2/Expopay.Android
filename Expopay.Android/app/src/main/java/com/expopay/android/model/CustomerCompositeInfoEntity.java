package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by NB-MIS-100002 on 2015/12/2.
 */
public class CustomerCompositeInfoEntity implements Serializable {
    private String cardBalance;
    private String remainingDays;
    private String defCardNumber;
    private String personName;
    private String cardQuantity;
    private String billAmount;
    private String creditLimitAmt;

    public CustomerCompositeInfoEntity() {
    }

    public String getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(String cardBalance) {
        this.cardBalance = cardBalance;
    }

    public String getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(String remainingDays) {
        this.remainingDays = remainingDays;
    }

    public String getDefCardNumber() {
        return defCardNumber;
    }

    public void setDefCardNumber(String defCardNumber) {
        this.defCardNumber = defCardNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCardQuantity() {
        return cardQuantity;
    }

    public void setCardQuantity(String cardQuantity) {
        this.cardQuantity = cardQuantity;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getCreditLimitAmt() {
        return creditLimitAmt;
    }

    public void setCreditLimitAmt(String creditLimitAmt) {
        this.creditLimitAmt = creditLimitAmt;
    }
}


