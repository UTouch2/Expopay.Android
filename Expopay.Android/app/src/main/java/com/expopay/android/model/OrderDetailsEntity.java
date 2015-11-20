package com.expopay.android.model;

import java.util.List;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class OrderDetailsEntity extends OrderEntity {
    private String orderNumber;
    private List<OrderItemEntity> orderItems;
    private String productQutity;
    private String orderAmount;
    private String repaymentPeriod;
    private String periodAmount;
    private String serviceAmount;
    private String payResult;
    private String orderStatus;
    private String orderTime;
    private String receiver;
    private String receiverMobile;
    private String receiverAddress;
    private String expressCompany;
    private String expressNumber;
    private List<LogisticsEntity> expressRecords;
    private String publicCompany;
    private String publicParamName;
    private String publicParamValue;
    private String publicParamText;

    public OrderDetailsEntity(){

    }

    @Override
    public String getPayResult() {
        return payResult;
    }

    @Override
    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public List<LogisticsEntity> getExpressRecords() {
        return expressRecords;
    }

    public void setExpressRecords(List<LogisticsEntity> expressRecords) {
        this.expressRecords = expressRecords;
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
    public String getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String getOrderStatus() {
        return orderStatus;
    }

    @Override
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String getOrderTime() {
        return orderTime;
    }

    @Override
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getPeriodAmount() {
        return periodAmount;
    }

    public void setPeriodAmount(String periodAmount) {
        this.periodAmount = periodAmount;
    }

    public String getProductQutity() {
        return productQutity;
    }

    public void setProductQutity(String productQutity) {
        this.productQutity = productQutity;
    }

    public String getPublicParamName() {
        return publicParamName;
    }

    public void setPublicParamName(String publicParamName) {
        this.publicParamName = publicParamName;
    }

    public String getPublicCompany() {
        return publicCompany;
    }

    public void setPublicCompany(String publicCompany) {
        this.publicCompany = publicCompany;
    }

    public String getPublicParamText() {
        return publicParamText;
    }

    public void setPublicParamText(String publicParamText) {
        this.publicParamText = publicParamText;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPublicParamValue() {
        return publicParamValue;
    }

    public void setPublicParamValue(String publicParamValue) {
        this.publicParamValue = publicParamValue;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public String getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(String serviceAmount) {
        this.serviceAmount = serviceAmount;
    }
}
