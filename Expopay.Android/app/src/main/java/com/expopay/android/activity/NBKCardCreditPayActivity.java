package com.expopay.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.expopay.android.R;
import com.expopay.android.fragment.NBKCardCreditPayFragment;
import com.expopay.android.fragment.NBKCardpayFragment;

/**
 * Created by misxu012 on 2015/11/25.
 */
public class NBKCardCreditPayActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_nbkcardpay);
        Fragment f = new NBKCardCreditPayFragment();
        f.setArguments(getIntent().getExtras());
        repleaceFragment(R.id.nbkcardpay_container, f);
    }
    public void closeOnclick(View v) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
