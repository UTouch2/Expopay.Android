package com.expopay.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.expopay.android.R;

import org.w3c.dom.Text;

/**
 * Created by misxu012 on 2015/10/28.
 */
public class CustormLoadingButton extends FrameLayout {
    private boolean isLoading;
    private View loadinView;
    private TextView loadingTextView;
    private TextView contentTextView;

    private OnClickListener onClickListener;

    public CustormLoadingButton(Context context) {
        super(context);
        init();
    }

    public CustormLoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustormLoadingButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.view_loadingbutton, null, false);
        loadinView = v.findViewById(R.id.loadingbutton_loading);
        loadingTextView = (TextView) v.findViewById(R.id.loadingbutton_message);
        contentTextView = (TextView) v.findViewById(R.id.loadingbutton_text);
        addView(v);
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLoading && onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }
        });
    }

    public void setLoadingText(String loadingText) {
        loadingTextView.setText(loadingText);
    }

    public void setText(String loadingText) {
        contentTextView.setText(loadingText);
    }

    public void showLoading() {
        loadinView.setVisibility(View.VISIBLE);
        contentTextView.setVisibility(View.GONE);
    }

    public void dismissLoading() {
        loadinView.setVisibility(View.GONE);
        contentTextView.setVisibility(View.VISIBLE);
    }

    public void setLoading(boolean flag) {
        isLoading = flag;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
