package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.CompanyListAdapter;
import com.expopay.android.model.CompanyEntity;

import java.util.List;

/**
 * Created by misxu012 on 2015/10/21.
 */
public class ChooseCompanyActivity extends BaseActivity {
    private ListView listView;
    List<CompanyEntity> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_weg_choosecompany);
        listView = (ListView) findViewById(R.id.choosecompany_listview);
        Intent intent = getIntent();
        list = (List<CompanyEntity>) intent.getExtras()
                .getSerializable("list");
        CompanyListAdapter adepter = new CompanyListAdapter(this, list);
        listView.setAdapter(adepter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int i, long l) {
                CompanyEntity companyEntity = list.get(i);
                Intent data = new Intent();
                data.putExtra("CompanyEntity", companyEntity);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
