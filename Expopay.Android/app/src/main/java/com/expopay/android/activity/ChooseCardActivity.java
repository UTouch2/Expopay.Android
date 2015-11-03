package com.expopay.android.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.model.CardEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/24.
 */
public class ChooseCardActivity extends BaseActivity {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecard);
        mListView = (ListView) findViewById(R.id.choosecard_listview);
       // mListView.setAdapter(new ArrayAdapter<CardEntity>(this,android.R.it));
    }

    private void getCards() {

    }

    private List<String> testData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("12345678909");
        }
        return list;
    }
}
