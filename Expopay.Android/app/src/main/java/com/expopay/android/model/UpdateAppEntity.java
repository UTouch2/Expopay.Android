package com.expopay.android.model;

/**
 * Created by misxu012 on 2015/10/15.
 */
public class UpdateAppEntity {
    private String versionCode;
    private String[] updateExplain;

    public UpdateAppEntity() {
    }

    public String getVersionCode() {
        return versionCode;
    }


    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String[] getUpdateExplain() {
        return updateExplain;
    }

    public void setUpdateExplain(String[] updateExplain) {
        this.updateExplain = updateExplain;
    }
}
