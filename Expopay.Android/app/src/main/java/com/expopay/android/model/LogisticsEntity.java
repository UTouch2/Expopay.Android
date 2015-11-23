package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by NB-MIS-100002 on 2015/11/19.
 */
public class LogisticsEntity implements Serializable {
    private String state;
    private String context;
    private String time;

    public LogisticsEntity(){

    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
