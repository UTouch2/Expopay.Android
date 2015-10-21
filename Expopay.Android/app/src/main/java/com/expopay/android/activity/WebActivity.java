package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class WebActivity extends BaseWebActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addJavascriptInterface(new MyJavascriptInterace());
    }

    public class MyJavascriptInterace {

        @JavascriptInterface
        public void close() {
            finish();
        }

        @JavascriptInterface
        public void readCard() {
        }

        @JavascriptInterface
        public void callBack(String jsName, String... params) {
            returnResult(jsName, params);
        }
    }
}
