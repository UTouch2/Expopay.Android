package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.MyCardsListAdapter;
import com.expopay.android.model.CardEntity;
import com.expopay.android.request.CardRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class MyCardsActivity extends BaseActivity {
    ListView listView;
    List<CardEntity> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mycards);
        listView =(ListView)findViewById(R.id.mycards_listview);
        data = testDate();
        listView.setAdapter(new MyCardsListAdapter(getApplicationContext(),data));
    }
    public void lossOnclick(View v){

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private  List<CardEntity>  testDate(){
        List<CardEntity> list = new ArrayList<CardEntity>();
        for(int i = 0;i<10;i++){
            CardEntity  e =new CardEntity();
            e.setBalance("20");
            e.setCardType("qweqweq");
            e.setIsDefault("0");
            e.setCardNumber("88888888888888888");
            list.add(e);
        }
        return list;
    }
}
