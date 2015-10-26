package com.expopay.android.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;

public class OrderDetailCancelActivity extends BaseActivity {

    private ImageView cancelProductImg;
    private TextView cancelProductName;
    private TextView cancelOrderAmmount;
    private TextView cancelProperties;
    private TextView cancelOrderStatus;
    private TextView cancelRepaymentPeriod;
    private TextView cancelServiceAmount;
    private TextView cancelPeriodAmount;
    private TextView cancelConsignee;
    private TextView cancelConsigneeMobile;
    private TextView cancelConsigneeAddress;
    private TextView cancelOrderNumber;
    private TextView cancelTranscationCode;
    private TextView cancelOrderTime;

    private void assignViews() {
        cancelProductImg = (ImageView) findViewById(R.id.cancelProductImg);
        cancelProductName = (TextView) findViewById(R.id.cancelProductName);
        cancelOrderAmmount = (TextView) findViewById(R.id.cancelOrderAmmount);
        cancelProperties = (TextView) findViewById(R.id.cancelProperties);
        cancelOrderStatus = (TextView) findViewById(R.id.cancelOrderStatus);
        cancelRepaymentPeriod = (TextView) findViewById(R.id.cancelRepaymentPeriod);
        cancelServiceAmount = (TextView) findViewById(R.id.cancelServiceAmount);
        cancelPeriodAmount = (TextView) findViewById(R.id.cancelPeriodAmount);
        cancelConsignee = (TextView) findViewById(R.id.cancelConsignee);
        cancelConsigneeMobile = (TextView) findViewById(R.id.cancelConsigneeMobile);
        cancelConsigneeAddress = (TextView) findViewById(R.id.cancelConsigneeAddress);
        cancelOrderNumber = (TextView) findViewById(R.id.cancelOrderNumber);
        cancelTranscationCode = (TextView) findViewById(R.id.cancelTranscationCode);
        cancelOrderTime = (TextView) findViewById(R.id.cancelOrderTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_cancel);

        assignViews();
        setTextView();
    }

    private void setTextView(){
        cancelProductImg.setImageResource(R.mipmap.mall_mobile);
        cancelProductName.setText("iPhone6S");
        cancelOrderAmmount.setText("5200.00");
        cancelProperties.setText("白色 32G");
        cancelOrderStatus.setText("已完成");
        cancelRepaymentPeriod.setText("12期");
        cancelServiceAmount.setText("（440+30）x"+12+"="+(440+30)*12);
        cancelPeriodAmount.setText("每期还款"+470+"元");
        cancelConsignee.setText("克里斯");
        cancelConsigneeMobile.setText("13600000000");
        cancelConsigneeAddress.setText("云南省昆明市五华区海源中路1088号和成国际A座25楼");
        cancelOrderNumber.setText("1234567890");
        cancelTranscationCode.setText("交易流水号:678839903987484");
        cancelOrderTime.setText("交易时间：2015-09-26  00:00:00");

    }
}
