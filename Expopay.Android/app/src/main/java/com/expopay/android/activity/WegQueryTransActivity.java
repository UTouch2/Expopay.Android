package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.model.CardEntity;
import com.expopay.android.model.CompanyEntity;
import com.expopay.android.request.WegRequest;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class WegQueryTransActivity extends BaseActivity {
    List<CompanyEntity> list;
    CustormLoadingView loadingView;
    TextView companyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_weg_querytransaction);
        companyText = (TextView) findViewById(R.id.weg_company_text);
        loadingView = (CustormLoadingView) findViewById(R.id.weg_loadingview);
        try {
            getCompanyList("", "", "", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void okOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), WegTransactionsActivity.class);
        startActivity(intent);
    }

    public void chooseCompanyOnclick(View v) {
        Intent intent = new Intent(getApplicationContext(), ChooseCompanyActivity.class);
        intent.putExtra("list", (Serializable) list);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

        }
    }

    private void getCompanyList(String openId,
                                String type, String provinceCode, String cityId) throws JSONException {
        WegRequest request = new WegRequest("");
        request.setEntity(request.createGetCompanyParam(openId,
                type, provinceCode, cityId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                testData();
                companyText.setText(list.get(0).getCompanyName());
                loadingView.dismiss();
            }

            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        Gson gson = new Gson();
                        list = gson.fromJson(json.getJSONObject("").toString(), new TypeToken<List<CompanyEntity>>() {
                        }.getType());
                        companyText.setText(list.get(0).getCompanyName());
                        loadingView.dismiss();
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

    private void testData() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompanyEntity e = new CompanyEntity();
            e.setCompanyName("强娃儿推哦");
            list.add(e);
        }
    }
}
