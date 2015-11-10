package com.expopay.android.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.adapter.pager.BannerPagerAdapter;

/**
 * Created by misxu012 on 2015/10/28.
 */
public class CustormLoadingButton extends FrameLayout {
    private TextView loadingTextView;
    private TextView normalTextView;
    private TextView resultTextView;
    private ImageView resultIcon;
    private OnClickListener onClickListener;
    private BannerPagerAdapter adapter;
    private ViewPager viewPager;
    private int status = 0;//0，是normal，1是loading，2是result

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
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_loadingbutton, null, false);
        View normal = LayoutInflater.from(getContext()).inflate(R.layout.view_custormbutton_normal, null, false);
        View loading = LayoutInflater.from(getContext()).inflate(R.layout.view_custormbutton_loading, null, false);
        View result = LayoutInflater.from(getContext()).inflate(R.layout.view_custormbutton_result, null, false);
        viewPager = (ViewPager) view.findViewById(R.id.loadingbutton_viewpager);
        setPadding(0, 0, 0, 0);
        normalTextView = (TextView) normal.findViewById(R.id.loadingbutton_normaltext);
        loadingTextView = (TextView) loading.findViewById(R.id.loadingbutton_loadingmsg);

        resultTextView = (TextView) result.findViewById(R.id.loadingbutton_resultmsg);
        resultIcon = (ImageView) result.findViewById(R.id.loadingbutton_resulticon);
        adapter = new BannerPagerAdapter(new View[]{normal, loading, result});
        viewPager.setAdapter(adapter);
        LayoutParams p = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        view.setLayoutParams(p);
        view.findViewById(R.id.loadingbutton_top).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }
        });

        addView(view);
    }


    public void showNormal(String text) {
        int index = viewPager.getCurrentItem();
        if (status == 2) {
            viewPager.setCurrentItem(index + 1);
            status = 0;
        }
        normalTextView.setText(text);
    }

    public void showLoading(String text) {
        int index = viewPager.getCurrentItem();
        if (status == 0) {
            viewPager.setCurrentItem(index + 1);
            status = 1;
        }
        loadingTextView.setText(text);
    }

    public void showResult(String text, boolean isSuccess) {
        int index = viewPager.getCurrentItem();
        if (status == 1) {
            viewPager.setCurrentItem(index + 1);
            status = 2;
        }
        if (isSuccess) {
            resultIcon.setImageResource(R.mipmap.loadingbutton_resultok);
        } else {
            resultIcon.setImageResource(R.mipmap.loadingbutton_resultfail);
        }
        resultTextView.setText(text);
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
