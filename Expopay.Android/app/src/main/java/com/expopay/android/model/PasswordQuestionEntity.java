package com.expopay.android.model;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class PasswordQuestionEntity {
    private String secuQuestionId;
    private String secuQuestion;
    public PasswordQuestionEntity() {
    }

    public String getSecuQuestionId() {
        return secuQuestionId;
    }

    public void setSecuQuestionId(String secuQuestionId) {
        this.secuQuestionId = secuQuestionId;
    }

    public String getSecuQuestion() {
        return secuQuestion;
    }

    public void setSecuQuestion(String secuQuestion) {
        this.secuQuestion = secuQuestion;
    }
}
