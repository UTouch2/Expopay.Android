package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.request.CardRequest;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class MyCardsActivity extends BaseActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycards);
        listView =(ListView)findViewById(R.id.mycards_listview);
    }
    public void lossOnclick(View v){

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    CardRequest request = new CardRequest("");
}
