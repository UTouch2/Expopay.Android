package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;

public class OrderDetailItemActivity extends BaseActivity {

    private LinearLayout itemBlank;
    private ImageView itemProductImg;
    private TextView itemProductName;
    private TextView itemOrderNumber;
    private TextView itemTranscationCode;
    private TextView itemPayCode;
    private TextView itemOrderTime;
    private TextView itemOrderStatus;

    private void assignViews() {
        itemBlank = (LinearLayout) findViewById(R.id.itemBlank);
        itemProductImg = (ImageView) findViewById(R.id.itemProductImg);
        itemProductName = (TextView) findViewById(R.id.itemProductName);
        itemOrderNumber = (TextView) findViewById(R.id.itemOrderNumber);
        itemTranscationCode = (TextView) findViewById(R.id.itemTranscationCode);
        itemPayCode = (TextView) findViewById(R.id.itemPayCode);
        itemOrderTime = (TextView) findViewById(R.id.itemOrderTime);
        itemOrderStatus = (TextView) findViewById(R.id.itemOrderStatus);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_item);

        assignViews();
        setTextView();
        itemBlank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderDetailItemActivity.this, "返回去", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setTextView(){
        itemProductImg.setImageResource(R.mipmap.mall_mobile);
        itemProductName.setText("iPhone6S");
        itemOrderNumber.setText("1234567890");
        itemTranscationCode.setText("交易流水号:678839903987484");
        itemPayCode.setText("支付流水号：23456787655678");
        itemOrderTime.setText("交易时间：2015-09-26  00:00:00");
        itemOrderStatus.setText("未完成");

    }

    public void btnCancel(View view){
        finish();
    }

    public void btnOk(View view){
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
