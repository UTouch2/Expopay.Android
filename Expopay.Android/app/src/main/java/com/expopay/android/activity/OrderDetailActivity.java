package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;

public class OrderDetailActivity extends BaseActivity {

    private LinearLayout llAddress;
    private TextView consigneeName;
    private TextView consigneeMobile;
    private TextView consigneeAddress;
    private ImageView productImg;
    private TextView productName;
    private TextView properties;
    private TextView orderAmount;
    private TextView repaymentPeriod;
    private TextView serviceAmount;
    private TextView cancelPeriodAmount;
    private CheckBox checkBox;

    private void assignViews() {
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        consigneeName = (TextView) findViewById(R.id.consigneeName);
        consigneeMobile = (TextView) findViewById(R.id.consigneeMobile);
        consigneeAddress = (TextView) findViewById(R.id.consigneeAddress);
        productImg = (ImageView) findViewById(R.id.productImg);
        productName = (TextView) findViewById(R.id.productName);
        properties = (TextView) findViewById(R.id.properties);
        orderAmount = (TextView) findViewById(R.id.orderAmount);
        repaymentPeriod = (TextView) findViewById(R.id.repaymentPeriod);
        serviceAmount = (TextView) findViewById(R.id.serviceAmount);
        cancelPeriodAmount = (TextView) findViewById(R.id.cancelPeriodAmount);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        assignViews();
        setTextView();
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderDetailActivity.this, "收获地址：", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnCommit(View view) {
        Toast.makeText(this, "提交订单", Toast.LENGTH_SHORT).show();
    }

    private void setTextView(){
        productImg.setImageResource(R.mipmap.mall_mobile);
        productName.setText("iPhone6S");
        properties.setText("白色");
        orderAmount.setText("5200");
        repaymentPeriod.setText("12期");
        serviceAmount.setText("12元");
        cancelPeriodAmount.setText("每期还款470元");
        consigneeName.setText("克里斯");
        consigneeMobile.setText("13600000000");
        consigneeAddress.setText("云南省昆明市五华区海源中路1088号和成国际A座25楼");
    }

}
