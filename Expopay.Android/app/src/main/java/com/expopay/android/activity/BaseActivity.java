package com.expopay.android.activity;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.AbsFragmentActivity;
import com.android.kechong.lib.util.SharedRefUtil;
import com.expopay.android.R;
import com.expopay.android.model.UserEntity;
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

    @Override
    public void repleaceFragment(int root, Fragment f) {
        super.repleaceFragment(root, f);
    }

    @Override
    public void addFragment(int root, Fragment f) {
        super.addFragment(root, f);
    }

    @Override
    public void removeFragment(Fragment f) {
        super.removeFragment(f);
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

    void initCustomActionBar() {
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

    public UserEntity getUser() {
        UserEntity user = new UserEntity();
        String openId = SharedRefUtil.getSharedPreference(this, "openId", "");
        String userName = SharedRefUtil.getSharedPreference(this, "userName", "");
        String password = SharedRefUtil.getSharedPreference(this, "password", "");
        String mobile = SharedRefUtil.getSharedPreference(this, "mobile", "");
        String userType = SharedRefUtil.getSharedPreference(this, "userType", "");
        String nickname = SharedRefUtil.getSharedPreference(this, "nickname", "");
        String certificationStatus = SharedRefUtil.getSharedPreference(this, "certificationStatus", "");
        String personName = SharedRefUtil.getSharedPreference(this, "personName", "");
        String personId = SharedRefUtil.getSharedPreference(this, "personId", "");
        String defCardNumber = SharedRefUtil.getSharedPreference(this, "defCardNumber", "");
        String companyName = SharedRefUtil.getSharedPreference(this, "companyName", "");
        String payStatus = SharedRefUtil.getSharedPreference(this, "payStatus", "");

        user.setOpenId(openId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setMobile(mobile);
        user.setUserType(userType);
        user.setNickname(nickname);
        user.setCertificationStatus(certificationStatus);
        user.setPersonId(personId);
        user.setPersonName(personName);
        user.setDefCardNumber(defCardNumber);
        user.setCompanyName(companyName);
        user.setPayStatus(payStatus);
        return user;
    }

    public void saveUser(UserEntity user) {
        String openId = user.getOpenId();
        String userName = user.getUserName();
        String password = user.getPassword();
        String mobile = user.getMobile();
        String userType = user.getUserType();
        String nickname = user.getNickname();
        String certificationStatus = user.getCertificationStatus();
        String personName = user.getPersonName();
        String personId = user.getPersonId();
        String defCardNumber = user.getDefCardNumber();
        String companyName = user.getCompanyName();
        String payStatus = user.getPayStatus();
        if (openId != null) {
            SharedRefUtil.setSharedPreference(this, "openId", openId);
        }
        if (userName != null) {
            SharedRefUtil.setSharedPreference(this, "userName", userName);
        }
        if (password != null) {
            SharedRefUtil.setSharedPreference(this, "password", password);
        }
        if (mobile != null) {
            SharedRefUtil.setSharedPreference(this, "mobile", mobile);
        }
        if (userType != null) {
            SharedRefUtil.setSharedPreference(this, "userType", userType);
        }
        if (nickname != null) {
            SharedRefUtil.setSharedPreference(this, "nickname", nickname);
        }
        if (personName != null) {
            SharedRefUtil.setSharedPreference(this, "personName", personName);
        }
        if (certificationStatus != null) {
            SharedRefUtil.setSharedPreference(this, "certificationStatus", certificationStatus);
        }
        if (personId != null) {
            SharedRefUtil.setSharedPreference(this, "personId", personId);
        }
        if (defCardNumber != null) {
            SharedRefUtil.setSharedPreference(this, "defCardNumber", defCardNumber);
        }
        if (companyName != null) {
            SharedRefUtil.setSharedPreference(this, "companyName", companyName);
        }
        if (payStatus != null) {
            SharedRefUtil.setSharedPreference(this, "payStatus", payStatus);
        }
    }
}
