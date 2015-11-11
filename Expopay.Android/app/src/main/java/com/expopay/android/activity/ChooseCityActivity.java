package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.CityListAdapter;
import com.expopay.android.db.DBManager;
import com.expopay.android.model.CardEntity;
import com.expopay.android.model.CityEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChooseCityActivity extends BaseActivity {
    ListView pListview, cListview, dListview;
    List<CityEntity> pList, cList, dList;
    CityEntity choosedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecity);
        pListview = (ListView) findViewById(R.id.choosecity_province_list);
        cListview = (ListView) findViewById(R.id.choosecity_city_list);
        dListview = (ListView) findViewById(R.id.choosecity_district_list);
        pList = DBManager.getCardList(1, 0);
        pListview.setAdapter(new CityListAdapter(getApplicationContext(), pList));
        pListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cList = DBManager.getCardList(2, pList.get(position).getId());
                cListview.setAdapter(new CityListAdapter(getApplicationContext(), cList));
            }
        });
        cListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dList = DBManager.getCardList(3, cList.get(position).getId());
                dListview.setAdapter(new CityListAdapter(getApplicationContext(), dList));
            }
        });
        dListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosedCity = dList.get(position);
            }
        });
    }

    public void closeOnclick(View v) {
        finish();
    }

    public void okOnclick(View v) {
        setResult(RESULT_OK, new Intent().putExtra("city", choosedCity));
        finish();
    }
}
