package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.AddressEntity;
import com.expopay.android.model.CityEntity;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class AddressDetailsActivity extends BaseActivity {
    AddressEntity address;
    CityEntity city;
    private EditText addressText, zipcodeText, nameText, mobileText;

    private TextView cityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressdetails);
        addressText = (EditText) findViewById(R.id.address_address_text);
        zipcodeText = (EditText) findViewById(R.id.address_zipcode_text);
        nameText = (EditText) findViewById(R.id.address_name_text);
        mobileText = (EditText) findViewById(R.id.address_mobile_text);
        cityText = (TextView) findViewById(R.id.address_city_text);
        address = (AddressEntity) getIntent().getSerializableExtra("address");
        if (null != addressText) {
            cityText.setText(address.getProvinceName() + address.getCityName() + address.getDistrictName());
            addressText.setText(address.getAddress());
            zipcodeText.setText(address.getZipCode());
            nameText.setText(address.getPersonName());
            mobileText.setText(address.getMobile());
        }
    }

    public void chooseCityOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), ChooseCityActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            city = (CityEntity) data.getSerializableExtra("city");
            cityText.setText(city.getProvinceName() + city.getCityName() + city.getDistrictName());
        }
    }
}