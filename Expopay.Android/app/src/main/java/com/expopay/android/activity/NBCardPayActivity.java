package com.expopay.android.activity;

import android.os.Bundle;

import com.expopay.android.R;
import com.expopay.android.fragment.NBKCardpayFragment;

/**
 * Created by misxu012 on 2015/10/24.
 */
public class NBCardPayActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_nbkcardpay);
        NBKCardpayFragment f = new NBKCardpayFragment();
        f.setArguments(getIntent().getExtras());
        addFragment(R.id.nbkcardpay_container, f);
    }
}
