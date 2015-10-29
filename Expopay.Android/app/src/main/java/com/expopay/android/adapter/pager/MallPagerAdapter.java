package com.expopay.android.adapter.pager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by NB-MIS-100002 on 2015/10/29.
 */
public class MallPagerAdapter extends PagerAdapter {

    View[] views;

    public MallPagerAdapter(View[] views) {
        super();
        this.views = views;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % views.length;
        View v = views[position];
        container.addView(v, 0);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % views.length;
        container.removeView(views[position]);
    }
}
