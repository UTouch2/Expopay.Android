package com.expopay.android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.android.kechong.lib.listener.AbsOnPageChangeListener;
import com.expopay.android.R;
import com.expopay.android.adapter.pager.MainPagerAdepter;
import com.expopay.android.fragment.RepaymentFragment;
import com.expopay.android.fragment.UnrepaymentFragment;

public class MyBillsActivity extends BaseActivity {

    private Button btn_unrepayment;
    private Button btn_repayment;
    private ViewPager viewPager;

    private void assignViews() {
        btn_unrepayment = (Button) findViewById(R.id.btn_unrepayment);
        btn_repayment = (Button) findViewById(R.id.btn_repayment);
        viewPager = (ViewPager) findViewById(R.id.bill_content);
        viewPager.setOnPageChangeListener(new AbsOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                super.onPageScrollStateChanged(arg0);
                    setTabSelection(viewPager.getCurrentItem());
            }
        });

        btn_unrepayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTabSelection(0);
            }
        });
        btn_repayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTabSelection(1);
            }
        });
        viewPager.setAdapter(new MainPagerAdepter(getSupportFragmentManager(), new Fragment[]{new UnrepaymentFragment(), new RepaymentFragment()}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("我的账单");
        setContentView(R.layout.activity_my_bills);

        assignViews();
        setTabSelection(0);
    }

    private void setTabSelection(int index) {
        clearSelection();
        if(0 == index){
            btn_unrepayment.setBackgroundColor(Color.parseColor("#BCBCBC"));
            btn_unrepayment.setTextColor(Color.WHITE);
        }else {
            btn_repayment.setBackgroundColor(Color.parseColor("#BCBCBC"));
            btn_repayment.setTextColor(Color.WHITE);
        }
        viewPager.setCurrentItem(index);
    }

    private void clearSelection() {
        btn_unrepayment.setBackgroundColor(Color.WHITE);
        btn_unrepayment.setTextColor(Color.parseColor("#666666"));
        btn_repayment.setBackgroundColor(Color.WHITE);
        btn_repayment.setTextColor(Color.parseColor("#666666"));
    }
}
