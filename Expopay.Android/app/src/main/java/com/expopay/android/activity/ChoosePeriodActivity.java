package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.expopay.android.R;
import com.expopay.android.adapter.gridview.PropertiesAdapter;
import com.expopay.android.model.PropertiesEntity;

import java.util.ArrayList;
import java.util.List;

public class ChoosePeriodActivity extends BaseActivity {

    private GridView myGridView;
    private PropertiesAdapter adapter;
    private static String str_periods;

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_choose_period);

        myGridView = (GridView) findViewById(R.id.buttonGridView);
        adapter = new PropertiesAdapter(this,testData());
        str_periods = adapter.getStr();
        myGridView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        intent.putExtra("str_periods", str_periods);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void blankOnClick(View view){
        intent.putExtra("str_periods", str_periods);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelOnClick(View view){
        intent.putExtra("str_periods", str_periods);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void periodOkOnclick(View view){
        intent.putExtra("str_periods", str_periods);
        setResult(RESULT_OK, intent);
        finish();
    }

    private List<PropertiesEntity> testData() {
        List<PropertiesEntity> list = new ArrayList<PropertiesEntity>();
        for (int i = 0; i < 7; i++) {
            PropertiesEntity mp = new PropertiesEntity();
            mp.setProperties("按钮"+i);
            list.add(mp);
        }
        return list;
    }

}
