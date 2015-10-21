package com.expopay.android.model;

/**
 * Created by misxu012 on 2015/10/15.
 */
public class UpdateAppEntity {
    private String versionCode;
    private String[] updateexplain;

    public UpdateAppEntity() {
    }

    public String getVersionCode() {
        return versionCode;
    }

    public String[] getUpdateexplain() {
        return updateexplain;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public void setUpdateexplain(String[] updateexplain) {
        this.updateexplain = updateexplain;
    }
}
