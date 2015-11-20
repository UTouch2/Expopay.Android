package com.expopay.android.model;

import java.io.Serializable;

/**
 * Created by misxu012 on 2015/11/18.
 */
public class MessageEntity implements Serializable {
    private String msgId;
    private String title;
    private String message;
    private String status;
    private String readAt;

    public MessageEntity() {
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReadAt() {
        return readAt;
    }

    public void setReadAt(String readAt) {
        this.readAt = readAt;
    }
}
