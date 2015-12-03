package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsTextWatcher;
import com.android.kechong.lib.util.BitmapUtil;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.UserEntity;
import com.expopay.android.request.CustomerRequest;
import com.expopay.android.view.CustormLoadingButton;
import com.expopay.android.view.CustormViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.kechong.lib.util.PatternUtil.checkUserName;
import static com.android.kechong.lib.util.PatternUtil.isMobile;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class LoginByPasswordActivity extends BaseActivity {
    private View contentView;
    private CustormViewPager viewPager;
    private EditText login_phonenum;
    private EditText login_pwd;
    private ImageView showPsdImageView;
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
        showPsdImageView = (ImageView) findViewById(R.id.loginbypassword_showpsd_btn);
        viewPager = (CustormViewPager) findViewById(R.id.login_viewpager);
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
        loginButton.showNormal("登 录");
        loginButton.setEnabled(false);
        loginButton.setBackgroundResource(R.drawable._button_down);
        loginButton.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                loginButton.showNormal("登 录");
            }
        });

        showPsdImageView.setOnClickListener(new View.OnClickListener() {
            boolean flag = true;
            @Override
            public void onClick(View v) {
                if (flag) {
                    login_pwd.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                } else {
                    login_pwd.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    showPsdImageView.setImageResource(R.mipmap.changepassword_icon);
                }
                flag = !flag;
            }
        });
        login_phonenum.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                String phonenum = login_phonenum.getText().toString().trim();
                String pwd = login_pwd.getText().toString().trim();
                if (6 == pwd.length() && (2 <= phonenum.length()) && (11 >= phonenum.length())) {
                    loginButton.setEnabled(true);
                    loginButton.setBackgroundResource(R.drawable._button);
                } else {
                    loginButton.setEnabled(false);
                    loginButton.setBackgroundResource(R.drawable._button_down);
                }
            }
        });
        login_pwd.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                String phonenum = login_phonenum.getText().toString().trim();
                String pwd = login_pwd.getText().toString().trim();
                if (6 == pwd.length() && (2 <= phonenum.length()) && (11 >= phonenum.length())) {
                    loginButton.setEnabled(true);
                    loginButton.setBackgroundResource(R.drawable._button);
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
                if (checkUserName(userName) || isMobile(userName)){
                    try {
                        loginRequest("", "", userName, password);
                    } catch (Exception e) {

                    }
                }else {
                    Toast.makeText(LoginByPasswordActivity.this, "用户名不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initPerp() {

    }

    public void closeOnclick(View v) {
        finish();
    }

    public void messageOnclick(View v) {
        Intent intent = new Intent(this, LoginByVerifycodeActivity.class);
        startActivity(intent);
        finish();
    }

    public void forgetPwdOnclick(View view){

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
        loginButton.showLoading("正在登录···");
        CustomerRequest re = new CustomerRequest(MyApplication.HOST + "/customer/login");
        re.setEntity(re.createLoginParams(phoneNum, vercode, userName, loginPwd));
        re.setOutTime(10000);
        re.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("")) {
                        String openId = json.getJSONObject("body").getString("openId");
                        UserEntity user = new UserEntity();
                        user.setOpenId(openId);
                        user.setUserName(userName);
                        user.setPassword(password);
                        saveUser(user);
                        loginButton.showResult("登录成功", true);
                    } else {
                        loginButton.showResult(json.getJSONObject("header").getString("desc"), false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    loginButton.showResult("数据解析异常", false);
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }

            @Override
            public void onFilure(Exception e) {
                loginButton.showResult("网络请求失败", false);
            }
        });
        re.execute();
        cancelRequest(re);
    }
}

