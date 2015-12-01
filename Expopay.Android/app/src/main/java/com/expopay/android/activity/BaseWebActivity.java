package com.expopay.android.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.expopay.android.R;
import com.expopay.android.view.CustormLoadingView;


public class BaseWebActivity extends BaseActivity {
    protected WebView webView;
    private String url, title;
    private CustormLoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        setTitle(title);
        webView = (WebView) findViewById(R.id.webview);
        loadingView = (CustormLoadingView) findViewById(R.id.web_loadingview);
        // 添加一个监听，去处理url的分打开方式
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    loadingView.dismiss();
                }
            }
        });
        loadingView.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(url);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {
                if (newUrl.indexOf("tel:") < 0) {
                    view.loadUrl(newUrl);
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                loadingView.setRetryMessage("网络请求失败");
                loadingView.showRetry();
            }
        });
    }

    public final void addJavascriptInterface(Object object) {
        //webView.addJavascriptInterface(object, "android");
        webView.loadUrl(url);
    }

    public final void returnResult(String jsName, String... params) {
        String str = "javascript:" + jsName + "(";
        for (int i = 0; i < params.length; i++) {
            str += "'" + params[i] + "'";
            if (i < params.length - 1) {
                str += ",";
            }
        }
        str += ")";
        webView.loadUrl(str);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void closeOnclick(View v) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
