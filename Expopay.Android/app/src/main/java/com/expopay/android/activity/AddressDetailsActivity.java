package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.AddressEntity;
import com.expopay.android.model.CityEntity;
import com.expopay.android.request.AddressRequest;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class AddressDetailsActivity extends BaseActivity {
    private AddressEntity address;
    private CityEntity city;
    private EditText addressText, zipcodeText, nameText, mobileText;
    private TextView cityText;
    private CustormLoadingButton okbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_addressdetails);
        addressText = (EditText) findViewById(R.id.address_address_text);
        zipcodeText = (EditText) findViewById(R.id.address_zipcode_text);
        nameText = (EditText) findViewById(R.id.address_name_text);
        mobileText = (EditText) findViewById(R.id.address_mobile_text);
        cityText = (TextView) findViewById(R.id.address_city_text);
        okbtn = (CustormLoadingButton) findViewById(R.id.address_ok);

        if (getIntent().getSerializableExtra("address") != null) {
            address = (AddressEntity) getIntent().getSerializableExtra("address");
            city = new CityEntity();
            city.setId(Integer.parseInt(address.getDistrictNum()));
        } else {
            address = new AddressEntity();
        }
        cityText.setText(address.getProvinceName() + address.getCityName() + address.getDistrictName());
        addressText.setText(address.getAddress());
        zipcodeText.setText(address.getZipCode());
        nameText.setText(address.getPersonName());
        mobileText.setText(address.getMobile());
        okbtn.showNormal("确 定");
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    deleteAddress(getUser().getOpenId(), address.getAddressId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        rightButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String openId = getUser().getOpenId();
                String addressId = address.getAddressId();
                String personName = nameText.getText().toString().trim();
                String mobile = mobileText.getText().toString().trim();
                String districtNum = city.getId() + "";
                String address = addressText.getText().toString().trim();
                String zipCode = zipcodeText.getText().toString().trim();
                String isDefault = "0";
                try {
                    saveAddress(openId, addressId, personName, mobile, districtNum, address, zipCode, isDefault);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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

    private void saveAddress(String openId,
                             String addressId,
                             String personName,
                             String mobile,
                             String districtNum,
                             String address,
                             String zipCode,
                             String isDefault) throws JSONException {
        okbtn.showLoading("正在加载···");
        AddressRequest request = new AddressRequest(MyApplication.HOST + "/customer/addaddress");
        request.setEntity(request.createAddAddressParams(openId, addressId, personName, mobile, districtNum, address, zipCode, isDefault));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                okbtn.showResult("网络异常", false);
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功

                    } else {

                    }
                } catch (JSONException e) {

                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void deleteAddress(String openId, String addressId) throws JSONException {

        AddressRequest request = new AddressRequest(MyApplication.HOST + "/customer/deleteaddress");
        request.setEntity(request.createDeleteAddressParams(openId, addressId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        okbtn.showResult("", true);
                    } else {
                        okbtn.showResult(json.getJSONObject("header").getString("desc"), false);
                    }
                } catch (JSONException e) {
                    okbtn.showResult("数据解析错误", true);
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }
}