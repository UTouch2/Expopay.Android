package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CompanyEntity;
import com.expopay.android.request.WegRequest;
import com.expopay.android.view.CustormLoadingButton;
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
    CustormLoadingButton loadingButton;
    TextView companyText;
    EditText barcodeText;
    CompanyEntity current;
    TextView paramTextText;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_weg_querytransaction);
        type = getIntent().getStringExtra("type");
        if ("0".equals(type)) {
            setTitle("缴纳水费");
        } else if ("1".equals(type)) {
            setTitle("缴纳电费");
        }
        companyText = (TextView) findViewById(R.id.weg_company_text);
        loadingView = (CustormLoadingView) findViewById(R.id.weg_loadingview);
        loadingButton = (CustormLoadingButton) findViewById(R.id.weg_loadingbutton);
        barcodeText = (EditText) findViewById(R.id.weg_barcode_edit);
        paramTextText = (TextView) findViewById(R.id.weg_barcode_lable);
        loadingButton.showNormal("查  询");
        loadingButton.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }
            @Override
            public void onFailureResult() {
                loadingButton.showNormal("查  询");
            }
        });
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    QueryAmount(getUser().getOpenId(), current.getCompanyId(), current.getPublicParamName(), barcodeText.getText().toString().trim());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            getCompanyList(getUser().getOpenId(), type, "530000", "530100");
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
            current = (CompanyEntity) data.getSerializableExtra("CompanyEntity");
            companyText.setText(current.getCompanyName());
            paramTextText.setText(current.getPublicParamText());
        }
    }

    private void getCompanyList(String openId,
                                String type, String provinceCode, String cityId) throws JSONException {
        WegRequest request = new WegRequest(MyApplication.HOST + "/ecommerce/publicutilitycompaines");
        request.setEntity(request.createGetCompanyParam(openId,
                type, provinceCode, cityId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }
            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        Gson gson = new Gson();
                        list = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(), new TypeToken<List<CompanyEntity>>() {
                        }.getType());
                        current = list.get(0);
                        companyText.setText(current.getCompanyName());
                        paramTextText.setText(current.getPublicParamText());
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


    private void QueryAmount(String openId,
                             String companyId, String publicParamName, final String publicParamValue) throws JSONException {
        loadingButton.showLoading("正在查询···");
        WegRequest request = new WegRequest(MyApplication.HOST + "/ecommerce/querypublicutilityamt");
        request.setEntity(request.createQueryAmountParam(openId, companyId, publicParamName, publicParamValue));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                loadingButton.showResult("请求失败", false);
            }

            @Override
            public void onSuccess(Object result) {
                JSONObject json = (JSONObject) result;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        loadingButton.showResult("", true);
                        current.setPublicParamValue(publicParamValue);
                        Intent intent = new Intent(getApplicationContext(), WegTransactionsActivity.class);
                        intent.putExtra("amount", json.getJSONObject("body").getString("amount"));
                        intent.putExtra("company", current);
                        startActivity(intent);
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
}
