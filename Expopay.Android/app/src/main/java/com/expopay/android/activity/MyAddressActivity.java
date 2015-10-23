package com.expopay.android.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.expopay.android.R;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class MyAddressActivity extends BaseActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaddress);
        listView = (ListView) findViewById(R.id.myaddress_listview);
    }

}
