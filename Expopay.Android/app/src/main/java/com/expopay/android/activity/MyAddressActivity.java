package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.adapter.listview.MyAddressListAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.AddressEntity;
import com.expopay.android.request.AddressRequest;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class MyAddressActivity extends BaseActivity {
    ListView listView;
    CustormLoadingView loadingView;
    MyAddressListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("收货信息");
        setContentView(R.layout.activity_myaddress);
        listView = (ListView) findViewById(R.id.myaddress_listview);
        loadingView = (CustormLoadingView) findViewById(R.id.myaddress_loadingview);
        adapter = new MyAddressListAdapter(this, new ArrayList<AddressEntity>());
        listView.setAdapter(adapter);
        loadingView.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getAddress(getUser().getOpenId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loadingView.setAddOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddressDetailsActivity.class);
                startActivity(intent);
            }
        });
        rightButtton.setImageResource(R.mipmap.mycards_add);
        rightButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddressDetailsActivity.class);
                startActivity(intent);
            }
        });
        try {
            getAddress(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getAddress(String openId) throws JSONException {
        loadingView.show();
        loadingView.showLoading();
        AddressRequest request = new AddressRequest(MyApplication.HOST + "/customer/addresses");
        request.setEntity(request.createGetAddressesParams(openId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loadingView.showRetry();
                loadingView.setRetryMessage("网络请求失败");
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        Gson gson = new Gson();
                        List<AddressEntity> list = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(),
                                new TypeToken<List<AddressEntity>>() {
                                }.getType());
                        if (list.size() == 0) {
                            loadingView.showAdd();
                            loadingView.setAddMessage("当前还没有添加收货地址");
                        } else {
                            adapter.setData(list);
                            adapter.notifyDataSetChanged();
                            loadingView.dismiss();
                        }
                    } else {
                        loadingView.showRetry();
                        loadingView.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    loadingView.showRetry();
                    loadingView.setRetryMessage("参数解析错误");
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }


    private List<AddressEntity> testDate() {
        List<AddressEntity> list = new ArrayList<AddressEntity>();
        for (int i = 0; i < 10; i++) {
            AddressEntity e = new AddressEntity();
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
