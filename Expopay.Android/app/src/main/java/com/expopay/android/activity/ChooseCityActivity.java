package com.expopay.android.activity;

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
    }

    public void closeOnclick(View v) {
        finish();
    }

    public void okOnclick(View v) {
        finish();
    }

    private List<CityEntity> testPData() {
        List<CityEntity> list = new ArrayList<CityEntity>();
        for (int i = 0; i < 10; i++) {
            CityEntity c = new CityEntity();
            c.setId(1);
            c.setName("北京市");
            c.setLevel(1);
            list.add(c);
        }
        return list;
    }

    private List<CityEntity> testCData() {
        List<CityEntity> list = new ArrayList<CityEntity>();
        for (int i = 0; i < 10; i++) {
            CityEntity c = new CityEntity();
            c.setId(1);
            c.setName("北京市");
            c.setLevel(2);
            list.add(c);
        }
        return list;
    }

    private List<CityEntity> testDData() {
        List<CityEntity> list = new ArrayList<CityEntity>();
        for (int i = 0; i < 10; i++) {
            CityEntity c = new CityEntity();
            c.setId(1);
            c.setName("朝阳区");
            c.setLevel(3);
            list.add(c);
        }
        return list;
    }
}
