package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.adapter.listview.MyCardsListAdapter;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CardEntity;
import com.expopay.android.request.CardRequest;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/22.
 */
public class MyCardsActivity extends BaseActivity {
    ListView listView;
    CustormLoadingView loadingView;
    List<CardEntity> data;
    MyCardsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_mycards);
        listView = (ListView) findViewById(R.id.mycards_listview);
        loadingView = (CustormLoadingView) findViewById(R.id.mycards_loading);
        adapter = new MyCardsListAdapter(getApplicationContext(), new ArrayList<CardEntity>());
        listView.setAdapter(adapter);
        try {
            getMyCards(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void lossOnclick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getMyCards(String openId) throws JSONException {
        loadingView.show();
        CardRequest request = new CardRequest(MyApplication.HOST + "/system/version");
        request.setEntity(request.createCardListParams(openId));
        request.setOutTime(10 * 1000);
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 成功
                        Gson gson = new Gson();
                        List<CardEntity> list = gson.fromJson(json.getJSONObject("").toString(), new TypeToken<List<CardEntity>>() {
                        }.getType());
                        adapter.setData(list);
                        adapter.notifyDataSetChanged();
                        loadingView.dismiss();
                    } else {
                        // 失败
                        loadingView.showRetry();
                        loadingView.setMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    // 失败
                    loadingView.showRetry();
                    loadingView.setMessage("数据解析异常");
                }
            }

            @Override
            public void onProgressUpdate(int i, int j) {

            }

            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
                loadingView.showRetry();
                loadingView.setMessage("请求失败");
                loadingView.dismiss();
                adapter.setData(null);
                adapter.notifyDataSetChanged();
            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void testData() {
        data = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CardEntity e = new CardEntity();
            e.setCardNumber("123456789" + i);
            data.add(e);
        }
    }
}
