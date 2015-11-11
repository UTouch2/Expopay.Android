package com.expopay.android.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.expopay.android.R;
import com.expopay.android.adapter.pager.MainPagerAdepter;
import com.expopay.android.fragment.PaymentOrderFragment;
import com.expopay.android.fragment.PeriodOrderFragment;

public class MyOrderActivity extends BaseActivity {

    private PeriodOrderFragment periodOrderFragment;
    private PaymentOrderFragment paymentOrderFragment;

    private Button periodOrderBtn;
    private Button paymentOrderBtn;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_my_order);
        // 初始化布局元素
        initView();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    @Override
    protected void initView() {
        periodOrderBtn = (Button) findViewById(R.id.btn_installmentOrders);
        paymentOrderBtn = (Button) findViewById(R.id.btn_paymentOrders);
        viewPager = (ViewPager) findViewById(R.id.myorder_viewpager);
        periodOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(0);
            }
        });
        paymentOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(1);
            }
        });
        viewPager.setAdapter(new MainPagerAdepter(getSupportFragmentManager(), new Fragment[]{new PeriodOrderFragment(), new PaymentOrderFragment()}));
    }


    /**
     * 根据传入的index参数来设置选中的tab页。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        if (index == 0) {
            periodOrderBtn.setBackgroundColor(Color.parseColor("#BCBCBC"));
            periodOrderBtn.setTextColor(Color.WHITE);
        } else {
            paymentOrderBtn.setBackgroundColor(Color.parseColor("#BCBCBC"));
            paymentOrderBtn.setTextColor(Color.WHITE);
        }
        viewPager.setCurrentItem(index);
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        periodOrderBtn.setBackgroundColor(Color.WHITE);
        periodOrderBtn.setTextColor(Color.parseColor("#666666"));
        paymentOrderBtn.setBackgroundColor(Color.WHITE);
        paymentOrderBtn.setTextColor(Color.parseColor("#666666"));
    }
}
