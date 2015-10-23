package com.expopay.android.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.expopay.android.R;

public class OrderDetailItemActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_item);
    }

    public void btnCancel(View view){
        finish();
    }

    public void btnOk(View view){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
