package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;

public class OrderDetailCommitActivity extends BaseActivity {

    private ImageView commitProductImg;
    private TextView commitProductName;
    private TextView commitOrderAmount;
    private TextView commitProperties;
    private TextView commitOrderStatus;
    private TextView commitRepaymentPeriod;
    private TextView commitServiceAmount;
    private TextView commitPeriodAmount;
    private LinearLayout llAddress;
    private TextView commitConsignee;
    private TextView commitConsigneeMobile;
    private TextView commitConsigneeAddress;
    private TextView commitOrderNumber;
    private TextView commitTranscationCode;
    private TextView commitOrderTime;

    private void assignViews() {
        commitProductImg = (ImageView) findViewById(R.id.commitProductImg);
        commitProductName = (TextView) findViewById(R.id.commitProductName);
        commitOrderAmount = (TextView) findViewById(R.id.commitOrderAmount);
        commitProperties = (TextView) findViewById(R.id.commitProperties);
        commitOrderStatus = (TextView) findViewById(R.id.commitOrderStatus);
        commitRepaymentPeriod = (TextView) findViewById(R.id.commitRepaymentPeriod);
        commitServiceAmount = (TextView) findViewById(R.id.commitServiceAmount);
        commitPeriodAmount = (TextView) findViewById(R.id.commitPeriodAmount);
        llAddress = (LinearLayout) findViewById(R.id.llAddress);
        commitConsignee = (TextView) findViewById(R.id.commitConsignee);
        commitConsigneeMobile = (TextView) findViewById(R.id.commitConsigneeMobile);
        commitConsigneeAddress = (TextView) findViewById(R.id.commitConsigneeAddress);
        commitOrderNumber = (TextView) findViewById(R.id.commitOrderNumber);
        commitTranscationCode = (TextView) findViewById(R.id.commitTranscationCode);
        commitOrderTime = (TextView) findViewById(R.id.commitOrderTime);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_commit);

        assignViews();
        setTextView();
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderDetailCommitActivity.this, "提交", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTextView(){
        commitProductImg.setImageResource(R.mipmap.mall_mobile);
        commitProductName.setText("iPhone6S");
        commitOrderAmount.setText("5200.00");
        commitProperties.setText("白色 32G");
        commitOrderStatus.setText("已完成");
        commitRepaymentPeriod.setText("12期");
        commitServiceAmount.setText("（440+30）x"+12+"="+(440+30)*12);
        commitPeriodAmount.setText("每期应还款"+470+" ");
        commitConsignee.setText("收货人");
        commitConsigneeMobile.setText("13600000000");
        commitConsigneeAddress.setText("云南省昆明市五华区海源中路1088号和成国际A座25楼");
        commitOrderNumber.setText("1234567890");
        commitTranscationCode.setText("交易流水号:678839903987484");
        commitOrderTime.setText("交易时间：2015-09-26  00:00:00");

    }
}
