package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsTextWatcher;
import com.android.kechong.lib.util.BitmapUtil;
import com.android.kechong.lib.util.SharedRefUtil;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.request.CustomerRequest;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class LoginByPasswordActivity extends BaseActivity {
    private View contentView;
    private ViewPager viewPager;
    private EditText login_phonenum;
    private EditText login_pwd;
    private CustormLoadingButton loginButton;


    private String userName, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_login_bypassword);
        initView();
    }

    @Override
    protected void initView() {
        contentView = findViewById(R.id.login_contentview);
        contentView.setBackgroundDrawable(new BitmapDrawable(BitmapUtil.readBitMap(getApplicationContext(), R.drawable.about_bg)));
        login_phonenum = (EditText) findViewById(R.id.login_phonenum);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        viewPager = (ViewPager) findViewById(R.id.login_viewpager);
        loginButton = (CustormLoadingButton) findViewById(R.id.login_ok);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setCurrentItem(100);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000l);
                        handler.sendEmptyMessage(1);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
        loginButton.setEnabled(false);
        loginButton.setBackgroundResource(R.drawable._button_down);
        login_phonenum.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                String phonenum = login_phonenum.getText().toString().trim();
                String pwd = login_pwd.getText().toString().trim();
                if (0 == pwd.length() && 11 == phonenum.length()) {
                    loginButton.setEnabled(true);
                    loginButton.setBackgroundResource(R.drawable._button);
                } else {
                    loginButton.setEnabled(false);
                    loginButton.setBackgroundResource(R.drawable._button_normal);
                }
            }
        });
        login_pwd.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                String phonenum = login_phonenum.getText().toString().trim();
                String pwd = login_pwd.getText().toString().trim();
                if (6 == pwd.length() && 11 == phonenum.length()) {
                    loginButton.setEnabled(true);
                    loginButton.setBackgroundResource(R.drawable._button_down);
                } else {
                    loginButton.setEnabled(false);
                    loginButton.setBackgroundResource(R.drawable._button_down);
                }
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = login_phonenum.getText().toString().trim();
                password = login_pwd.getText().toString().trim();
                try {
                    loginRequest("", "", userName, password);
                } catch (Exception e) {

                }
            }
        });
    }

    @Override
    protected void initPerp() {

    }

    public void messageOnclick(View v) {
        Intent intent = new Intent(this, LoginByVerifycodeActivity.class);
        startActivity(intent);
    }

    public void forgetPasswordOnclick(View v) {
        Intent intent = new Intent(this, LoginByVerifycodeActivity.class);
        startActivity(intent);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int startIndex = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(startIndex);
        }
    };

    private View[] createViews() {
        View[] views = new View[6];
        ImageView view = new ImageView(getApplicationContext());
        views[0] = view;
        views[0].setBackgroundResource(R.mipmap.loginbanner_01);
        view = new ImageView(getApplicationContext());
        views[1] = view;
        views[1].setBackgroundResource(R.mipmap.loginbanner_02);
        view = new ImageView(getApplicationContext());
        views[2] = view;
        views[2].setBackgroundResource(R.mipmap.loginbanner_03);
        view = new ImageView(getApplicationContext());
        views[3] = view;
        views[3].setBackgroundResource(R.mipmap.loginbanner_04);
        view = new ImageView(getApplicationContext());
        views[4] = view;
        views[4].setBackgroundResource(R.mipmap.loginbanner_05);
        view = new ImageView(getApplicationContext());
        views[5] = view;
        views[5].setBackgroundResource(R.mipmap.loginbanner_06);
        return views;
    }

    private void loginRequest(String phoneNum,
                              String vercode, final String userName, String loginPwd) throws Exception {
        CustomerRequest re = new CustomerRequest(MyApplication.HOST + "");
        re.setEntity(re.createLoginParams(phoneNum, vercode, userName, loginPwd));
        re.setOutTime(10000);
        re.setIRequestListener(new JsonRequestListener() {


            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("")) {
                        String openId = json.getJSONObject("body").getString("openId");
                        SharedRefUtil.setSharedPreference(LoginByPasswordActivity.this, MyApplication.USERNAME, userName);
                        SharedRefUtil.setSharedPreference(LoginByPasswordActivity.this, MyApplication.PASSWORD, password);
                        SharedRefUtil.setSharedPreference(LoginByPasswordActivity.this, MyApplication.OPENID, password);
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onProgressUpdate(int i, int i1) {

            }
            @Override
            public void onFilure(Exception e) {

            }
        });
        re.execute();
        cancelRequest(re);
    }
}

