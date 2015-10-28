package com.expopay.android.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.kechong.lib.AbsFragmentActivity;
import com.expopay.android.systembar.SystemBarTintManager;

/**
 * Created by misxu012 on 2015/10/16.
 */
public class BaseActivity extends AbsFragmentActivity {
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        ActionBar mActionbar = getActionBar();
        setStatusColor();
        //statusBarCoverActivity();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_main, menu);
//        i = menu.findItem(R.id.action_notify);
        return super.onCreateOptionsMenu(menu);
    }

    public void setTitle(String title) {
        // if (initCustomActionBar())
        // tvTitle.setText(title);
    }

    protected void statusBarCoverActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
    }

    protected void setStatusColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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
            tintManager.setStatusBarTintColor(Color.parseColor("#ff0000"));
        }
    }
}
