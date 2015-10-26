package com.expopay.android.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class LoginByVerifycodeActivity extends BaseActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_byverifycode);
        viewPager = (ViewPager) findViewById(R.id.login_viewpager);
        viewPager.setAdapter(new BannerPagerAdapter(createViews()));
    }
    private View[] createViews() {
        View[] views = new View[6];
        ImageView view = new ImageView(getApplicationContext());
        views[1] = view;
        views[1].setBackgroundResource(R.mipmap.loginbanner_01);
        views[2] = view;
        views[2].setBackgroundResource(R.mipmap.loginbanner_02);
        views[3] = view;
        views[3].setBackgroundResource(R.mipmap.loginbanner_03);
        views[4] = view;
        views[4].setBackgroundResource(R.mipmap.loginbanner_04);
        views[5] = view;
        views[5].setBackgroundResource(R.mipmap.loginbanner_05);
        views[6] = view;
        views[6].setBackgroundResource(R.mipmap.loginbanner_06);
        return views;
    }
}
