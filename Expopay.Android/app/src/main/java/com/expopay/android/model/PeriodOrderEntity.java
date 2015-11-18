package com.expopay.android.model;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/22.
 */
public class PeriodOrderEntity extends OrderEntity {
    private String productQuantity;

    private String repaymentPeriod;
    private List<OrderItemEntity> orderItems;

    public PeriodOrderEntity() {
    }

    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }
}
