package com.android.kechong.lib.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;

public class ViewUtil {
	/**
	 * 测量给定的View的宽和高, 测量之后, 可以得到view的宽和高
	 * 
	 * @param child
	 */
	public static void measureView(View child) {
		// 得到参数对象
		ViewGroup.LayoutParams lp = child.getLayoutParams();
		if (lp == null) {
			lp = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);

		int lpHeight = lp.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}
}
