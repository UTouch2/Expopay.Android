package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class PasswordQuestionEntity implements Serializable {
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
