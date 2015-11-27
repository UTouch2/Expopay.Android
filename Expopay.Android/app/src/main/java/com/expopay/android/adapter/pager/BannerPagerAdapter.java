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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class BannerPagerAdapter extends PagerAdapter {
    List<View> views;

    public BannerPagerAdapter(View[] views) {
        super();
        this.views =new ArrayList();
        for (int i = 0; i < views.length; i++) {
            this.views.add(views[i]);
        }
    }

    public BannerPagerAdapter(List<View> views) {

        this.views = views;
    }

    /**
     * 获取当前页面数
     */
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 适配器给container容器添加视图
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % views.size();
        if (position < views.size()) {
            View v = views.get(position);
            if (v.getParent() == null) {
                container.addView(v, 0);
            } else {
                ((ViewGroup) v.getParent()).removeView(v);
                container.addView(v);
            }
            return v;
        }
        return null;
    }

    /**
     * 适配器移除container容器中的视图
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        position = position % views.length;
//        container.removeView(views[position]);
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(View[] views) {
        this.views =new ArrayList();
        for (int i = 0; i < views.length; i++) {
            this.views.add(views[i]);
        }
    }

    public void setViews(List<View> views) {
        this.views = views;
    }
}
