package com.expopay.android.adapter.pager;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewStub;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class BannerPagerAdapter extends PagerAdapter {
    View[] views;

    public BannerPagerAdapter(View[] views) {
        super();
        this.views = views;
    }
    @Override
    public int getCount() {
        return views.length;
    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView(views[arg1]);
    }

    @Override
    public void finishUpdate(View arg0) {
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(views[arg1], 0);
        return views[arg1];
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (arg1);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void startUpdate(View arg0) {

    }
}
