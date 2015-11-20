package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/11/19.
 */
public class ProductImageEntity implements Serializable {
    private String imgUrl;
    private String imgName;

    public ProductImageEntity() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
