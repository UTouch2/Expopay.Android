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
    public BannerFootView(Context context) {
        super(context);
    }

    public BannerFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setViewsSize(int size) {
        setGravity(Gravity.CENTER);
        setPadding(5, 5, 5, 5);
        setOrientation(HORIZONTAL);
        int w = (int) getContext().getResources().getDimension(R.dimen.bannerpointsize);
        for (int i = 0; i < size; i++) {
            CircleView v = new CircleView(getContext());
            LayoutParams p = new LayoutParams(w, w);
            p.setMargins(5, 5, 5, 5);
            v.setLayoutParams(p);
            v.setCircleColor(Color.parseColor("#A1A19F"));
            addView(v);
        }
    }

    public void setSelectedIndex(int index) {
        for (int i = 0; i < getChildCount(); i++) {
            CircleView v = (CircleView) getChildAt(i);
            if (i == index) {
                v.setCircleColor(Color.parseColor("#ffffff"));
            } else {
                v.setCircleColor(Color.parseColor("#A1A19F"));
            }
        }
    }
}
