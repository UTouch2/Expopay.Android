package com.expopay.android.serivice;

import java.io.IOException;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.http.listener.FileLoadRequestListener;
import com.android.kechong.lib.util.ApkUtil;
import com.android.kechong.lib.util.FileManager;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.request.AppRequest;

public class DownLoadService extends Service {

    private String mSavePath;
    private RemoteViews contentView;
    NotificationManager nm;
    Notification notification;

    @Override
    public IBinder onBind(Intent arg0) {
        return new DownLoadServiceBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSavePath = FileManager.apkPath + "ExpoPay.Android.apk";
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification = new Notification();
        showCustomizeNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppRequest request = new AppRequest(MyApplication.HOST + "/install/ExpoPay.Android.apk");
        request.setRequestMethod(RequestMethod.GET);
        request.setIRequestListener(new FileLoadRequestListener(mSavePath) {
            @Override
            public void onSuccess(Object result) {
                System.out.println(result);
            }

            @Override
            public void onProgressUpdate(int i, int j) {
                if (j == FINISH) {
                    try {
                        notification.tickerText = "更新下载完成,点击安装！";
                        nm.notify(100, notification);
                        nm.cancel(100);
                        ApkUtil.installApk(mSavePath, DownLoadService.this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (j == DOING) {
                    notification.contentView.setProgressBar(
                            R.id.nodification_progress, 100, i, false);
                    nm.notify(100, notification);
                }
            }

            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
            }
        });
        request.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        nm.cancel(100);
        super.onDestroy();
    }

    public class DownLoadServiceBinder extends Binder {
        public DownLoadService getService() {
            return DownLoadService.this;
        }
    }

    public void showCustomizeNotification() {
        notification.icon = R.drawable.icon;// 图标
        notification.tickerText = "南博卡更新";
        contentView = new RemoteViews(getPackageName(),
                R.layout.view_download_notification);
        contentView.setImageViewResource(R.id.nodification_icon,
                R.drawable.icon);
        notification.contentView = contentView;// 通知显示的布局
    }
}
