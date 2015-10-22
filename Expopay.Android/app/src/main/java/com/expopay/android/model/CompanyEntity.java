package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class CompanyEntity  implements Serializable{
    String  companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
