package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class WegTransactionsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wegtransaction);
    }

    public void okOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), NBCardPayActivity.class);
        startActivity(intent);
    }
}
