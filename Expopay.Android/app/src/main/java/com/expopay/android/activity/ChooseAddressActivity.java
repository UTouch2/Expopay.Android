package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.ChooseAddressAdapter;
import com.expopay.android.model.AddressEntity;

import java.util.List;

public class ChooseAddressActivity extends BaseActivity {

    private ListView listView;
    ChooseAddressAdapter adapter;
    List<AddressEntity> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("选择收货地址");
        setContentView(R.layout.activity_choose_adress);

        addressList = (List<AddressEntity>) getIntent().getSerializableExtra("address");
        listView = (ListView) findViewById(R.id.chooseaddress_listview);
        adapter = new ChooseAddressAdapter(this, addressList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("entity", addressList.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
