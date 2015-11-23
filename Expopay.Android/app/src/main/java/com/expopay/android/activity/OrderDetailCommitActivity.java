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
    private TextView receiverText;
    private TextView receiverMobileText;
    private TextView receiverAddressText;
    private ImageView commitProductImg;
    private TextView productNameText;
    private TextView propertyNameText;
    private TextView propertyValueText;
    private TextView orderAmountText;
    private TextView oproductQutityText;
    private TextView repaymentPeriodText;
    private TextView periodAmountText;
    private CheckBox checkBox;
    private CustormLoadingButton btnSubmit;

    protected void initView() {
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        receiverText = (TextView) findViewById(R.id.orderDetailCommit_receiver);
        receiverMobileText = (TextView) findViewById(R.id.orderDetailCommit_receiverMobile);
        receiverAddressText = (TextView) findViewById(R.id.orderDetailCommit_receiverAddress);
        commitProductImg = (ImageView) findViewById(R.id.orderDetailCommit_productImg);
        productNameText = (TextView) findViewById(R.id.orderDetailCommit_productName);
        propertyNameText = (TextView) findViewById(R.id.orderDetailCommit_propertyName);
        propertyValueText = (TextView) findViewById(R.id.orderDetailCommit_propertyValue);
        orderAmountText = (TextView) findViewById(R.id.orderDetailCommit_orderAmount);
        oproductQutityText = (TextView) findViewById(R.id.orderDetailCommit_productQutity);
        repaymentPeriodText = (TextView) findViewById(R.id.orderDetailCommit_repaymentPeriod);
        periodAmountText = (TextView) findViewById(R.id.orderDetailCommit_periodAmount);
        checkBox = (CheckBox) findViewById(R.id.orderDetailCommit_checkBox);
        btnSubmit = (CustormLoadingButton) findViewById(R.id.orderDetailCommit_submitBtn);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("订单详情");
        setContentView(R.layout.activity_order_detail_commit);

        initView();
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    public void submitOnclick(View view) {
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
