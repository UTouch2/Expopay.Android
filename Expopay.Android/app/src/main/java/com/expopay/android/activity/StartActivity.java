package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.android.kechong.lib.util.BitmapUtil;
import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class StartActivity extends BaseActivity {
    View contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initView();
        contentView.setBackgroundDrawable(new BitmapDrawable(BitmapUtil.readBitMap(getApplicationContext(),R.mipmap.start_bg)));
        AsyncTask task =new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                startActivity(new Intent().setClass(getApplicationContext(),MainAcativity.class));
            }
        };
        task.execute(new String[]{});
    }
    @Override
    protected void initPerp() {
        super.initPerp();
    }
    @Override
    protected void initView() {
        super.initView();
        contentView = findViewById(R.id.start_bg);
    }
}
