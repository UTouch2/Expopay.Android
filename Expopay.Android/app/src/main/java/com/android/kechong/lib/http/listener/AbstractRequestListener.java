package com.android.kechong.lib.http.listener;

import com.android.kechong.lib.http.Exception.RequestInterruptedException;

public abstract class AbstractRequestListener implements com.android.kechong.lib.http.listener.IRequestListener {

    /**
     * 完成
     */
    public static final int FINISH = 1;
    /**
     * 正在下载
     */
    public static final int DOING = 2;
    public boolean isCancelled;

    public AbstractRequestListener() {
        super();
    }

    @Override
    public void cancel() {
        isCancelled = true;
    }

    @Override
    public void checkIfCanceled() throws RequestInterruptedException {
        if (isCancelled) {
            throw new RequestInterruptedException("request has been cancelled");
        }
    }

    public Integer[] getProgress() {
        return null;
    }
}
