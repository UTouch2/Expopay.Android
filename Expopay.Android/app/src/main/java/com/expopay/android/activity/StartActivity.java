package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.android.kechong.lib.util.BitmapUtil;
import com.android.kechong.lib.util.FileUtil;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.utils.FileManager;

import java.io.IOException;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class StartActivity extends BaseActivity {
    View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_start);
        initView();
        initApp();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent().setClass(getApplicationContext(), MainAcativity.class));
            finish();
        }
    };

    private void initApp() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000l);
                    FileManager.createDir();
                    FileUtil.deleteFile(FileManager.dbPath
                            + MyApplication.DB_NAME);
                    FileUtil.createFileByStream(getAssets().open(MyApplication.DB_NAME), FileManager.dbPath, MyApplication.DB_NAME);
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    @Override
    protected void initPerp() {
        super.initPerp();
    }

    @Override
    protected void initView() {
        super.initView();
        contentView = findViewById(R.id.start_bg);
        contentView.setBackgroundDrawable(new BitmapDrawable(BitmapUtil.readBitMap(getApplicationContext(), R.mipmap.start_bg)));
    }
}
