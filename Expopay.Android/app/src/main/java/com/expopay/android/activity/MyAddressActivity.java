package com.expopay.android.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.expopay.android.R;
import com.expopay.android.adapter.listview.MyAddressListAdapter;
import com.expopay.android.model.AddressEntity;
import com.expopay.android.model.CardEntity;

import java.util.ArrayList;
import java.util.List;

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
        listView.setAdapter(new MyAddressListAdapter(getApplicationContext(),testDate()));
    }
    private List<AddressEntity> testDate(){
        List<AddressEntity> list = new ArrayList<AddressEntity>();
        for(int i = 0;i<10;i++){
            AddressEntity  e =new AddressEntity();
            e.setIsDefault("0");
            e.setAddress("海源中路1088号和成国际A座25楼");
            e.setAddressId("" + i);
            e.setCityName("昆明市");
            e.setDistrictName("五华区");
            e.setMobile("13245678901");
            e.setPersonName("克里斯");
            e.setProvinceName("云南省");
            e.setZipCode("234567890");
            list.add(e);
        }
        return list;
    }
}
