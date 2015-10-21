package com.expopay.android.model;

/**
 * Created by misxu012 on 2015/10/15.
 */
public class UserEntity {
    private String openId;
    private String mobile;
    private String userName;
    private String userType;
    private String nickname;
    private String certificationStatus;
    private String personName;
    private String personId;
    private String defCardNumber;
    private String companyName;
    private String payStatus;
    public UserEntity() {

    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCertificationStatus(String certificationStatus) {
        this.certificationStatus = certificationStatus;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public void setDefCardNumber(String defCardNumber) {
        this.defCardNumber = defCardNumber;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOpenId() {
        return openId;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCertificationStatus() {
        return certificationStatus;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonId() {
        return personId;
    }

    public String getDefCardNumber() {
        return defCardNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPayStatus() {
        return payStatus;
    }
}
