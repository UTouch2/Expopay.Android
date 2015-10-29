package com.expopay.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsTextWatcher;
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
public class LoginByVerifycodeActivity extends BaseActivity {
    private ViewPager viewPager;
    private EditText loginPhonenum;
    private EditText loginVercode;
    private Button btnSendVercode;
    private CustormLoadingButton loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_login_byverifycode);
        initView();
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.login_viewpager);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setCurrentItem(100);

        loginPhonenum = (EditText) findViewById(R.id.login_phonenum);
        loginVercode = (EditText) findViewById(R.id.login_vercode);
        btnSendVercode = (Button) findViewById(R.id.btn_sendvercode);
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
        loginPhonenum.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                String phonenum = loginPhonenum.getText().toString().trim();
                String vercode = loginVercode.getText().toString().trim();
                if (11 == phonenum.length() && 6 == vercode.length()) {
                    loginBtn.setEnabled(true);
                    loginBtn.setBackgroundResource(R.drawable._button);
                } else {
                    loginBtn.setEnabled(false);
                    loginBtn.setBackgroundResource(R.drawable._button_down);
                }
            }
        });
        loginVercode.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                super.onTextChanged(arg0, arg1, arg2, arg3);
                String phonenum = loginPhonenum.getText().toString().trim();
                String vercode = loginVercode.getText().toString().trim();
                if (11 == phonenum.length() && 6 == vercode.length()) {
                    loginBtn.setEnabled(true);
                    loginBtn.setBackgroundResource(R.drawable._button);
                } else {
                    loginBtn.setEnabled(false);
                    loginBtn.setBackgroundResource(R.drawable._button_down);
                }
            }
        });
        btnSendVercode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonenum = loginPhonenum.getText().toString().trim();
                String vercode = loginVercode.getText().toString().trim();
                try {
                    loginRequest(phonenum, vercode, "", "");
                } catch (Exception e) {

                }
            }
        });
    }

    public void passwordLoginOnclick(View view) {
        finish();
    }

    public void forgetPasswordOnclick(View view){
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
                              String vercode, String userName, String loginPwd) throws Exception {
        CustomerRequest re = new CustomerRequest(MyApplication.HOST + "");
        re.setEntity(re.createLoginParams(phoneNum, vercode, userName, loginPwd));
        re.setOutTime(10000);
        re.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {
                JSONObject js = (JSONObject) o;
                try {
                    js.getJSONObject("");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                String phoneNum = ;
//                String vercode = ;
//
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
//                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//                String date = formatter.format(curDate);
//
//                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
//                SharedPreferences.Editor ed = sp.edit();
//                ed.putString("login_phonenum", phoneNum);
//                ed.putString("login_vercode", vercode);
//                ed.putString("login_date", date);
//                ed.commit();
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        re.execute();
        cancelRequest(re);
    }
}
