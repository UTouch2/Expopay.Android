package com.expopay.android.model;

/**
 * Created by NB-MIS-100002 on 2015/10/22.
 */
public class PeriodOrderEntity extends OrderEntity{
    String properties;
    String repaymentPeriod;

    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }


}
