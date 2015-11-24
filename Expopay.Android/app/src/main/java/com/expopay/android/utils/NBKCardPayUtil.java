package com.expopay.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.expopay.android.activity.BaseActivity;
import com.expopay.android.activity.NBCardPayActivity;

/**
 * Created by misxu012 on 2015/11/4.
 */
public class NBKCardPayUtil {
    public static final int PAY_REQUEST_CODE = 99;

    /**
     * @param activity
     * @param orderNumer
     * @param orderSource
     * @param orderAmount
     */
    public static void nbkCardPay(Activity activity, String orderNumer, String orderSource, String orderAmount) {
        String payStatus = ((BaseActivity) activity).getUser().getPayStatus();
        // 设置支付密码页面
        if ("0".equals(payStatus)) {

        } else if ("1".equals(payStatus)) {
            Intent intent = new Intent(activity, NBCardPayActivity.class);
            intent.putExtra("orderNumber", orderNumer);
            intent.putExtra("orderSource", orderSource);
            intent.putExtra("orderAmount", orderAmount);
            activity.startActivityForResult(intent, PAY_REQUEST_CODE);
        }

    }
}
