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
                Toast.makeText(OrderDetailActivity.this, "�ջ���ַ��", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnCommit(View view) {
        Toast.makeText(this, "�ύ����", Toast.LENGTH_SHORT).show();
    }

    private void setTextView(){
        productImg.setImageResource(R.mipmap.mall_mobile);
        productName.setText("iPhone6S");
        properties.setText("��ɫ 32G");
        orderAmount.setText("5200.00");
        repaymentPeriod.setText("12��");
        serviceAmount.setText("12Ԫ");
        cancelPeriodAmount.setText("ÿ�ڻ���470Ԫ");
        consigneeName.setText("����˹");
        consigneeMobile.setText("13600000000");
        consigneeAddress.setText("����ʡ�������廪����Դ��·1088�źͳɹ���A��25¥");
    }

}
