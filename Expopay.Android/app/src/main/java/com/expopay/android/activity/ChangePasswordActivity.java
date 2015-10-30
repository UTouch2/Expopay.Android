package com.expopay.android.activity;

import android.os.Bundle;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangePasswordActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_changepassword);
    }
}
