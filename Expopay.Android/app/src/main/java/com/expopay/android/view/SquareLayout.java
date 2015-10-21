package com.expopay.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
public class SquareLayout extends LinearLayout {

	public SquareLayout(Context context) {
		super(context);
	}
	public SquareLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
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
		heightMeasureSpec = widthMeasureSpec = MeasureSpec.makeMeasureSpec(
				childWidthSize, MeasureSpec.EXACTLY);
		this.setMinimumHeight((int)(childWidthSize));
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
