package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class LoginByVerifycodeActivity extends BaseActivity {
    private CustormViewPager viewPager;
    private TextView timeoutText;
    private EditText mobileText;
    private EditText loginVercode;
    private Button getVercodeBtn;
    private CustormLoadingButton loginBtn;
    private View contentView;
    private String mobile, vercode;
    private int time = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_login_byverifycode);
        initView();
    }

    @Override
    protected void initView() {
        contentView = findViewById(R.id.login_contentview);
        contentView.setBackgroundDrawable(new BitmapDrawable
                (BitmapUtil.readBitMap(getApplicationContext(), R.drawable.about_bg)));
        viewPager = (CustormViewPager) findViewById(R.id.login_viewpager);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setCurrentItem(100);
        timeoutText = (TextView) findViewById(R.id.login_timeout_btn);
        mobileText = (EditText) findViewById(R.id.login_mobiletext);
        loginVercode = (EditText) findViewById(R.id.login_vercode);
        getVercodeBtn = (Button) findViewById(R.id.btn_sendvercode);
        loginBtn = (CustormLoadingButton) findViewById(R.id.login_ok);
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
        loginBtn.setEnabled(false);
        mobileText.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                mobile = mobileText.getText().toString().trim();
                vercode = loginVercode.getText().toString().trim();
                if (11 == mobile.length() && 6 == vercode.length()) {
                    loginBtn.setEnabled(true);
                } else {
                    loginBtn.setEnabled(false);
                }
            }
        });
        loginVercode.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                mobile = mobileText.getText().toString().trim();
                vercode = loginVercode.getText().toString().trim();
                if (11 == mobile.length() && 6 == vercode.length()) {
                    loginBtn.setEnabled(true);
                    loginBtn.setBackgroundResource(R.drawable._button);
                } else {
                    loginBtn.setEnabled(false);
                    loginBtn.setBackgroundResource(R.drawable._button_down);
                }
            }
        });
        getVercodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = mobileText.getText().toString().trim();
                try {
                    sendVercode(mobile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile = mobileText.getText().toString().trim();
                vercode = loginVercode.getText().toString().trim();
                try {
                    loginRequest(mobile, vercode, "", "");
                } catch (Exception e) {

                }
            }
        });
        loginBtn.showNormal("登录");
    }

    public void passwordLoginOnclick(View view) {
        Intent intent = new Intent(this, LoginByPasswordActivity.class);
        startActivity(intent);
        finish();
    }

    public void closeOnclick(View v) {
        finish();
    }

    public void forgetPasswordOnclick(View view) {
        finish();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int startIndex = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(startIndex);
        }
    };

    Handler timeoutHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int time = msg.arg1;
                getVercodeBtn.setEnabled(false);
                timeoutText.setText(time + "秒");
            } else {
                getVercodeBtn.setEnabled(true);
                getVercodeBtn.setText("重 新 获 取");
            }
        }
    };

    public void startTimer() {
        time = 60;
        final Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (time <= 0) {
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    timeoutHandler.sendMessage(msg);
                    timer.cancel();
                } else {
                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    msg.arg1 = time;
                    timeoutHandler.sendMessage(msg);
                }
                time--;
            }
        };
        timer.schedule(tt, 0, 1000);
    }

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

    private void sendVercode(String mobile) throws JSONException {
        CustomerRequest re = new CustomerRequest(MyApplication.HOST + "/system/sendcode");
        re.setEntity(re.createGetVerCodeParams(mobile));
        re.setOutTime(10000);
        re.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                System.out.print(e);
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject js = (JSONObject) o;
                try {
                    if (js.getJSONObject("header").getString("code").equals("0000")) {
                        startTimer();
                    } else {
                        Toast.makeText(LoginByVerifycodeActivity.this, js.getJSONObject("header").getString("desc"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        re.execute();
        cancelRequest(re);
    }

    private void loginRequest(String mobile,
                              String vercode, String userName, String loginPwd) throws Exception {
        loginBtn.showLoading("正在验证···");
        CustomerRequest re = new CustomerRequest(MyApplication.HOST + "/customer/login");
        re.setEntity(re.createLoginParams(mobile, vercode, userName, loginPwd));
        re.setOutTime(10000);
        re.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loginBtn.showResult("网络请求失败", false);
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        UserEntity user = new UserEntity();
                        user.setOpenId(json.getJSONObject("body").getString("openId"));
                        saveUser(user);
                        loginBtn.showResult("登录成功", true);
                        finish();
                    } else {
                        loginBtn.showResult("", false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        re.execute();
        cancelRequest(re);
    }
}
