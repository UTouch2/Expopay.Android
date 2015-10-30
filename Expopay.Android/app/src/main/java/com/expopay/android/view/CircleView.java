package com.expopay.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.expopay.android.R;

public class CircleView extends TextView {

    private int borderColor =Color.parseColor("#00000000");
    private int circleColor =Color.parseColor("#00000000");;
    private float borderSize =1;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
                | Paint.FILTER_BITMAP_FLAG));
        Paint first = new Paint();
        first.setAntiAlias(true);
        first.setDither(true);
        first.setColor(borderColor);
        // 原
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2
                - borderSize, first);
        first.setColor(circleColor);
        // 边界
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2,
                first);
        super.onDraw(canvas);
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public float getBorderSize() {
        return borderSize;
    }

    public void setBorderSize(float borderSize) {
        this.borderSize = borderSize;
    }
}
