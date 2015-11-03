package com.expopay.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/30.
 */
public class BannerFootView extends LinearLayout {
    int w = (int) getContext().getResources().getDimension(R.dimen.bannerpointsize);

    public BannerFootView(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setPadding(5, 5, 5, 5);
        setOrientation(HORIZONTAL);
    }

    public BannerFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setGravity(Gravity.CENTER);
        setPadding(5, 5, 5, 5);
        setOrientation(HORIZONTAL);
    }

    public BannerFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        setPadding(5, 5, 5, 5);
        setOrientation(HORIZONTAL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setViewsSize(int size) {
        LayoutParams p = new LayoutParams(w, w);
        p.setMargins(5, 5, 5, 5);
        for (int i = 0; i < size; i++) {
            CircleView v = new CircleView(getContext());
            v.setLayoutParams(p);
            v.setCircleColor(Color.parseColor("#A1A19F"));
            addView(v);
        }
    }

    public void setSelectedIndex(int size, int index) {
        removeAllViews();
        LayoutParams p = new LayoutParams(w, w);
        p.setMargins(5, 5, 5, 5);
        for (int i = 0; i < size; i++) {
            CircleView v = new CircleView(getContext());
            v.setLayoutParams(p);
            if (i == index) {
                v.setCircleColor(Color.parseColor("#ffffff"));
            } else {
                v.setCircleColor(Color.parseColor("#A1A19F"));
            }
            addView(v);
        }
    }

    public void setSelectedIndex(int index) {
        LayoutParams p = new LayoutParams(w, w);
        p.setMargins(5, 5, 5, 5);
        for (int i = 0; i < getChildCount(); i++) {
            CircleView v = new CircleView(getContext());
            v.setLayoutParams(p);
            addView(v);
            if (i == index) {
                v.setCircleColor(Color.parseColor("#ffffff"));
            } else {
                v.setCircleColor(Color.parseColor("#A1A19F"));
            }
        }
    }
}
