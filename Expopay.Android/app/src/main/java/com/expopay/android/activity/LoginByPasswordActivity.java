package com.expopay.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kechong.lib.http.RequestMethod;
import com.android.kechong.lib.http.listener.BitmapRequestListener;
import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsTextWatcher;
import com.android.kechong.lib.util.SharedRefUtil;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.request.AppRequest;
import com.expopay.android.request.CustomerRequest;

import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class LoginByPasswordActivity extends BaseActivity {
    ViewPager viewPager;
    int startIndex = 100;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bypassword);
        viewPager = (ViewPager) findViewById(R.id.login_viewpager);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
        viewPager.setCurrentItem(startIndex);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000l);
                        startIndex++;
                        handler.sendEmptyMessage(1);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
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

    private void loginRequest() throws Exception {
        CustomerRequest re = new CustomerRequest(MyApplication.HOST + "");
        re.setEntity(re.createLoginParams("", "", "", ""));
        re.setRequestMethod(RequestMethod.GET);
        re.setOutTime(10000);
        re.setIRequestListener(new BitmapRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {
                JSONObject js = (JSONObject) o;
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        re.execute();
        cancelRequest(re);
    }
}
