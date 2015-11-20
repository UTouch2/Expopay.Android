package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.request.OrderRequest;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderDetailCommitActivity extends BaseActivity {

    private LinearLayout llAddress;
    private TextView orderDetailCommitReceiver;
    private TextView orderDetailCommitReceiverMobile;
    private TextView orderDetailCommitReceiverAddress;
    private LinearLayout linearLayout2;
    private ImageView commitProductImg;
    private TextView orderDetailCommitProductName;
    private TextView orderDetailCommitPropertyName;
    private TextView orderDetailCommitPropertyValue;
    private TextView commitOrderAmount;
    private TextView orderDetailCommitProductQutity;
    private TextView orderDetailCommitRepaymentPeriod;
    private TextView commitPeriodAmount;
    private CheckBox checkBox;
    private CustormLoadingButton btnSubmit;

    private void initViews() {
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        orderDetailCommitReceiver = (TextView) findViewById(R.id.orderDetailCommit_receiver);
        orderDetailCommitReceiverMobile = (TextView) findViewById(R.id.orderDetailCommit_receiverMobile);
        orderDetailCommitReceiverAddress = (TextView) findViewById(R.id.orderDetailCommit_receiverAddress);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        commitProductImg = (ImageView) findViewById(R.id.commitProductImg);
        orderDetailCommitProductName = (TextView) findViewById(R.id.orderDetailCommit_productName);
        orderDetailCommitPropertyName = (TextView) findViewById(R.id.orderDetailCommit_propertyName);
        orderDetailCommitPropertyValue = (TextView) findViewById(R.id.orderDetailCommit_propertyValue);
        commitOrderAmount = (TextView) findViewById(R.id.commitOrderAmount);
        orderDetailCommitProductQutity = (TextView) findViewById(R.id.orderDetailCommit_productQutity);
        orderDetailCommitRepaymentPeriod = (TextView) findViewById(R.id.orderDetailCommit_repaymentPeriod);
        commitPeriodAmount = (TextView) findViewById(R.id.commitPeriodAmount);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        btnSubmit = (CustormLoadingButton) findViewById(R.id.btnSubmit);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("订单详情");
        setContentView(R.layout.activity_order_detail_commit);

        initViews();
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    public void submitOnclick(View view){
        if (checkBox.isChecked()) {
            try {
                getOrder(getUser().getOpenId(), "", "", "", "", "", "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(OrderDetailCommitActivity.this, "您未同意《南博卡分期协议》", Toast.LENGTH_SHORT).show();
        }
    }

    private void getOrder(String openId, String orderSource, String paymentMethod, String orerAmount,
                          String productId, String periodId, String addressId) throws JSONException {
        btnSubmit.showLoading("正在提交...");
        OrderRequest request = new OrderRequest(MyApplication.HOST + "");
        request.setEntity(request.createCreateOrderParms(openId, orderSource,
                paymentMethod, orerAmount, productId, periodId, addressId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                btnSubmit.showResult("网络请求失败", false);
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = (JSONObject) o;
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        btnSubmit.showResult("订单提交成功", true);
                    } else {
                        btnSubmit.showResult(json.getJSONObject("header").getString("desc"), false);
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
