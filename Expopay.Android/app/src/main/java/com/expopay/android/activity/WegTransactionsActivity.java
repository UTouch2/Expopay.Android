package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.utils.NBKCardPayUtil;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class WegTransactionsActivity extends BaseActivity {
    String orderNumber, orderSource,orderAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_wegtransaction);
    }

    public void okOnclick(View v) {
        NBKCardPayUtil.nbkCardPay(this,orderNumber,orderSource,orderAmount);
    }
    public void closeOnclick(View v) {
       finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            finish();
        }
    }
}
