package com.expopay.android.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class BaseWebView extends WebView {
	public BaseWebView(Context context) {
		super(context);
		init();
	}

	public BaseWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BaseWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public BaseWebView(Context context, AttributeSet attrs, int defStyle,
			boolean privateBrowsing) {
		super(context, attrs, defStyle);
		init();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void init() {
		this.requestFocus();
		WebSettings webSettings = this.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setDomStorageEnabled(true);
		webSettings.setBlockNetworkImage(false);
		webSettings.setBlockNetworkLoads(false);
		webSettings.setLoadsImagesAutomatically(true);
		webSettings.setAllowFileAccess(true);
		webSettings.setBuiltInZoomControls(true);
	}
}
