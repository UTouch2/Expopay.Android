package com.expopay.android.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.AbsFragmentActivity;
import com.expopay.android.R;
import com.expopay.android.systembar.SystemBarTintManager;

/**
 * Created by misxu012 on 2015/10/16.
 */
public class BaseActivity extends AbsFragmentActivity {
    protected TextView titleTextView;
    protected ImageView leftButton, rightButtton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCustomActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    protected void statusBarCoverActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    protected void setStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // 创建状态栏的管理实例
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 激活导航栏设置
            tintManager.setNavigationBarTintEnabled(true);
            // 设置一个颜色给系统栏
            // tintManager.setTintColor(Color.parseColor("#ff0000"));
            // 设置一个样式背景给导航栏
            // tintManager.setNavigationBarTintColor(Color.parseColor("#ff0000"));
            // 设置一个状态栏资源
            tintManager.setStatusBarTintColor(Color.parseColor("#3D3D3D"));
        }
    }

    private void initCustomActionBar() {
        final ActionBar actionBar = getActionBar();
        if (actionBar == null) {
            return;
        }
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View v = LayoutInflater.from(this).inflate(R.layout.view_myactionbar, null);
        titleTextView = (TextView) v.findViewById(R.id.title);
        leftButton = (ImageView) v.findViewById(R.id.leftbutton);
        rightButtton = (ImageView) v.findViewById(R.id.rightbutton);
        actionBar.setCustomView(v);//自定义ActionBar布局
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
