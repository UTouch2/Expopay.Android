package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/12/1.
 */
public class WegTransactionEntity implements Serializable {
    String publicParamText;
    String publicParamValue;
    String amount;

    public WegTransactionEntity() {
    }

    public String getPublicParamText() {
        return publicParamText;
    }

    public void setPublicParamText(String publicParamText) {
        this.publicParamText = publicParamText;
    }

    public String getPublicParamValue() {
        return publicParamValue;
    }

    public void setPublicParamValue(String publicParamValue) {
        this.publicParamValue = publicParamValue;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
