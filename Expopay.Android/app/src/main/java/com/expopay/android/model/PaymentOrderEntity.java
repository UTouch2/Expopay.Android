package com.expopay.android.model;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/22.
 */
public class PaymentOrderEntity extends OrderEntity {
    private String publicType;
    private String publicParamName;
    private String publicParamValue;
    private String publicParamText;
    private List<OrderItemEntity> orderItems;

    public PaymentOrderEntity() {
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPublicType() {
        return publicType;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType;
    }

    public String getPublicParamName() {
        return publicParamName;
    }

    public void setPublicParamName(String publicParamName) {
        this.publicParamName = publicParamName;
    }

    public String getPublicParamValue() {
        return publicParamValue;
    }

    public void setPublicParamValue(String publicParamValue) {
        this.publicParamValue = publicParamValue;
    }

    public String getPublicParamText() {
        return publicParamText;
    }

    public void setPublicParamText(String publicParamText) {
        this.publicParamText = publicParamText;
    }
}
