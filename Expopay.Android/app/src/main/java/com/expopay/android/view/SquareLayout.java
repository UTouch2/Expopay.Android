package com.expopay.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.expopay.android.R;

public class SquareLayout extends LinearLayout {

    private double proportion;

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.SquareLayout);
        proportion = t.getFloat(R.styleable.SquareLayout_proportion, 1.1f);
        t.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec),
                getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                (int) (childWidthSize * proportion), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
