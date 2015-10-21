package com.expopay.android.model;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class OrderDetailsEntity extends OrderEntity {
    private String orderNumber;
    private String orderType;
    private String productImg;
    private String productName;
    private String productQutity;
    private String orderAmount;
    private String repaymentPeriod;
    private String periodAmount;
    private String serviceAmount;
    private String properties;
    private String consignee;
    private String goodsCount;
    private String orderStatus;
    private String repaymentTimes;
    private String orderTime;
    private String transcationCode;
    private String consigneeName;
    private String consigneeMobile;
    private String consigneeAddress;
    private String logisticsComp;
    private String logisticsNum;
    private String logistics;
    private String publicUtilityComp;
    private String publicUtilityNum;

    public OrderDetailsEntity() {
    }

    @Override
    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String getProductImg() {
        return productImg;
    }

    @Override
    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String getProductQutity() {
        return productQutity;
    }

    @Override
    public void setProductQutity(String productQutity) {
        this.productQutity = productQutity;
    }

    @Override
    public String getOrderAmount() {
        return orderAmount;
    }

    @Override
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    @Override
    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public String getPeriodAmount() {
        return periodAmount;
    }

    public void setPeriodAmount(String periodAmount) {
        this.periodAmount = periodAmount;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    @Override
    public String getProperties() {
        return properties;
    }

    @Override
    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    @Override
    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRepaymentTimes() {
        return repaymentTimes;
    }

    public void setRepaymentTimes(String repaymentTimes) {
        this.repaymentTimes = repaymentTimes;
    }

    @Override
    public String getOrderTime() {
        return orderTime;
    }

    @Override
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTranscationCode() {
        return transcationCode;
    }

    public void setTranscationCode(String transcationCode) {
        this.transcationCode = transcationCode;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getLogisticsComp() {
        return logisticsComp;
    }

    public void setLogisticsComp(String logisticsComp) {
        this.logisticsComp = logisticsComp;
    }

    public String getLogisticsNum() {
        return logisticsNum;
    }

    public void setLogisticsNum(String logisticsNum) {
        this.logisticsNum = logisticsNum;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getPublicUtilityComp() {
        return publicUtilityComp;
    }

    public void setPublicUtilityComp(String publicUtilityComp) {
        this.publicUtilityComp = publicUtilityComp;
    }

    public String getPublicUtilityNum() {
        return publicUtilityNum;
    }

    public void setPublicUtilityNum(String publicUtilityNum) {
        this.publicUtilityNum = publicUtilityNum;
    }
}
