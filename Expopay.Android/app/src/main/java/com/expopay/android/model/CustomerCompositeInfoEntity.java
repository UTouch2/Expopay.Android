package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by NB-MIS-100002 on 2015/12/2.
 */
public class CustomerCompositeInfoEntity implements Serializable {
    private double cardBalance;
    private int remainingDays;
    private String defCardNumber;
    private String personName;
    private int cardQuantity;
    private double billAmount;
    private double creditLimitAmt;

    public double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
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

    public int getCardQuantity() {
        return cardQuantity;
    }

    public void setCardQuantity(int cardQuantity) {
        this.cardQuantity = cardQuantity;
    }

    public double getCreditLimitAmt() {
        return creditLimitAmt;
    }

    public void setCreditLimitAmt(double creditLimitAmt) {
        this.creditLimitAmt = creditLimitAmt;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }
}
