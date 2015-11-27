package com.android.kechong.lib.cache;

import java.io.Serializable;

import android.graphics.Bitmap;

public class CacheEntity implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long time;
    private Object content;
    private String stringContent;
    private Bitmap bitmapContent;

    public CacheEntity() {
        super();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getStringContent() {
        if (content instanceof String) {
            return (String) content;
        }
        return null;
    }

    public void setStringContent(String stringContent) {
        this.stringContent = stringContent;
    }

    public Bitmap getBitmapContent() {
        if (content instanceof Bitmap) {
            return (Bitmap) content;
        }
        return null;
    }

    public void setBitmapContent(Bitmap bitmapContent) {
        this.bitmapContent = bitmapContent;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public int sizeOf() {
        int size = 0;
        if (time != null) {
            size += time.toString().length();
        }
        if (stringContent != null) {
            size += stringContent.toString().length();
        }
        if (bitmapContent != null) {
            size += bitmapContent.toString().length();
        }
        return size;
    }
}
