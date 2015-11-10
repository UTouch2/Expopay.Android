package com.expopay.android.model;

import java.util.List;

/**
 * Created by misxu012 on 2015/10/15.
 */
public class CardDetailsEntity extends CardEntity {
    private String compName;
    private String certificationStatus;
    private String chargeAmount;
    private String consumeAmount;
    private List<CardChargeEntity> chargeRecords;
    private List<CardConsumeEntity> consumeRecords;

    public CardDetailsEntity() {
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getCertificationStatus() {
        return certificationStatus;
    }

    public void setCertificationStatus(String certificationStatus) {
        this.certificationStatus = certificationStatus;
    }

    public String getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(String consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public List<CardChargeEntity> getChargeRecords() {
        return chargeRecords;
    }

    public void setChargeRecords(List<CardChargeEntity> chargeRecords) {
        this.chargeRecords = chargeRecords;
    }

    public List<CardConsumeEntity> getConsumeRecords() {
        return consumeRecords;
    }

    public void setConsumeRecords(List<CardConsumeEntity> consumeRecords) {
        this.consumeRecords = consumeRecords;
    }
}
