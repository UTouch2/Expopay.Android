package com.expopay.android.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.android.kechong.lib.util.BitmapUtil;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.WelcomePagerAdepter;

public class WelcomeActivity extends BaseActivity {

    private ViewPager viewPager;
    private LinearLayout indexes;
    private int[] ids;
    private View[] guides;
    private ImageView pointone, pointtwo, pointthree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initPerp();
        initView();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.welcome_viewpager);
        indexes = (LinearLayout) findViewById(R.id.welcome_indexes);
        pointone = (ImageView) findViewById(R.id.welcome_pointone);
        pointtwo = (ImageView) findViewById(R.id.welcome_pointtwo);
        pointthree = (ImageView) findViewById(R.id.welcome_pointthree);

        ids = new int[]{R.mipmap.welcom_first, R.mipmap.welcom_second,
                R.mipmap.welcome_third};
        guides = new View[ids.length];
        for (int i = 0; i < ids.length; i++) {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            if (i == ids.length - 1) {
                View v = LayoutInflater.from(this).inflate(
                        R.layout.view_welcome_last, null);
                v.setBackgroundDrawable(new BitmapDrawable(BitmapUtil
                        .readBitMap(this, R.mipmap.welcome_third)));
                guides[i] = (v);
            } else {
                ImageView iv = new ImageView(this);
                iv.setImageBitmap(BitmapUtil.readBitMap(this, ids[i]));
                iv.setLayoutParams(params);
                iv.setScaleType(ScaleType.FIT_XY);
                guides[i] = (iv);
            }
        }
        viewPager.setAdapter(new WelcomePagerAdepter(guides));
        viewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                super.onPageScrollStateChanged(arg0);
                if (arg0 == 0) {
                    setPointByIndex(viewPager.getCurrentItem());
                }
            }
        });
        setPointByIndex(0);
    }

    @Override
    protected void initPerp() {

    }

    private void setPointByIndex(int index) {
        if (index == 0) {
            pointone.setImageResource(R.mipmap.welcome_oneselceted);
            pointtwo.setImageResource(R.mipmap.welcome_twonormal);
            pointthree.setImageResource(R.mipmap.welcome_threenormal);
        }
        if (index == 1) {
            pointone.setImageResource(R.mipmap.welcome_onenormal);
            pointtwo.setImageResource(R.mipmap.welcome_twoselceted);
            pointthree.setImageResource(R.mipmap.welcome_threenormal);
        }
        if (index == 2) {
            pointone.setImageResource(R.mipmap.welcome_onenormal);
            pointtwo.setImageResource(R.mipmap.welcome_twonormal);
            pointthree.setImageResource(R.mipmap.welcome_threeselceted);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void finishOnclick(View v) {
        finish();
    }
}
