package com.expopay.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;

/**
 * Created by misxu012 on 2015/10/28.
 */
public class CustormLoadingButton extends CustormViewPager {
    private TextView loadingTextView;
    private TextView normalTextView;
    private TextView resultTextView;
    private ImageView resultIcon;
    private OnClickListener onClickListener;
    private BannerPagerAdapter adapter;

    public CustormLoadingButton(Context context) {
        super(context);
        init();
    }

    public CustormLoadingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View normoal = LayoutInflater.from(getContext()).inflate(R.layout.view_custormbutton_normal, null, false);
        View loading = LayoutInflater.from(getContext()).inflate(R.layout.view_custormbutton_loading, null, false);
        View result = LayoutInflater.from(getContext()).inflate(R.layout.view_custormbutton_result, null, false);
        loadingTextView = (TextView) loading.findViewById(R.id.loadingbutton_loadingmsg);
        normalTextView = (TextView) normoal.findViewById(R.id.loadingbutton_normaltext);
        resultTextView = (TextView) result.findViewById(R.id.loadingbutton_resultmsg);

        resultIcon = (ImageView) result.findViewById(R.id.loadingbutton_resulticon);

        adapter = new BannerPagerAdapter(new View[]{normoal, loading, result});
        this.setAdapter(adapter);
        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    setCurrentItem(getCurrentItem() + 1);
                    onClickListener.onClick(v);
                }
            }
        });
    }


    public void showNormal(String text) {
        int index = getCurrentItem();
        if (index != 0 && index % 3 == 2) {
            setCurrentItem(index + 1);
        }
        normalTextView.setText(text);
    }

    public void showLoading(String text) {
        int index = getCurrentItem();
        if (index % 3 == 0) {
            setCurrentItem(index + 1);
        }
        loadingTextView.setText(text);
    }

    public void showResult(String text, boolean isSuccess) {
        int index = getCurrentItem();
        if (index % 3 == 1) {
            setCurrentItem(index + 1);
        }
        if (isSuccess) {
            resultIcon.setImageResource(R.mipmap.loadingbutton_resultok);
        } else {
            resultIcon.setImageResource(R.mipmap.loadingbutton_resultok);
        }
        resultTextView.setText(text);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
