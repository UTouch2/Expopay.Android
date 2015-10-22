package com.expopay.android.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.expopay.android.R;
import com.expopay.android.fragment.PeriodOrderFragment;
import com.expopay.android.fragment.PaymentOrderFragment;

public class MyOrderActivity extends BaseActivity implements View.OnClickListener{

    private PeriodOrderFragment installmentOrderFragment;
    private PaymentOrderFragment paymentOrderFragment;
    private FragmentManager fragmentManager;

    private Button btnInstallmentOrders;
    private Button btnPaymentOrders;
    private FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        // 初始化布局元素
        assignViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void assignViews() {
        btnInstallmentOrders = (Button) findViewById(R.id.btn_installmentOrders);
        btnPaymentOrders = (Button) findViewById(R.id.btn_paymentOrders);
        content = (FrameLayout) findViewById(R.id.content);

        btnInstallmentOrders.setOnClickListener(this);
        btnPaymentOrders.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_installmentOrders:
                // 当点击了分期订单tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.btn_paymentOrders:
                // 当点击了缴费订单tab时，选中第2个tab
                setTabSelection(1);
                break;
            default:
                break;
        }
    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示分期订单，1表示缴费订单。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的背景颜色和文字颜色
                btnInstallmentOrders.setBackgroundColor(Color.parseColor("#0000FF"));
                btnInstallmentOrders.setTextColor(Color.WHITE);
                if (paymentOrderFragment == null) {
                    // 如果paymentOrderFragment为空，则创建一个并添加到界面上
                    paymentOrderFragment = new PaymentOrderFragment();
                    transaction.add(R.id.content, paymentOrderFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(paymentOrderFragment);
                }
                break;
            default:
                // 当点击了设置tab时，改变控件的背景颜色和文字颜色
                btnPaymentOrders.setBackgroundColor(Color.parseColor("#0000FF"));
                btnPaymentOrders.setTextColor(Color.WHITE);
                if (installmentOrderFragment == null) {
                    // 如果installmentOrderFragment为空，则创建一个并添加到界面上
                    installmentOrderFragment = new PeriodOrderFragment();
                    transaction.add(R.id.content, installmentOrderFragment);
                } else {
                    // 如果installmentOrderFragment不为空，则直接将它显示出来
                    transaction.show(installmentOrderFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        btnInstallmentOrders.setBackgroundColor(Color.WHITE);
        btnInstallmentOrders.setTextColor(Color.BLACK);
        btnPaymentOrders.setBackgroundColor(Color.WHITE);
        btnPaymentOrders.setTextColor(Color.BLACK);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (installmentOrderFragment != null) {
            transaction.hide(installmentOrderFragment);
        }
        if (paymentOrderFragment != null) {
            transaction.hide(paymentOrderFragment);
        }
    }

}
