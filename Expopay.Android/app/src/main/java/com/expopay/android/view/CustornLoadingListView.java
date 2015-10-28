package com.expopay.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.android.kechong.lib.util.ViewUtil;

/**
 * Created by misxu012 on 2015/10/28.
 */
public class CustornLoadingListView extends ListView {
    private CustormLoadingButton custormLoadingButton;
    private int footerViewHeight;

    public CustornLoadingListView(Context context) {
        super(context);
    }

    public CustornLoadingListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustornLoadingListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        custormLoadingButton = new CustormLoadingButton(getContext());
        ViewUtil.measureView(custormLoadingButton); // 测量一下脚布局的高度
        footerViewHeight = custormLoadingButton.getMeasuredHeight();
        custormLoadingButton.setPadding(0, -footerViewHeight, 0, 0); // 隐藏脚布局
        this.addFooterView(custormLoadingButton);
    }

    public CustormLoadingButton getCustormButton() {
        return custormLoadingButton;
    }

    public void showLoading() {
        custormLoadingButton.setPadding(0, 0, 0, 0); // 隐藏脚布局
    }

    public void dismissLoading() {
        custormLoadingButton.setPadding(0, -footerViewHeight, 0, 0); // 隐藏脚布局
    }
}
