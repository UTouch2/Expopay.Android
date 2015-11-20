package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by NB-MIS-100002 on 2015/10/22.
 */
public class MallProductEntity implements Serializable {
    private String productId;
    private String productPrice;
    private String productImg;
    private String productName;
    private String productType;

    public MallProductEntity() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
