package com.expopay.android.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.kechong.lib.util.LocationUtil;
import com.expopay.android.R;

public class BuyCardActivity extends BaseActivity {

	RelativeLayout email, tel, web;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_buycard);
		initPerp();
		initView();
	}

	@Override
	protected void initPerp() {
		setTitle("我要购卡");
	}

	@Override
	protected void initView() {

	}

	public void buycardOnclick(View v) {
		Location location = LocationUtil.getLocation(BuyCardActivity.this);
		Intent intent = new Intent(BuyCardActivity.this, WebActivity.class);
		intent.putExtra("title", "附近网点");
		if (location != null) {
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			intent.putExtra("url", "http://wx.expopay.cn/info/appmap?latitude="
					+ lat + "&longitude=" + lng);
		} else {
			intent.putExtra("url", "http:/wx.expopay.cn/info/appmap");
		}
		startActivity(intent);
		finish();
	}
}
