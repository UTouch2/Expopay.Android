package com.expopay.android.activity;

import android.content.Intent;
import android.graphics.Color;
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
import com.expopay.android.model.AddressEntity;
import com.expopay.android.model.ProductDetailsEntity;
import com.expopay.android.model.ProductPeroidEntity;
import com.expopay.android.request.AddressRequest;
import com.expopay.android.request.OrderRequest;
import com.expopay.android.utils.NBKCardPayUtil;
import com.expopay.android.view.CustormLoadingButton;
import com.expopay.android.view.CustormLoadingView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

public class OrderDetailCommitActivity extends BaseActivity {

    private LinearLayout chooseAddressView;
    private TextView receiverText;
    private TextView receiverMobileText;
    private TextView receiverAddressText;
    private TextView productNameText;
    private TextView propertyColorText;
    private TextView propertyMemoryText;
    private TextView orderAmountText;
    private TextView productQutityText;
    private TextView repaymentPeriodText;
    private TextView periodAmountText;
    private ImageView commitProductImg;
    private CheckBox checkBox;
    private CustormLoadingButton btnSubmit;

    private ProductDetailsEntity productDetailsEntity;
    private AddressEntity addressEntity;
    private ProductPeroidEntity peroidEntity;
    private CustormLoadingView loadingView;
    List<AddressEntity> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("订单详情");
        setContentView(R.layout.activity_order_detail_commit);
        initView();
        productDetailsEntity = (ProductDetailsEntity) getIntent().getSerializableExtra("product");
        peroidEntity = (ProductPeroidEntity) getIntent().getSerializableExtra("peroid");

        productNameText.setText(productDetailsEntity.getProductName());
        propertyColorText.setText(productDetailsEntity.getPropertyValue1());
        propertyMemoryText.setText(productDetailsEntity.getPropertyValue2());
        orderAmountText.setText(productDetailsEntity.getProductPrice());
        repaymentPeriodText.setText(String.format("%s期", peroidEntity.getPeriod()));
        periodAmountText.setText(String.format("每期应还款%s元", Double.parseDouble(peroidEntity.getPeriodAmount()) + Double.parseDouble(peroidEntity.getServiceAmount())));

        try {
            getAddress(getUser().getOpenId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void initView() {
        chooseAddressView = (LinearLayout) findViewById(R.id.llAddress);
        receiverText = (TextView) findViewById(R.id.orderDetailCommit_receiver);
        receiverMobileText = (TextView) findViewById(R.id.orderDetailCommit_receiverMobile);
        receiverAddressText = (TextView) findViewById(R.id.orderDetailCommit_receiverAddress);
        commitProductImg = (ImageView) findViewById(R.id.orderDetailCommit_productImg);
        productNameText = (TextView) findViewById(R.id.orderDetailCommit_productName);
        propertyColorText = (TextView) findViewById(R.id.orderDetailCommit_propertycolor);
        propertyMemoryText = (TextView) findViewById(R.id.orderDetailCommit_propertymemory);
        orderAmountText = (TextView) findViewById(R.id.orderDetailCommit_orderAmount);
        productQutityText = (TextView) findViewById(R.id.orderDetailCommit_productQutity);
        repaymentPeriodText = (TextView) findViewById(R.id.orderDetailCommit_repaymentPeriod);
        periodAmountText = (TextView) findViewById(R.id.orderDetailCommit_periodAmount);
        checkBox = (CheckBox) findViewById(R.id.orderDetailCommit_checkBox);
        loadingView = (CustormLoadingView) findViewById(R.id.orderDetailCommit_loadingview);
        btnSubmit = (CustormLoadingButton) findViewById(R.id.orderDetailCommit_submitBtn);
        btnSubmit.showNormal("提交订单");
        btnSubmit.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                btnSubmit.showNormal("提交订单");
                btnSubmit.setBackgroundResource(R.drawable._button);
            }

            @Override
            public void onFailureResult() {
                btnSubmit.showNormal("提交订单");
                btnSubmit.setBackgroundResource(R.drawable._button);
            }
        });
        chooseAddressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailCommitActivity.this, ChooseAddressActivity.class);
                intent.putExtra("address", (Serializable) addressList);
                startActivityForResult(intent, 1);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    try {
                        String price = productDetailsEntity.getProductPrice();
                        String productId = productDetailsEntity.getProductId();
                        String periodId = peroidEntity.getPeriodId();
                        String addressId = addressEntity.getAddressId();
                        productDetailsEntity.setQuantity("1");
                        getOrder(getUser().getOpenId(), "2", "101", price, new ProductDetailsEntity[]{productDetailsEntity}, periodId, addressId);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(OrderDetailCommitActivity.this, "您未同意《南博卡分期协议》", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loadingView.setAddOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailCommitActivity.this, AddressDetailsActivity.class);
                startActivityForResult(intent, 0);
            }
        });
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    addressEntity = (AddressEntity) data.getSerializableExtra("");
                    setAddressText(addressEntity);
                }
            case 1:
                if (resultCode == RESULT_OK) {
                    addressEntity = (AddressEntity) data.getSerializableExtra("entity");
                    setAddressText(addressEntity);
                }
                break;
            default:
                break;
        }
    }

    private void setAddressText(AddressEntity addressEntity) {
        receiverText.setText(addressEntity.getPersonName());
        receiverMobileText.setText(addressEntity.getMobile());
        receiverAddressText.setText(addressEntity.getAddress());
    }

    private void getOrder(String openId, final String orderSource, String paymentMethod, final String orerAmount,
                          ProductDetailsEntity[] productId, String periodId, String addressId) throws JSONException {
        btnSubmit.showLoading("正在提交...");
        OrderRequest request = new OrderRequest(MyApplication.HOST + "/order/createorder");
        request.setEntity(request.createCreateOrderParms(openId, orderSource,
                paymentMethod, orerAmount, productId, periodId, addressId));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                btnSubmit.showResult("网络请求失败", false);
                btnSubmit.setBackgroundColor(Color.parseColor("#ED4545"));
            }

            @Override
            public void onSuccess(Object o) {
                try {
                    JSONObject json = (JSONObject) o;
                    if (json.getJSONObject("header").getString("code").equals("0000")) {
                        String orderNumber = json.getJSONObject("body").getString("orderNumber");
                        btnSubmit.showResult("", true);
                        btnSubmit.setBackgroundResource(R.drawable._button);
                        NBKCardPayUtil.nbkCardCreditPay(OrderDetailCommitActivity.this, orderNumber, orderSource, orerAmount);
                    } else {
                        btnSubmit.showResult(json.getJSONObject("header").getString("desc"), false);
                        btnSubmit.setBackgroundColor(Color.parseColor("#ED4545"));
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
                        addressList = gson.fromJson(json.getJSONObject("body").getJSONArray("records").toString(),
                                new TypeToken<List<AddressEntity>>() {
                                }.getType());
                        if (addressList.size() == 0) {
                            loadingView.showAdd();
                            loadingView.setAddMessage("当前还没有添加收货地址");

                        } else {
                             loadingView.dismiss();
                            addressEntity = getDefaultAddress(addressList);
                            setAddressText(addressEntity);
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

    private AddressEntity getDefaultAddress(List<AddressEntity> list) {
        for (AddressEntity addressEntity : list) {
            if ("1".equals(addressEntity.getIsDefault())) {
                return addressEntity;
            }
        }
        return list.size() == 0 ? null : list.get(0);
    }
}
