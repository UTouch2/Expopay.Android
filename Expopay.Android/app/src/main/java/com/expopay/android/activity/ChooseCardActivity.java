package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.ChooseCardListAdapter;
import com.expopay.android.model.CardEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/24.
 */
public class ChooseCardActivity extends BaseActivity {
    ListView mListView;
    List<CardEntity> cards = new ArrayList<>();

    CardEntity result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choosecard);
        mListView = (ListView) findViewById(R.id.choosecard_listview);
        testData();
        mListView.setAdapter(new ChooseCardListAdapter(this, cards));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                result = cards.get(position);
            }
        });
    }
    public void closeOnclick(View v) {
        finish();
    }

    public void okOnclick(View v) {
        setResult(RESULT_OK, new Intent().putExtra("card", result));
        finish();
    }
    private void getCards() {

    }

    private void testData() {
        for (int i = 0; i < 2; i++) {
            CardEntity e = new CardEntity();
            e.setCardNumber("123456789");
            cards.add(e);
        }
    }
}
