package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class WegQueryTransActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_weg_querytransaction);
    }
    public void okOnclick(View v){
        Intent intent = new Intent(getApplicationContext(), WegTransactionsActivity.class);
        startActivity(intent);
    }
}
