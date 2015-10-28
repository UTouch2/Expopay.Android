package com.expopay.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/27.
 */
public class CustormLoadingView extends FrameLayout {
    View loadingView, retryView;
    TextView msgText;

    public CustormLoadingView(Context context) {
        super(context);
        init();
    }

    public CustormLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustormLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.view_loading, null, false);
        loadingView = v.findViewById(R.id.loadingview_loading);
        retryView = v.findViewById(R.id.loadingview_retry);
        msgText = (TextView) v.findViewById(R.id.loadingview_msg);
        addView(v);
    }

    public void setMessage(String msg) {
        msgText.setText(msg);
    }

    public void dismiss() {
        this.setVisibility(GONE);
    }

    public void show() {
        this.setVisibility(VISIBLE);
    }

    public void showRetry() {
        loadingView.setVisibility(GONE);
        retryView.setVisibility(VISIBLE);
    }

    public void setRetryOnclickListener(final OnClickListener l) {
        retryView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingView.setVisibility(VISIBLE);
                retryView.setVisibility(GONE);
                if (null != l) {
                    l.onClick(v);
                }
            }
        });
    }
}
