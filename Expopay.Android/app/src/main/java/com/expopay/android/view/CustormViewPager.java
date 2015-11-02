package com.expopay.android.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by misxu012 on 2015/11/2.
 */
public class CustormViewPager extends ViewPager {
    private Boolean isScrollable = false;

    public CustormViewPager(Context context) {
        super(context);
    }

    public CustormViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void scrollTo(int x, int y) {
        if (isScrollable) {
            super.scrollTo(x, y);
        }
    }
}
