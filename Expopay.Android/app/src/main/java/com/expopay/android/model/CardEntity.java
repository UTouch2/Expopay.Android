package com.expopay.android.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/10/15.
 */
public class CardEntity implements Serializable {

    private String cardNumber;
    private String cardType;
    private String isDefault;
    @SerializedName("cardBalance")
    private String balance;
    private String hasPwd;

    public CardEntity() {
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getHasPwd() {
        return hasPwd;
    }

    public void setHasPwd(String hasPwd) {
        this.hasPwd = hasPwd;
    }
}
