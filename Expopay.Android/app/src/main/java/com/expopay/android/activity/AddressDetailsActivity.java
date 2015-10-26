package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class AddressDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressdetails);
    }

    public void chooseCityOnclick(View v) {
        Intent intent =new Intent(getApplicationContext(),ChooseCityActivity.class);
        startActivity(intent);
    }
}
