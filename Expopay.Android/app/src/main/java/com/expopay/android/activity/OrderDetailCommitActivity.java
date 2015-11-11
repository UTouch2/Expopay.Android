package com.expopay.android.activity;

import android.content.Intent;
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

    private ImageView commitProductImg;
    private TextView commitProductName;
    private TextView commitOrderAmount;
    private TextView commitProperties;
//    private TextView commitRepaymentPeriod;
    private TextView commitServiceAmount;
    private TextView commitPeriodAmount;
    private LinearLayout llAddress;
    private TextView commitConsignee;
    private TextView commitConsigneeMobile;
    private TextView commitConsigneeAddress;
    private CheckBox checkBox;
    private CustormLoadingButton btnSubmit;

    private void assignViews() {
        commitProductImg = (ImageView) findViewById(R.id.commitProductImg);
        commitProductName = (TextView) findViewById(R.id.commitProductName);
        commitOrderAmount = (TextView) findViewById(R.id.commitOrderAmount);
        commitProperties = (TextView) findViewById(R.id.commitProperties);
//        commitRepaymentPeriod = (TextView) findViewById(R.id.commitRepaymentPeriod);
        commitServiceAmount = (TextView) findViewById(R.id.commitServiceAmount);
        commitPeriodAmount = (TextView) findViewById(R.id.commitPeriodAmount);
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        commitConsignee = (TextView) findViewById(R.id.commitConsignee);
        commitConsigneeMobile = (TextView) findViewById(R.id.commitConsigneeMobile);
        commitConsigneeAddress = (TextView) findViewById(R.id.commitConsigneeAddress);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        btnSubmit = (CustormLoadingButton) findViewById(R.id.btnSubmit);
        btnSubmit.showNormal("提交订单");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_order_detail_commit);

        assignViews();
        setTextView();
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String repaymentPeriod = commitServiceAmount.getText().toString().trim();

                if (checkBox.isChecked()) {
                    try {
                        getOrder(getUser().getOpenId(), "", repaymentPeriod, "", "", "", "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(OrderDetailCommitActivity.this, "您未同意《南博卡分期协议》", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    private void setTextView() {
        Intent intent = getIntent();
        commitProductImg.setImageResource(R.mipmap.mall_mobile);
        commitProductName.setText(intent.getStringExtra("detailProductName"));
        commitOrderAmount.setText(intent.getStringExtra("detailAmount"));
        commitProperties.setText(intent.getStringExtra("tvSelected"));
//        commitRepaymentPeriod.setText("12期");
        commitServiceAmount.setText(intent.getStringExtra("tvStaging"));
        commitPeriodAmount.setText("每期应还款" + 470 + " ");
        commitConsignee.setText("收货人");
        commitConsigneeMobile.setText("13600000000");
        commitConsigneeAddress.setText("云南省昆明市五华区海源中路1088号和成国际A座25楼");
    }

}
