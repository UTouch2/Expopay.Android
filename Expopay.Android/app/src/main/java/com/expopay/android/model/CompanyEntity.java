package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class CompanyEntity implements Serializable {
    String companyName;
    String companyId;
    String publicParamName;
    String publicParamValue;
    String publicParamText;

    public CompanyEntity() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
