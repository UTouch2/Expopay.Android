package com.expopay.android.adapter.pager;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
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


    /**
     * 获取当前页面数
     */
    @Override
    public int getCount() {
        // Log.v(TAG, "getCount" + views.size());
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        // Log.v(TAG, "isViewFromObject" + (view == object));
        return view == object;
    }

    /**
     * 适配器给container容器添加视图
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % views.length;
        View v = views[position];
        container.addView(v, 0);
        return v;
    }

    /**
     * 适配器移除container容器中的视图
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % views.length;
        container.removeView(views[position]);
    }
}
