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
    View loadingView, retryView, addView;
    TextView retryMsgText, loadingMsgText, addMsgText;
    OnClickListener retryOnclickListener, addOnclickListener;

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
        addView = v.findViewById(R.id.loadingview_add);

        retryMsgText = (TextView) v.findViewById(R.id.loadingview_retrymsg);
        loadingMsgText = (TextView) v.findViewById(R.id.loadingview_loadingmsg);
        addMsgText = (TextView) v.findViewById(R.id.loadingview_addmsg);

        retryView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != retryOnclickListener) {
                    retryOnclickListener.onClick(v);
                }
            }
        });
        addView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != addOnclickListener) {
                    addOnclickListener.onClick(v);
                }
            }
        });
        addView(v);
    }

    public void setLoadingMessage(String msg) {
        loadingMsgText.setText(msg);
    }

    public void setRetryMessage(String msg) {
        retryMsgText.setText(msg);
    }

    public void setAddMessage(String msg) {
        addMsgText.setText(msg);
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
        addView.setVisibility(GONE);
    }

    public void showLoading() {
        loadingView.setVisibility(VISIBLE);
        retryView.setVisibility(GONE);
        addView.setVisibility(GONE);
    }

    public void showAdd() {
        loadingView.setVisibility(GONE);
        retryView.setVisibility(GONE);
        addView.setVisibility(VISIBLE);
    }

    public void setRetryOnclickListener(OnClickListener l) {
        retryOnclickListener = l;
    }

    public void setAddOnclickListener(OnClickListener addOnclickListener) {
        this.addOnclickListener = addOnclickListener;
    }
}
