package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/11/19.
 */
public class ProductPropertyEntity implements Serializable {
    private String propertyId;
    private String propertyName;
    private String propertyValue;

    public ProductPropertyEntity() {
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
