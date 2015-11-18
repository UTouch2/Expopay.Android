package com.expopay.android.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.android.kechong.lib.util.BitmapUtil;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.WelcomePagerAdepter;

public class WelcomeActivity extends BaseActivity {

	private ViewPager viewPager;
	private LinearLayout indexes;
	private int[] ids;
	private View[] guides;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initPerp();
		initView();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void initView() {
		viewPager = (ViewPager) findViewById(R.id.welcome_viewpager);
		indexes = (LinearLayout) findViewById(R.id.welcome_indexes);
		ids = new int[] { R.mipmap.welcom_first, R.mipmap.welcom_second,
				R.mipmap.welcome_third };
		guides = new View[ids.length];
		for (int i = 0; i < ids.length; i++) {
			ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT);
			if (i == ids.length - 1) {
				View v = LayoutInflater.from(this).inflate(
						R.layout.view_welcome_last, null);
				v.setBackgroundDrawable(new BitmapDrawable(BitmapUtil
						.readBitMap(this, R.mipmap.welcome_third)));
				guides[i] = (v);
			} else {
				ImageView iv = new ImageView(this);
				iv.setImageBitmap(BitmapUtil.readBitMap(this, ids[i]));
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.FIT_XY);
				guides[i] = (iv);
			}
		}
		viewPager.setAdapter(new WelcomePagerAdepter(guides));
		for (int i = 0; i < ids.length; i++) {
			ImageView iv = new ImageView(this);
//			iv.setImageResource(R.mipmap.welcome_point_on);
//			int width = (int) getResources().getDimension(R.dimen.twenty);
//			ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width,
//					width);
//			int padding = getResources().getDimensionPixelSize(R.dimen.three);
//			iv.setPadding(padding, padding, padding, padding);
//			iv.setLayoutParams(params);
//			iv.setScaleType(ScaleType.FIT_XY);
			indexes.addView(iv);
		}
		viewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
				super.onPageScrollStateChanged(arg0);
				if (arg0 == 0) {
					setPointByIndex(viewPager.getCurrentItem());
				}
			}
		});
		setPointByIndex(0);
	}

	@Override
	protected void initPerp() {

	}

	private void setPointByIndex(int index) {
//		for (int i = 0; i < indexes.getChildCount(); i++) {
//			ImageView iv = (ImageView) indexes.getChildAt(i);
//			iv.setImageResource(R.mipmap.welcome_point_on);
//		}
//		ImageView iv = (ImageView) indexes.getChildAt(index);
//		iv.setImageResource(R.mipmap.welcome_point_off);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void finishOnclick(View v) {
		finish();
	}
}
