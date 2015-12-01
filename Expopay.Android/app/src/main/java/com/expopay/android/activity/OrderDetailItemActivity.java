package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
import com.expopay.android.model.MallProductEntity;
import com.expopay.android.model.OrderItemEntity;
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
            MallProductEntity entity = (MallProductEntity) getIntent().getSerializableExtra("product");
            productImg.setImageBitmap(MyApplication.cache.getBitmapFromMemCache(entity.getProductImg()));
            orderNumber.setText(paymentOrderEntity.getOrderNumber());
            orderTime.setText(paymentOrderEntity.getOrderTime());
            if ("0".equals(paymentOrderEntity.getOrderStatus())){
                orderStatus.setText("未完成");
            }
            if ("1".equals(paymentOrderEntity.getOrderStatus())){
                orderStatus.setText("已完成");
            }
        }else if(periodOrderEntity!=null){
            productImg.setImageBitmap(MyApplication.cache.getBitmapFromMemCache(
                    ((OrderItemEntity) periodOrderEntity.getOrderItems()).getProductImg()));
            orderNumber.setText(periodOrderEntity.getOrderNumber());
            orderTime.setText(periodOrderEntity.getOrderTime());
            if ("0".equals(periodOrderEntity.getOrderStatus())){
                orderStatus.setText("未完成");
            }
            if ("1".equals(periodOrderEntity.getOrderStatus())){
                orderStatus.setText("已完成");
            }
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
