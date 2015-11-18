package com.expopay.android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by misxu012 on 2015/11/18.
 */
public class BillEntity implements Serializable {
    String billAmount;
    String repaymentAmt;
    String creditLimitAmt;
    List<RepaymentBillEntity> repaymentBills;
    List<RepaymentedBillEntity> repaymentedBills;

    public BillEntity() {
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getRepaymentAmt() {
        return repaymentAmt;
    }

    public void setRepaymentAmt(String repaymentAmt) {
        this.repaymentAmt = repaymentAmt;
    }

    public String getCreditLimitAmt() {
        return creditLimitAmt;
    }

    public void setCreditLimitAmt(String creditLimitAmt) {
        this.creditLimitAmt = creditLimitAmt;
    }

    public List<RepaymentBillEntity> getRepaymentBills() {
        return repaymentBills;
    }

    public void setRepaymentBills(List<RepaymentBillEntity> repaymentBills) {
        this.repaymentBills = repaymentBills;
    }

    public List<RepaymentedBillEntity> getRepaymentedBills() {
        return repaymentedBills;
    }

    public void setRepaymentedBills(List<RepaymentedBillEntity> repaymentedBills) {
        this.repaymentedBills = repaymentedBills;
    }
}
