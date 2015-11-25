package com.expopay.android.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by misxu012 on 2015/11/19.
 */
public class ProductDetailsEntity implements Serializable {
    private String productId;
    private String productName;
    private String productPrice;
    private List<ProductImageEntity> productImgs;
    private String productImg;
    private String productType;
    private String productAd;
    private String propertyId1;
    private String propertyId2;
    private String propertyName1;
    private String propertyName2;
    private String propertyValue1;
    private String propertyValue2;
    private String quantity;

    private List<ProductPeroidEntity> productPeriods;

    public ProductDetailsEntity() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public List<ProductImageEntity> getProductImgs() {
        return productImgs;
    }

    public void setProductImgs(List<ProductImageEntity> productImgs) {
        this.productImgs = productImgs;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPropertyId1() {
        return propertyId1;
    }

    public void setPropertyId1(String propertyId1) {
        this.propertyId1 = propertyId1;
    }

    public String getPropertyId2() {
        return propertyId2;
    }

    public void setPropertyId2(String propertyId2) {
        this.propertyId2 = propertyId2;
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

    public List<ProductPeroidEntity> getProductPeriods() {
        return productPeriods;
    }

    public void setProductPeriods(List<ProductPeroidEntity> productPeriods) {
        this.productPeriods = productPeriods;
    }

    public String getProductAd() {
        return productAd;
    }

    public void setProductAd(String productAd) {
        this.productAd = productAd;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
