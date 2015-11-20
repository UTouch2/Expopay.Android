package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/11/20.
 */
public class ProductPeroidEntity implements Serializable {
    private String periodId;
    private String period;
    private String periodAmount;
    private String overdueAmount;
    private String serviceAmount;

    public ProductPeroidEntity() {
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriodAmount() {
        return periodAmount;
    }

    public void setPeriodAmount(String periodAmount) {
        this.periodAmount = periodAmount;
    }

    public String getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(String overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }
}
