package com.expopay.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.expopay.android.R;

import java.lang.reflect.Field;

/**
 * Created by misxu012 on 2015/11/2.
 */
public class CustormViewPager extends ViewPager {
    private Boolean isScrollable = false;
    private int duration = 1000;

    public CustormViewPager(Context context) {
        super(context);
        init();
    }

    public CustormViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.CustormViewPager);
        duration = t.getInt(R.styleable.CustormViewPager_duration, 1000);
        t.recycle();

        init();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    private void init() {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(getContext(),
                    new AccelerateInterpolator());
            scroller.setmDuration(getDuration());
            field.set(this, scroller);
        } catch (Exception e) {
        }
    }

    public static class FixedSpeedScroller extends Scroller {
        private int mDuration = 1500;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }

    }
}
