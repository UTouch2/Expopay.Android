package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/11/18.
 */
public class OrderItemEntity implements Serializable {
    private String productImg;
    private String productName;
    private String productPrice;
    private String propertyName1;
    private String propertyName2;
    private String propertyValue1;
    private String propertyValue2;

    public OrderItemEntity() {
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getPropertyName1() {
        return propertyName1;
    }

    public void setPropertyName1(String propertyName1) {
        this.propertyName1 = propertyName1;
    }

    public String getPropertyName2() {
        return propertyName2;
    }

    public void setPropertyName2(String propertyName2) {
        this.propertyName2 = propertyName2;
    }

    public String getPropertyValue1() {
        return propertyValue1;
    }

    public void setPropertyValue1(String propertyValue1) {
        this.propertyValue1 = propertyValue1;
    }

    public String getPropertyValue2() {
        return propertyValue2;
    }

    public void setPropertyValue2(String propertyValue2) {
        this.propertyValue2 = propertyValue2;
    }
}
