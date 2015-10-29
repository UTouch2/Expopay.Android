package com.expopay.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.expopay.android.R;
import com.expopay.android.application.MyApplication;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class AboutActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       statusBarCoverActivity();
        setContentView(R.layout.activity_about);
    }

    public void phoneOnclick(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + MyApplication.PHONE));
        startActivity(intent);
    }

    public void emailOnclick(View v) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:"+MyApplication.EMAIL));
        startActivity(data);
    }

    public void netOnclick(View v) {
        Intent intent = new Intent(AboutActivity.this,
                WebActivity.class);
        intent.putExtra("title", "官网");
        intent.putExtra("url", MyApplication.NET);
        startActivity(intent);
    }

    public void advanceOnclick(View v) {
        Intent intent = new Intent(AboutActivity.this,
                AdvanceActivity.class);
        startActivity(intent);
    }

    public void versionOnclick(View v) {

    }
}
