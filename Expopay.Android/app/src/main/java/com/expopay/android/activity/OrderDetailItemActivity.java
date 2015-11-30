package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.PaymentOrderEntity;
import com.expopay.android.model.PeriodOrderEntity;

public class OrderDetailItemActivity extends BaseActivity {

    private ImageView productImg;
    private TextView productName;
    private TextView orderNumber;
    private TextView transcationCode;
    private TextView payCode;
    private TextView orderTime;
    private TextView orderStatus;

    private void initViews() {
        productImg = (ImageView) findViewById(R.id.orderDetailItem_ProductImg);
        productName = (TextView) findViewById(R.id.orderDetailItem_ProductName);
        orderNumber = (TextView) findViewById(R.id.orderDetailItem_OrderNumber);
        transcationCode = (TextView) findViewById(R.id.orderDetailItem_transcationCode);
        payCode = (TextView) findViewById(R.id.orderDetailItem_payCode);
        orderTime = (TextView) findViewById(R.id.orderDetailItem_orderTime);
        orderStatus = (TextView) findViewById(R.id.orderDetailItem_orderStatus);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarCoverActivity();
        setContentView(R.layout.activity_order_detail_item);
        initViews();

        PaymentOrderEntity paymentOrderEntity = (PaymentOrderEntity) getIntent().getSerializableExtra("paymentOrderData");
        PeriodOrderEntity periodOrderEntity = (PeriodOrderEntity) getIntent().getSerializableExtra("periodOrderData");
        if(paymentOrderEntity!=null){
            orderNumber.setText(paymentOrderEntity.getOrderNumber());

            orderTime.setText(paymentOrderEntity.getOrderTime());
            orderStatus.setText(paymentOrderEntity.getOrderStatus());
        }else if(periodOrderEntity!=null){
            orderNumber.setText(periodOrderEntity.getOrderNumber());

            orderTime.setText(periodOrderEntity.getOrderTime());
            orderStatus.setText(periodOrderEntity.getOrderStatus());
        }
    }

    public void cancelOrderItemOnclick(View view){
        finish();
    }

    public void OkOnclick(View view){
        finish();
    }

    public void itemBlankOnClick(View view){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
