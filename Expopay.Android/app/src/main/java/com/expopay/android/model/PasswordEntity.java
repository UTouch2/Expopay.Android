package com.expopay.android.model;

/**
 * Created by misxu012 on 2015/10/15.
 */
public class PasswordEntity {
    String userName;
    String mobile;
    String secuQuestionId;
    String secuAnswer;
    String newLoginPwd;

    public PasswordEntity() {
    }

    public String getUserName() {
        return userName;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSecuQuestionId() {
        return secuQuestionId;
    }

    public String getSecuAnswer() {
        return secuAnswer;
    }

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSecuQuestionId(String secuQuestionId) {
        this.secuQuestionId = secuQuestionId;
    }

    public void setSecuAnswer(String secuAnswer) {
        this.secuAnswer = secuAnswer;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }
}
