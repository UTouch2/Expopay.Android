package com.expopay.android.activity;

import android.content.Intent;
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
        loadingView.setRetryOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getMyCards(getUser().getOpenId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        loadingView.setAddOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCardActivity.class);
                startActivity(intent);
            }
        });
        rightButtton.setImageResource(R.mipmap.mycards_add);
        rightButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddCardActivity.class);
                startActivity(intent);
            }
        });
        try {
            getMyCards(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void lossOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), CardLossApplyActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getMyCards(String openId) throws JSONException {
        loadingView.show();
        loadingView.showLoading();
        CardRequest request = new CardRequest(MyApplication.HOST + "/customer/cardList");
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
                        List<CardEntity> list = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(), new TypeToken<List<CardEntity>>() {
                        }.getType());
                        if (list.size() == 0) {
                            loadingView.setAddMessage("你还没有添加卡");
                            loadingView.showAdd();
                        } else {
                            adapter.setData(list);
                            adapter.notifyDataSetChanged();
                            loadingView.dismiss();
                        }
                    } else {
                        // 失败
                        loadingView.showRetry();
                        loadingView.setRetryMessage(json.getJSONObject("header").getString("desc"));
                    }
                } catch (JSONException e) {
                    // 数据解析异常
                    // 失败 63248663035
                    loadingView.showRetry();
                    loadingView.setRetryMessage("数据解析异常");
                }
            }
            @Override
            public void onProgressUpdate(int i, int j) {

            }
            @Override
            public void onFilure(Exception result) {
                System.out.println(result);
                loadingView.showRetry();
                loadingView.setRetryMessage("请求失败");
            }
        });
        request.execute();
        cancelRequest(request);
    }
}
