package com.expopay.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.CompanyEntity;
import com.expopay.android.request.OrderRequest;
import com.expopay.android.utils.NBKCardPayUtil;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by misxu012 on 2015/10/20.
 */
public class WegTransactionsActivity extends BaseActivity {
    String orderNumber, orderSource, orderAmount;

    private CompanyEntity companyEntity;
    private CustormLoadingButton wegtransactionOk;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_wegtransaction);
        wegtransactionOk = (CustormLoadingButton) findViewById(R.id.wegtransaction_ok);
        wegtransactionOk.showNormal("缴费");
        listView = (ListView) findViewById(R.id.wegtransaction_listview);
        listView.setAdapter(null);
        wegtransactionOk.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                NBKCardPayUtil.nbkCardPay(WegTransactionsActivity.this, orderNumber, orderSource, orderAmount);
                wegtransactionOk.showNormal("缴费");
            }

            @Override
            public void onFailureResult() {
                wegtransactionOk.showNormal("缴费");
            }
        });

        wegtransactionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createOrderRequest(getUser().getOpenId(), orderSource, "4", "0.01", companyEntity.getCompanyId(), companyEntity.getPublicParamName(), companyEntity.getPublicParamValue(), companyEntity.getPublicParamText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        companyEntity = (CompanyEntity) getIntent().getSerializableExtra("company");
        orderSource = "1";
        orderAmount = getIntent().getStringExtra("amount");
    }


    public void closeOnclick(View v) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            finish();
        }
    }

    private void createOrderRequest(String openId, final String orderSource,
                                    String paymentMethod, final String orderAmount,
                                    String publicCompanyID, String publicParamName,
                                    String publicParamValue, String publicParamText) throws JSONException {
        wegtransactionOk.showLoading("正在加载中···");
        OrderRequest request = new OrderRequest(MyApplication.HOST + "/order/createorder");
        request.setEntity(request.createCreateOrderParms(openId, orderSource, paymentMethod, orderAmount, publicCompanyID, publicParamName, publicParamValue, publicParamText));
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
                        orderNumber = json.getJSONObject("body").getString("orderNumber");
                        wegtransactionOk.showResult("", true);
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
